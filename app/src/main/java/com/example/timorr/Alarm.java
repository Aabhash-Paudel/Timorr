package com.example.timorr;

public class Alarm {
    private String time;
    private String description;
    private boolean isEnabled;

    public Alarm(String time, String description, boolean isEnabled) {
        this.time = time;
        this.description = description;
        this.isEnabled = isEnabled;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
