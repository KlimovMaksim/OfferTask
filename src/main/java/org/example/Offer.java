package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;

public class Offer {
    // Offer identifier, only numerical symbols are allowed
    private String id;
    // Offer price, value in range from 0 to 109
    private int price;
    // Items left on stocks, value in range from 0 to 109
    private int stock_count;
    // Object partner
    private PartnerCount partner_count;
    @JsonIgnore
    // Set of trigger fields
    public HashSet<String> triggerSet;
    // Set of shipment fields
    public HashSet<String> shipmentSet;

    public Offer() {
        this.price = -1;
        this.stock_count = -1;
    }

    public Offer(String id, int triggerCount, int shipmentCount) {
        this.id = id;
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
    public PartnerCount getPartner_count() {
        return partner_count;
    }
    public void setPartner_count(PartnerCount partner_count) {
        this.partner_count = partner_count;
    }

    public boolean priceUpdate(int newPrice){
        if (this.price == newPrice){
            return false;
        }
        else{
            this.price = newPrice;
            return true;
        }
    }

    public boolean stock_countUpdate(int newStock_count){
        if (this.stock_count == newStock_count){
            return false;
        }
        else {
            this.stock_count = newStock_count;
            return true;
        }
    }

    public boolean partner_countUpdate(PartnerCount newPartner_count){
        if (newPartner_count == null) { return false; }
        if (this.partner_count.titleUpdate(newPartner_count.getTitle()) || this.partner_count.descriptionUpdate(newPartner_count.getDescription())){
            return true;
        }
        else {
            return false;
        }
    }

    private class PartnerCount {
        // Offer title filled in by the partner
        private String title;
        // Offer description filled in by partner
        private String description;

        // геттеры и сеттеры для private полей класса PartnerCount
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

        public PartnerCount() {
        }

        public boolean titleUpdate(String newTitle){
            if (Objects.equals(this.title, newTitle)){
                return false;
            }
            else{
                this.title = newTitle;
                return true;
            }
        }

        public boolean descriptionUpdate(String newDescription){
            if (Objects.equals(this.description, newDescription)){
                return false;
            }
            else{
                this.description = newDescription;
                return true;
            }
        }
    }
}
