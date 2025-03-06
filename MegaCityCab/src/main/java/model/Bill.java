package model;

import java.sql.Timestamp;

public class Bill {
    private int billId;
    private int bookingId;
    private double totalAmount;
    private Timestamp billTime;

    // Constructor
    public Bill(int billId, int bookingId, double totalAmount, Timestamp billTime) {
        this.billId = billId;
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.billTime = billTime;
    }

    // Getters and Setters
    public int getBillId() { return billId; }
    public int getBookingId() { return bookingId; }
    public double getTotalAmount() { return totalAmount; }
    public Timestamp getBillTime() { return billTime; }
}
