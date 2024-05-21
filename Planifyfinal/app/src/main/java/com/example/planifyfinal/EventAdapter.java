package com.example.planifyfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

// Adapter class for Event objects
public class EventAdapter extends ArrayAdapter<Event> {
    // Constructor for the EventAdapter class
    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    // Method to get the view for an item in the adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Getting the Event at the current position
        Event event = getItem(position);

        if (convertView == null) { // If the view is null, inflate the event_cell layout for the view to display the event
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV); // Getting the TextView for the event cell

        String eventTitle = event.getName() + " " + CalendarUtils.formattedTime(event.getTime()); // Creating the event title with the event name and time
        eventCellTV.setText(eventTitle); // Setting the event title to the TextView
        return convertView; // Returning the view
    }
}
