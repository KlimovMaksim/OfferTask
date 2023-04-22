package org.example;

public class Message {
    // Message identifier, only numerical symbols are allowed
    private String trace_id;
    // reference on Offer object
    private Offer offer;

    // геттеры и сеттеры private полей
    public String getTrace_id() {
        return trace_id;
    }
    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }
    public Offer getOffer() {
        return offer;
    }
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Message(String trace_id, Offer offer) {
        this.trace_id = trace_id;
        this.offer = offer;
    }
}
