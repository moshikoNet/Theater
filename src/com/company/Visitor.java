package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Visitor {

    private final String visitorName;
    private ArrayList<Double> transactions;
    private double balance;
    private int conformationID;
    private LinkedList<Reservation> reservations ;

    public Visitor(String visitorName) {
        this.visitorName = visitorName;
        this.balance=0;
        this.transactions =new ArrayList<>();
        this.reservations= new LinkedList<>();
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public LinkedList<Reservation> getReservations() {
        return reservations;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void addTransaction(double transaction){
        this.transactions.add(transaction);
        this.balance +=transaction;

    }

    public double getBalance() {

        //System.out.println("the balance of \""+ this.visitorName+"\" is: "+ this.balance);

        return this.balance;
    }

    public int getConformationID() {
        return conformationID;
    }

    public void setConformationID(int conformationID) {
        this.conformationID = conformationID;
    }

public void seeMyReservations(){

    ListIterator<Reservation> reservationListIteratorIterator = reservations.listIterator();

    int index = 1;

    System.out.println("\033[1;4m\""+this.visitorName+ "\" reservations:\033[0m\n" );

    if (!reservationListIteratorIterator.hasNext()){
        System.out.println("There are no reservations :( \n");
    }
    while (reservationListIteratorIterator.hasNext()){

        Reservation reservation =reservationListIteratorIterator.next();
        System.out.println(index + ") " + "Reservation for :\"" + reservation.getShowName() +
                "\" , seat number: " + reservation.getSeatsNumber()+" , cost : "+ reservation.getReservedTicketPrice()+ "[$]. \n");

        index++;
    }
}

}
