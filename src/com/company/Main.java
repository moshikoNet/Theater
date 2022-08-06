package com.company;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        int choice;

        Show abba_bandShow = new Show("Abba Band", 8, 10, 60, 40.5);
        Show frozenShow = new Show("Frozen", 8, 12, 90, 60.9);
        Show michaelJackson = new Show("Michael Jackson", 8, 12, 75, 99.9);

        Visitor visitor = new Visitor("Moshiko Netzer");
        visitor.addTransaction(45.3);
        visitor.addTransaction(-63);
        visitor.addTransaction(100.5);

        Theater<Show> showsTheater = new Theater<>("showTheater");

        showsTheater.addShow(abba_bandShow);
        showsTheater.addShow(frozenShow);
        showsTheater.addShow(michaelJackson);

        System.out.println();
        System.out.println("Hello " + visitor.getVisitorName() + " welcome to the \"" + showsTheater.getTheaterName() + "\" Theater \n");

        while (!quit) {

            printMainMenu();

            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Please enter only form the options below:");
                printMainMenu();

            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                //show running shows
                case 1:
                    showsTheater.printRunningShows();
                    break;

                //reserve a seat for a show
                case 2:

                    System.out.print("\nPlease choose one of the running shows, ");
                    showsTheater.printRunningShows();

                    System.out.println("Press : " + (showsTheater.getShows().size() + 1) + " to return to main menu.");

                    while (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("\nWrong choice, please choose again");
                        showsTheater.printRunningShows();
                        System.out.println("Press : " + (showsTheater.getShows().size() + 1) + " to return to main menu.");

                    }

                    int showChoice = scanner.nextInt();
                    scanner.nextLine();

                    boolean quitShowMenu = false;

                    while (showChoice > showsTheater.getShows().size()) {

                        if (showChoice > (showsTheater.getShows().size() + 1)) {

                            System.out.println("\nWrong choice, please choose again");

                            showsTheater.printRunningShows();

                            System.out.println("Press : " + (showsTheater.getShows().size() + 1) + " to return to main menu");
                        }

                        if (showChoice == showsTheater.getShows().size() + 1) break;

                        while (!scanner.hasNextInt()) {
                            scanner.nextLine();
                            System.out.println("\nWrong choice, please choose again");

                        }

                        showChoice = scanner.nextInt();
                        scanner.nextLine();

                    }

                    if (showChoice == showsTheater.getShows().size() + 1) {
                        System.out.println("\nReturning to main menu. \n");
                        break;
                    }

                    Show show = showsTheater.getShows().get(showChoice - 1);

                    System.out.println("\nYou choose the show \"" + show.getShowName() + "\"... Good choice  :) \n");

                    while (!quitShowMenu) {

                        printShowMenu();

                        while (!scanner.hasNextInt()) {
                            scanner.nextLine();
                            System.out.println("Please enter only form the options below: \n");
                            printShowMenu();

                        }

                        int reservationChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (reservationChoice) {

                            case 1:
                                System.out.println("\nPlease choose a seat to be reserved: \n" +
                                        "enter a row number from [A00 TO " + (char) show.getRowsNumber() + show.getSeatsNumber() + "]\n");

                                String seatToBeReserved = scanner.nextLine();

                                System.out.println("You choose seat number: \"" + seatToBeReserved + "\" \nPlease wait for conformation...");
                                TimeUnit.SECONDS.sleep(1);

                                Reservation reservationConformation = show.reserveTheSeat(seatToBeReserved, visitor);
                                if (reservationConformation != null) {
                                    System.out.println("Your conformation ID: " + reservationConformation.getConformationID() + " \n");
                                    visitor.getReservations().add(reservationConformation);
                                }

                                quitShowMenu = true;
                                break;

                            case 2:
                                System.out.println("\nReturning to main menu.");
                                quitShowMenu = true;
                                break;

                            default:
                                System.out.println("Please choose only form the options given:");
                                printShowMenu();
                                break;
                        }
                    }
                    break;

                case 3:

                    System.out.print("\n\033[1;4mPlease choose the relevant reservation:\033[0m\n");

                    visitor.seeMyReservations();

                    System.out.println("Press : " + (visitor.getReservations().size() + 1) + " to return to main menu.");

                    int reservationChoice = scanner.nextInt();
                    scanner.nextLine();

                    boolean quitReservationMenu = false;

                    while (reservationChoice > visitor.getReservations().size()) {

                        if (reservationChoice == visitor.getReservations().size() + 1) break;

                        if (reservationChoice > (visitor.getReservations().size() + 1)) {

                            System.out.println("\nWrong choice, please choose again");

                            visitor.seeMyReservations();

                            System.out.println("Press : " + (visitor.getReservations().size() + 1) + " to return to main menu");
                        }

                        reservationChoice = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (reservationChoice == visitor.getReservations().size() + 1) {
                        System.out.println("\nReturning to main menu. \n");
                        break;
                    }

                    Reservation reservation = visitor.getReservations().get(reservationChoice - 1);

                    System.out.println("\nYou choose to cancel the reservation for the show: \"" + reservation.getShowName() + "\"\n");

                    for (Show cancelShow : showsTheater.getShows()) {
                        if (reservation.getShowName().compareTo(cancelShow.getShowName()) == 0) {
                            if (cancelShow.cancelReservation(reservation.getSeatsNumber()))
                                visitor.addTransaction(cancelShow.getReservedTicketPrice());
                            visitor.getReservations().remove(reservation);
                            System.out.println("reservation canceled successfully");
                            break;
                        }

                    }

                    break;

                case 4:
                    visitor.seeMyReservations();
                    break;

                case 5:
                    System.out.println("See you soon... please come again :)");
                    quit = true;
                    break;

                case 6:

                    System.out.println("The balance of \"" + visitor.getVisitorName() + "\" is : " + visitor.getBalance()+"[$]. \n");
                    break;

                default:
                    System.out.println("please choose only form the options given:");
                    printMainMenu();

            }

        }

    }

    static void printMainMenu() {

        System.out.println("\033[1;4mPlease choose an option:\033[0m\n" +
                "1 - Find out which shows are running today.\n" +
                "2 - Reserve seats for a show.\n" +
                "3 - Cancel a reservation for a show.\n" +
                "4 - See my reservations. \n" +
                "5 - Quit menu.  \n" +
                "6 - visitor account balance"
        );
    }

    static void printShowMenu() {

        System.out.println("\033[1;4mPlease choose an option:\033[0m\n" +
                "1 - Reserve seats for the show.\n" +
                "2 - Quit to main menu.  ");
    }
}
