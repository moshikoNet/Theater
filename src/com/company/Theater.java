package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Theater<T extends Show> {

    private final String theaterName;
    private LinkedList<T> shows;

    public Theater(String theaterName) {
        this.theaterName = theaterName;
        this.shows = new LinkedList<>();
    }

    public String getTheaterName() {
        return theaterName;
    }

    public LinkedList<T> getShows() {
        return shows;
    }

    public boolean addShow(T newShow) {

        if (newShow == null) {
            System.out.println("There is a no show");
            return false;
        }

        if (shows.contains(newShow)) {
            System.out.println("The show named: \"" + newShow.getShowName() + "\" already played in the \"" + this.theaterName + "\" theater");
            return false;
        }

        shows.add(newShow);
        System.out.println("The show named: \"" + newShow.getShowName() + "\", was added to the \"" + this.theaterName + "\" theater");
        return true;

    }

    public boolean removeAShow(T newShow) {

        if (newShow == null) {
            System.out.println("there is a no show");
            return false;
        }

        if (shows.contains(newShow)) {
            shows.remove(newShow);
            System.out.println("the show named: \"" + newShow.getShowName() + "\" was canceled and removed from the shows of the \"" + this.theaterName + "\" theater");
            return true;

        }else
        {
            System.out.println("the show named: \"" + newShow.getShowName() + "\" never have been played in the theater");
            return false;
        }
    }

    public void printRunningShows() {

        int index = 1;

        ListIterator<T> showsIterator = shows.listIterator();

        System.out.println("\033[1;4mThe Theater \""+this.theaterName+"\" running shows are:\033[0m\n");

        while (showsIterator.hasNext()){

            T show =showsIterator.next();
            System.out.println(index + ") " + "show name :\"" + show.getShowName() +
                    "\" , show duration: " + show.getShowDuration()+" [min] , Ticket costs: "+ show.getReservedTicketPrice()+ "[$]. \n");
            index++;
        }

    }
}

