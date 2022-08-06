package com.company;

public class Reservation<dateOfTheShow> {

    private final String reservationByName;
    private final int conformationID;
    private final String showName;
    private final String seatsNumber;
    private final double reservedTicketPrice;

    public Reservation(String reservationByName, int conformationID, String showName, String seatsNumber, double reservedTicketPrice) {
        this.reservationByName = reservationByName;
        this.conformationID = conformationID;
        this.showName = showName;
        this.seatsNumber = seatsNumber;
        this.reservedTicketPrice = reservedTicketPrice;
    }

    public double getReservedTicketPrice() {
        return reservedTicketPrice;
    }

    public int getConformationID() {
        return conformationID;
    }

    public String getShowName() {
        return showName;
    }

    public String getSeatsNumber() {
        return seatsNumber;
    }
}
