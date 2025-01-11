package com.example.timorr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare UI elements
    private Button dateButton;
    private Button profileButton;
    private TextView timeTextView;
    private Switch workSwitch;
    private Switch alarmSwitch;
    private Switch polyPhaseSwitch;
    private ImageView homeIcon;
    private ImageView profileIcon;
    private  TextView alarm;

    // Handler for updating time
    private final Handler timeHandler = new Handler();
    private boolean isTimeUpdating = false;

    // Runnable for updating time every second
    private final Runnable timeUpdater = new Runnable() {
        @Override
        public void run() {
            updateDateTime();
            timeHandler.postDelayed(this, 1000); // Run again in 1 second
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Add null check before accessing ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
        // Initialize UI elements
        initializeViews();
        setupClickListeners();
        startTimeUpdates();
    }

    private void initializeViews() {
        dateButton = findViewById(R.id.date_button);
        profileButton = findViewById(R.id.profile_button);
        timeTextView = findViewById(R.id.textView4);
        workSwitch = findViewById(R.id.work_switch);
        alarmSwitch = findViewById(R.id.alarm_switch);
        polyPhaseSwitch = findViewById(R.id.pol_switch);
        homeIcon = findViewById(R.id.imageView);
        profileIcon = findViewById(R.id.imageView2);
        alarm=findViewById(R.id.Alarm);
    }

    private void setupClickListeners() {
        // Date button click listener
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateTime();
            }
        });

        // Profile button click listener
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "User Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // Switch listeners
        workSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(MainActivity.this, "Work scheduler " + status, Toast.LENGTH_SHORT).show();
        });

        alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(MainActivity.this, "Alarm " + status, Toast.LENGTH_SHORT).show();
        });

        polyPhaseSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(MainActivity.this, "Poly-phase " + status, Toast.LENGTH_SHORT).show();
        });

        // Navigation icons click listeners
        homeIcon.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        profileIcon.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        alarm.setOnClickListener(v ->{
            Toast.makeText(MainActivity.this, "Alarm", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        });
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        timeTextView.setText(currentTime);

        SimpleDateFormat fullDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String currentDate = fullDateFormat.format(new Date());
        dateButton.setText(currentDate);
    }

    private void startTimeUpdates() {
        if (!isTimeUpdating) {
            isTimeUpdating = true;
            timeHandler.post(timeUpdater);
        }
    }

    private void stopTimeUpdates() {
        isTimeUpdating = false;
        timeHandler.removeCallbacks(timeUpdater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimeUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimeUpdates();
    }

    @Override
    protected void onDestroy() {//memory leak nahos vanera app stop huda memory free gardinxa
        super.onDestroy();
        stopTimeUpdates();
    }
}