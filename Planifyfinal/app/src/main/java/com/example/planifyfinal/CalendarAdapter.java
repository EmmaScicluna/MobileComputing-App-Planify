package com.example.planifyfinal;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

// Adapter class for the calendar cells
public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<LocalDate> days; // List of days
    private final OnItemListener onItemListener; // Listener for the calendar cells

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) { // Constructor for the adapter
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override // Create a new view holder
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // Inflate the layout for the calendar cell
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        // Set the height of the view based on the number of days in the month
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15){
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        } else {
            layoutParams.height = (int) parent.getHeight();
        }

        //Return  a new ViewHolder
        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override // Method to bind data to a ViewHolder
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        final LocalDate date = days.get(position); // Get the date for the current position

        // Set the text of the dayOfMonth TextView to the day of the month
        holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));

        // If the date is the selected date, change the background color of the parent view
        if(date.equals(CalendarUtils.selectedDate)) {
            holder.parentView.setBackgroundColor(Color.LTGRAY);
        }

        // If the month of the date is the same as the month of the selected date, set the text color to black
        // Otherwise, set the text color to light gray
        if(date.getMonth().equals(CalendarUtils.selectedDate.getMonth())) {
            holder.dayOfMonth.setTextColor(Color.BLACK);
        } else {
            holder.dayOfMonth.setTextColor(Color.LTGRAY);
        }
        //holder.dayOfMonth.setText(date.getDayOfMonth());
    }

    @Override // Method to get the number of items in the adapter
    public int getItemCount() {

        return days.size();
    }

    public interface OnItemListener { // Interface for the item click listener
        void onItemClick(int position, LocalDate date);
    }
}
