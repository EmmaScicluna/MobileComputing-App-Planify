package com.example.planifyfinal;

import java.time.LocalTime;
import java.util.ArrayList;

// Class representing an event that occurs at a specific hour
public class HourEvent {
    LocalTime time; // The time at which the event occurs
    ArrayList<Event> events; // A list of events that occur at this time

    public HourEvent(LocalTime time, ArrayList<Event> events) { // Constructor for the HourEvent class
        this.time = time;
        this.events = events;
    }

    public LocalTime getTime() { // Getter for the time attribute
        return time;
    }

    public void setTime(LocalTime time) { // Setter for the time attribute
        this.time = time;
    }

    public ArrayList<Event> getEvents() { // Getter for the events attribute
        return events;
    }

    public void setEvents(ArrayList<Event> events) { // Setter for the events attribute
        this.events = events;
    }
}
