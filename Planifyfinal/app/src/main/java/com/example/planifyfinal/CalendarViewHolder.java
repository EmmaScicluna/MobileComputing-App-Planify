package com.example.planifyfinal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

// ViewHolder class for the calendar cells
public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ArrayList<LocalDate> days; // List of days in the month
    public final View parentView; // The parent view of the cell
    public final TextView dayOfMonth; // The text view for the day of the month
    private final CalendarAdapter.OnItemListener onItemListener; // Listener for when an item is clicked

    // Constructor for the CalendarViewHolder class
    public CalendarViewHolder( @NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);

        // Initialize the parent view and the text view for the day of the month
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener; // Set the onItemListener
        itemView.setOnClickListener(this); // Set the click listener for the itemView
        this.days = days; // Set the list of days in the month
    }

    @Override // Method for handling the click event
    public void onClick(View view) {
        // Call the onItemClick method of the item click listener
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
