package com.company;

import java.util.ArrayList;

public class Show {


    private final String showName;
    private final int showDuration;
    private final int rowsNumber;
    private final int seatsNumber;
    private double reservedTicketPrice;

    private ArrayList<ArrayList<Seat>> rows;

    //final field can be set in the declaration or in the constructor

    public Show(String showName, int rowsNumber, int seatsNumber, int showDuration, double reservedTicketPrice) {

        this.reservedTicketPrice = reservedTicketPrice;
        this.showName = showName;
        this.showDuration = showDuration;

        this.rowsNumber = 'A' + (rowsNumber - 1);
        this.seatsNumber = seatsNumber;

        // rowsNumber is set as pure integer, Not the object field (this.) !
        rows = new ArrayList<>(rowsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            rows.add(new ArrayList(i));
        }

        // the rowNumber is defined as a letter, starting from 'A' to the defined rowsNumber
        //   could be written as:  for (char currentRow = 'A'; currentRow <= (char) this.rowsNumber; currentRow++) {

        for (char currentRow = 'A'; currentRow <= this.rowsNumber; currentRow++) {

            //(currentRow-'A') returning to number, not ASCII;
            // setting the seat numbers as rowNumber+the seatNumber in a format of 2 digits.
            for (int i = 0; i <= seatsNumber; i++) {

                Seat currentSeat = new Seat(currentRow + String.format("%02d", i));
                this.rows.get((currentRow - 'A')).add(currentSeat);

            }

        }


    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public String getShowName() {
        return showName;
    }

    public int getShowDuration() {
        return showDuration;
    }

    public double getReservedTicketPrice() {
        return reservedTicketPrice;
    }

    public Reservation reserveTheSeat(String requestedSeatNumber, Visitor visitor) {

        if (requestedSeatNumber == null || requestedSeatNumber.length() != 3) return null;

        char rowNumberToBeReserved = requestedSeatNumber.charAt(0);
        int seatNumberToBeReserved = Integer.parseInt(requestedSeatNumber.substring(1, 3));

        if (seatNumberToBeReserved >= 0 & rowNumberToBeReserved <= this.rowsNumber & rowNumberToBeReserved >= 'A' & seatNumberToBeReserved <= this.seatsNumber) {

            //seat is a pointer to the required seat in the arrayList
            //so it saves the change in the reservation -> it is not a local variable

            Seat seat = this.rows.get(rowNumberToBeReserved - 'A').get(seatNumberToBeReserved);

            if (!seat.isReserved) {
                if (visitor.getBalance() - this.reservedTicketPrice < 0) {
                    System.out.println("You are account balance is too short... sorry you can't reserve a seat..");
                    return null;
                } else {
                    visitor.addTransaction(-this.reservedTicketPrice);
                    System.out.println("The seat number : " + seat.getSeatNumber() + ", is now reserved for visitor named: \""
                            + visitor.getVisitorName() + "\"" +
                            ", the updated balance is: " + visitor.getBalance() + "[$] \n");

                    seat.setReservedConformationNumber((int) (Math.PI * (Math.random() * 1000)));
                    seat.isReserved = true;
                    return new Reservation(visitor.getVisitorName(), seat.getReservedConformationNumber(), this.showName,
                            requestedSeatNumber, this.reservedTicketPrice);
                }

            } else {
                System.out.println("The seat number : " + seat.getSeatNumber() + " is already taken.. sorry");
                return null;
            }
        } else {
            System.out.println("There is no such seat number : " + requestedSeatNumber);
            return null;
        }

    }

    boolean cancelReservation(String reservedSeatNumber) {

        if (reservedSeatNumber == null || reservedSeatNumber.length() != 3) {
            return false;
        } else {

            char rowNumberToBeCancel = reservedSeatNumber.charAt(0);
            int seatNumberToBeCancel = Integer.parseInt(reservedSeatNumber.substring(1, 3));

            if (rowNumberToBeCancel > this.rowsNumber || seatNumberToBeCancel > this.seatsNumber) {

                System.out.println("There is no such reserved for seat number : " + reservedSeatNumber);
                return false;
            } else {

                Seat seat = this.rows.get(rowNumberToBeCancel - 'A').get(seatNumberToBeCancel);

                if (seat.isReserved) {
                    seat.isReserved = false;
                    System.out.println("The reserved seat : " + seat.getSeatNumber() + " is now canceled\n");
                    return true;
                } else {
                    System.out.println("The seat : " + seat.getSeatNumber() + " was never reserved\n");
                    return false;
                }
            }
        }
    }

    private class Seat {


        // seat number is set as letter (such as 'A') and 2 digits number(such as 01)

        private final String seatNumber;
        private boolean isReserved = false;
        private int reservedConformationNumber;

        public int getReservedConformationNumber() {
            return reservedConformationNumber;
        }

        public void setReservedConformationNumber(int reservedConformationNumber) {
            this.reservedConformationNumber = reservedConformationNumber;
        }

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isReserved() {
            return isReserved;
        }


    }
}
