package com.example.lyndaob.reminderapplication.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * ReminderApplication2
 * Michael Obi
 * 17 04 2017 2:58 PM
 */
@IgnoreExtraProperties
public class Reminder {

    public boolean alarm;
    public String category;
    public String dueDate;
    public String repeat;
    public String title;

    public Reminder() {
    }

    public Reminder(String title, String category, String dueDate, boolean alarm, String repeat) {
        this.alarm = alarm;
        this.category = category;
        this.dueDate = dueDate;
        this.repeat = repeat;
        this.title = title;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("alarm", alarm);
        map.put("category", category);
        map.put("dueDate", dueDate);
        map.put("repeat", repeat);
        map.put("title", title);
        return map;
    }
}
