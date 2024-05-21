package com.example.planifyfinal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

// Class representing an event
public class Event {

    // Static list of all events
    public static ArrayList<Event> eventsList = new ArrayList<>();

    // Method to get all events for a specific date
    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        // Loop through all events and add the ones with the matching date to the list
        for (Event event : eventsList) {
            if (event.getDate().equals(date)) {
                events.add(event);
            }
        }

        return events;
    }

    // Method to get all events for a specific date and time
    public static ArrayList<Event> eventsForDateAndTime(LocalDate date, LocalTime time) {
        ArrayList<Event> events = new ArrayList<>();

        // Loop through all events and add the ones with the matching date and time to the list
        for (Event event : eventsList) {
            int eventHour = event.time.getHour();
            int cellHour = time.getHour();
            if (event.getDate().equals(date) && eventHour == cellHour){
                events.add(event);
            }
        }

        return events;
    }
    private String name; // Name of the event
    private LocalDate date; // Date of the event
    private LocalTime time; // Time of the event

    // Constructor for the Event class
    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    } // Getter for the name

    public void setName(String name) {
        this.name = name;
    } // Setter for the name

    public LocalDate getDate() {
        return date;
    } // Getter for the date

    public void setDate(LocalDate date) {
        this.date = date;
    } // Setter for the date

    public LocalTime getTime() {
        return time;
    } // Getter for the time

    public void setTime(LocalTime time) {
        this.time = time;
    } // Setter for the time
}
