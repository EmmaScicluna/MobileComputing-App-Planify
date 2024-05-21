package com.example.planifyfinal;

import static com.example.planifyfinal.CalendarUtils.daysInMonthArray;
import static com.example.planifyfinal.CalendarUtils.daysInWeekArray;
import static com.example.planifyfinal.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText; //TextView displaying the month and year
    private RecyclerView calendarRecyclerView; //RecyclerView displaying the calendar
    private ListView eventListView; //ListView displaying the events

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate method for the activity week view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets(); //Initializing widgets
        setWeekView(); //Setting the week view
    }

    private void initWidgets() { // Method to initialize the widgets
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void setWeekView() { // Method to set the week view
        // Setting the month and year text view to the selected date
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));

        // Getting the days of the week for the selected date
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        // Setting the adapter for the RecyclerView
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

        // Setting the adapter for the events
        setEventAdapter();
    }

    // Method to go to the previous week
    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    // Method to go to the next week
    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    // Method to handle item click in the RecyclerView
    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    // Method to handle onResume lifecycle event
    @Override
    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    // Method to set the adapter for the events
    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    // Method to start the EventEditActivity
    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    // Method to start the DailyCalendarActivity
    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }
}