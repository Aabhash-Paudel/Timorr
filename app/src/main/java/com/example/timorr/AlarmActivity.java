package com.example.timorr;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> alarmList;
    private FloatingActionButton fabAddAlarm;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Initialize views
        recyclerView = findViewById(R.id.rvAlarms);
        fabAddAlarm = findViewById(R.id.fabAddAlarm);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Setup RecyclerView
        setupRecyclerView();

        // Setup FAB
        fabAddAlarm.setOnClickListener(v -> {
            addNewAlarm();
        });

        // Setup Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_alarm) {
                Toast.makeText(this, "Alarm selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_clock) {
                Toast.makeText(this, "Clock selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_stopwatch) {
                Toast.makeText(this, "Stopwatch selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_timer) {
                Toast.makeText(this, "Timer selected", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false;
            }
        });



    }

    private void setupRecyclerView() {
        // Initialize alarm list
        alarmList = new ArrayList<>();
        alarmList.add(new Alarm("6:30 AM", "Every day | Wake up", true));
        alarmList.add(new Alarm("8:00 AM", "Ring once | Morning meeting", false));
        alarmList.add(new Alarm("10:22 AM", "Ring once", true));

        // Set up adapter and RecyclerView
        alarmAdapter = new AlarmAdapter(alarmList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(alarmAdapter);
    }

    private void addNewAlarm() {
        // Add a new alarm (dummy example)
        alarmList.add(new Alarm("12:00 PM", "Ring once | New Alarm", false));
        alarmAdapter.notifyItemInserted(alarmList.size() - 1);
        recyclerView.scrollToPosition(alarmList.size() - 1);
        Toast.makeText(this, "New alarm added", Toast.LENGTH_SHORT).show();
    }
}
