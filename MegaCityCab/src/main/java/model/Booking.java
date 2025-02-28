package model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private String customerName;
    private String pickupLocation;
    private String destination;
    private double fare;
    private String status;
    private Timestamp bookingTime;

    // Constructor to initialize the booking object
    public Booking(int bookingId, String customerName, String pickupLocation, String destination, double fare, String status, Timestamp bookingTime) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.fare = fare;
        this.status = status;
        this.bookingTime = bookingTime;
    }
    
    public Booking() {
    }

    // Getter methods
    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    // Setter methods (if needed)
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", customerName=" + customerName + ", pickupLocation=" + pickupLocation
                + ", destination=" + destination + ", fare=" + fare + ", status=" + status + ", bookingTime=" + bookingTime + "]";
    }
}
