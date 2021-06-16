package com.threedev.datestreak.data.model;

import androidx.annotation.Nullable;

public class DateStreakModel {

    @Nullable
    String date,streak;


    public DateStreakModel(){

    }

    public DateStreakModel(String date, String streak) {
        this.date = date;
        this.streak = streak;
    }

    @Nullable
    public String getDate() {
        return date;
    }

    public void setDate(@Nullable String date) {
        this.date = date;
    }

    @Nullable
    public String getStreak() {
        return streak;
    }

    public void setStreak(@Nullable String streak) {
        this.streak = streak;
    }
}
