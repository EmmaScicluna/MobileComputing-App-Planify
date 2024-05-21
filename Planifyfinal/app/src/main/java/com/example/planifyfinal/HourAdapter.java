package com.example.planifyfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Adapter class for HourEvent objects
public class HourAdapter extends ArrayAdapter<HourEvent> {
    // Constructor for the HourAdapter class
    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents) {
        super(context, 0, hourEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { // Method to get the view for an item in the adapter
        HourEvent event = getItem(position); // Get the HourEvent object at the specified position

        if (convertView == null) { // If the view is null (i.e. it has not been created yet) then inflate the view
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);
        }

        setHour(convertView, event.time); // Set the hour for the view
        setEvents(convertView, event.events); // Set the events for the view

        return convertView; // Return the view
    }

    private void setHour(View convertView, LocalTime time) { // Method to set the hour for a view
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(CalendarUtils.formattedShortTime(time));
    }

    private void setEvents(View convertView, ArrayList<Event> events) { // Method to set the events for a view
        TextView event1 = convertView.findViewById(R.id.event1);
        TextView event2 = convertView.findViewById(R.id.event2);
        TextView event3 = convertView.findViewById(R.id.event3);

        if (events.size() == 0) { // Depending on the number of events, show or hide the event TextViews accordingly
            hideEvent(event1);
            hideEvent(event2);
            hideEvent(event3);
        } else if (events.size() == 1) { // If there is exactly 1 event, show the first event and hide the other two
            setEvent(event1, events.get(0));
            hideEvent(event2);
            hideEvent(event3);
        } else if (events.size() == 2) { // If there are exactly 2 events, show both events
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            hideEvent(event3);
        } else if (events.size() == 3) { // If there are exactly 3 events, show all three events
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            setEvent(event3, events.get(2));
        }else { // If there are more than 3 events, show the first two events and display the number of events not shown in the third TextView
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            event3.setVisibility(View.VISIBLE);

            String eventsNotShown = String.valueOf(events.size() - 2); // Calculate the number of events not shown
            eventsNotShown += "More events";
            event3.setText(eventsNotShown);
        }
    }

    private void setEvent(TextView textView, Event event) { // Method to set the event for a TextView
        textView.setText(event.getName());
        textView.setVisibility(View.VISIBLE);
    }

    private void hideEvent(TextView tv) {
        tv.setVisibility(View.INVISIBLE);
    } // Method to hide a TextView
}
