package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;         // ID перевода

    @Column(nullable = false)
    private Long senderId;           // ID отправителя

    @Column(nullable = false)
    private Long receiverId;         // ID получателя

    @Column(nullable = false)
    private double amount;           // Сумма перевода

    @Column(nullable = false)
    private LocalDateTime transferDate; // Дата и время перевода

    // Getters и Setters

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

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

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }
}
