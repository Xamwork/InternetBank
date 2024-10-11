package org.example.dto;

public class TransferRequest {

    private Long senderId;   // ID отправителя
    private Long receiverId; // ID получателя
    private double amount;   // Сумма перевода

    // Getters и Setters
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}