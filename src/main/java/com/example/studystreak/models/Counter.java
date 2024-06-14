package com.example.studystreak.models;

import java.io.Serializable;
import java.util.Date;

public class Counter implements Serializable {

    private int currentStreak;
    private int totalQst;
    private int dayQst;
    private Date date;

    public Counter() {
    }

    public Counter(int currentStreak, int totalQst, int dayQst, Date date) {
        this.currentStreak = currentStreak;
        this.totalQst = totalQst;
        this.dayQst = dayQst;
        this.date = date;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getTotalQst() {
        return totalQst;
    }

    public void setTotalQst(int totalQst) {
        this.totalQst = totalQst;
    }

    public int getDayQst() {
        return dayQst;
    }

    public void setDayQst(int dayQst) {
        this.dayQst = dayQst;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
