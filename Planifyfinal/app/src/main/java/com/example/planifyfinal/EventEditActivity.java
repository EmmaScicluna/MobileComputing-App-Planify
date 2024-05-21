package com.example.planifyfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET; // EditText widget for event name
    private TextView eventDateTV, eventTimeTV; // TextView widgets for event date and time

    private LocalTime time; // LocalTime object for event time

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate method for the activity event edit
        super.onCreate(savedInstanceState); // Calling the super class onCreate method
        setContentView(R.layout.activity_event_edit); // Seting the content view to activity_event_edit
        initWidgets(); // Initializing widgets
        time = LocalTime.now(); // Setting time to current time

        // Set the date and time text views to the selected date and current time
        eventDateTV.setText("Date:" + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time:" + CalendarUtils.formattedTime(time));
    }

    private void initWidgets() { // Method to initialize widgets
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view) { // Method to save the event
        String eventName = eventNameET.getText().toString(); //Getting the event name from the EditText
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time); // Creating a new event with the entered name, selected date, and current time
        Event.eventsList.add(newEvent); // Adding the event to the events list
        finish(); // Closing the activity
    }
}