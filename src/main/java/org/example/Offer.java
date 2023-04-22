package org.example;

public class Offer {
    // Offer identifier, only numerical symbols are allowed
    private String id;
    // Offer price, value in range from 0 to 109
    private int price;
    // Items left on stocks, value in range from 0 to 109
    private int stock_count;
    // Object partner
    private PartnerCount partner_count;

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

    public Offer(String id) {
        this.id = id;
        this.price = -1;
        this.stock_count = -1;
        this.partner_count = null;
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
    }
}
