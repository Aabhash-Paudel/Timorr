package com.example.timorr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private ImageView homeIcon;
    private ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        homeIcon = findViewById(R.id.imageView);
        profileIcon = findViewById(R.id.imageView2);

        // Set up navigation
        setupNavigation();

        // Handle back button
        setupBackPressedHandler();
    }

    private void setupNavigation() {
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish(); // Close this activity
        });

        profileIcon.setOnClickListener(v -> {
            // Already in profile, do nothing or refresh
        });
    }

    private void setupBackPressedHandler() {
        // Add a custom callback for the back button
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to the MainActivity
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish(); // Close the current activity
            }
        });
    }
}
