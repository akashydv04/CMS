package com.example.cms.model;

public class TransactionModel {

    String transaction_id, sender_name, sender_id, receiver_name, receiver_id, transaction_amount;

    public TransactionModel(String transaction_id, String sender_name, String sender_id, String receiver_name, String receiver_id, String transaction_amount) {
        this.transaction_id = transaction_id;
        this.sender_name = sender_name;
        this.sender_id = sender_id;
        this.receiver_name = receiver_name;
        this.receiver_id = receiver_id;
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }
}
