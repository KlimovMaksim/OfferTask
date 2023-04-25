package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashSet;
import java.util.Objects;

public class Offer {
    private String id; // Offer identifier, only numerical symbols are allowed
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = IntegerFilter.class)
    private int price; // Offer price, value in range from 0 to 109
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = IntegerFilter.class)
    private int stock_count; // Items left on stocks, value in range from 0 to 109
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PartnerContent partner_content; // Object partner
    @JsonIgnore
    public HashSet<String> triggerSet; // Set of trigger fields
    @JsonIgnore
    public HashSet<String> shipmentSet; // Set of shipment fields

    public Offer() {
        this.price = -1;
        this.stock_count = -1;
    }

    public Offer(String id, int triggerCount, int shipmentCount) {
        this.id = id;
        this.price = -1;
        this.stock_count = -1;
        this.triggerSet = new HashSet<>(triggerCount);
        this.shipmentSet = new HashSet<>(shipmentCount);
    }

    // геттеры и сеттеры для private полей класса Offer
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStock_count() {
        return stock_count;
    }
    public void setStock_count(int stock_count) {
        this.stock_count = stock_count;
    }
    public PartnerContent getPartner_content() {
        return partner_content;
    }
    public void setPartner_content(PartnerContent partner_content) {
        this.partner_content = partner_content;
    }

    public boolean priceUpdate(int newPrice){
        if (this.price == newPrice || newPrice == -1){
            return false;
        }
        else{
            this.price = newPrice;
            return true;
        }
    }

    public boolean stock_countUpdate(int newStock_count){
        if (this.stock_count == newStock_count || newStock_count == -1){
            return false;
        }
        else {
            this.stock_count = newStock_count;
            return true;
        }
    }

    public boolean partner_contentUpdate(PartnerContent newPartner_content){
        if (newPartner_content == null) {
            return false;
        }
        else if (this.partner_content == null){
            this.partner_content = newPartner_content;
            return true;
        }
        return this.partner_content.titleUpdate(newPartner_content.getTitle()) || this.partner_content.descriptionUpdate(newPartner_content.getDescription());
    }

    private class PartnerContent {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String title; // Offer title filled in by the partner
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description; // Offer description filled in by partner

        // геттеры и сеттеры для private полей класса PartnerContent
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public PartnerContent() {
        }

        public boolean titleUpdate(String newTitle){
            if (Objects.equals(this.title, newTitle) || newTitle == null){
                return false;
            }
            else{
                this.title = newTitle;
                return true;
            }
        }

        public boolean descriptionUpdate(String newDescription){
            if (Objects.equals(this.description, newDescription) || newDescription == null){
                return false;
            }
            else{
                this.description = newDescription;
                return true;
            }
        }
    }
}

class IntegerFilter{
    @Override
    public boolean equals(Object obj) {
        return Integer.valueOf(-1).equals(obj);
    }
}
