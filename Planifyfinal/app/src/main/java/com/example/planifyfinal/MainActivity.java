package com.example.planifyfinal;

import static com.example.planifyfinal.CalendarUtils.daysInMonthArray;
import static com.example.planifyfinal.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText; // TextView displaying the month and year
    private RecyclerView calendarRecyclerView; // RecyclerView for displaying the calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) { // This method is called when the activity is created
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets(); // Initializing the widgets
        CalendarUtils.selectedDate = LocalDate.now(); // Setting the selected date to the current date
        setMonthView(); // Setting the month view
    }


    private void initWidgets() { // Method to initialize the widgets
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView() { // Method to set the month view

        // Setting the text of the monthYearText to the month and year of the selected date
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));

        // Getting the days of the month
        ArrayList<LocalDate> daysInMonth = daysInMonthArray();

        // Setting the adapter for the RecyclerView
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }
    public void previousMonthAction(View view){ // Method to handle the previous month action
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view){ // Method to handle the next month action
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override // Method to handle the item click of the RecyclerView
    public void onItemClick(int position, LocalDate date) {
        if(date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    // Method to start the WeekViewActivity
    public void weeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

}