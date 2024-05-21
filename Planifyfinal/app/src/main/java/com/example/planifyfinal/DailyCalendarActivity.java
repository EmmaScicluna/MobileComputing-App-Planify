package com.example.planifyfinal;

import static com.example.planifyfinal.CalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Class representing the daily calendar activity
public class DailyCalendarActivity extends AppCompatActivity {

    private TextView monthDayText; // Text view for displaying the month and day
    private TextView dayOfWeekTV; // Text view for displaying the day of the week
    private ListView hourListView; // List view for displaying the hours

    @Override // Method to create the activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);
        initWidgets(); // Initializing the widgets
    }

    private void initWidgets() { // Method to initialize the widgets
        monthDayText = findViewById(R.id.monthDayText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
    }

    @Override // Method to resume the activity
    protected void onResume() {
        super.onResume(); // Calling the super class method
        setDayView(); // Setting the day view
    }

    private void setDayView() { // Method to set the day view
        monthDayText.setText(CalendarUtils.monthDayFromDate(selectedDate)); // Setting the month and day text
        String dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()); // Set the day of the week text
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter(); // Setting the hour adapter
    }

    private void setHourAdapter() { // Method to set the hour adapter
        HourAdapter hourAdapter = new HourAdapter(getApplicationContext(), hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    // Method to get a list of HourEvent objects for each hour of the day
    private ArrayList<HourEvent> hourEventList() {
        ArrayList<HourEvent> list = new ArrayList<>();

        // Loop through all hours of the day and create an HourEvent object for each hour
        for(int hour = 0; hour < 24; hour++) {
            LocalTime time = LocalTime.of(hour, 0);

            // Getting all events for the selected date and time
            ArrayList<Event> events = Event.eventsForDateAndTime(selectedDate, time);

            // Creating an HourEvent object with the time and events for that hour
            HourEvent hourEvent = new HourEvent(time, events);
            list.add(hourEvent);
        }
        return list;
    }

    public void previousDayAction(View view) { // Method to handle the previous day action
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view) { // Method to handle the next day action
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    public void newEventAction(View view) { // Method to handle the new event action
        startActivity(new Intent(this, EventEditActivity.class));
    }
}