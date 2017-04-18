package com.example.lyndaob.reminderapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.example.lyndaob.reminderapplication.databinding.NewReminderBinding;
import com.example.lyndaob.reminderapplication.models.Reminder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewReminderActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    private NewReminderBinding newReminderBinding;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newReminderBinding = DataBindingUtil.setContentView(this, R.layout.new_reminder);
        database = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] items = getResources().getStringArray(R.array.reminder_categories);
        ArrayAdapter<String> reminderCategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        newReminderBinding.category.setAdapter(reminderCategoryAdapter);
        String[] repeatSettings = getResources().getStringArray(R.array.repeat_settings);
        ArrayAdapter<String> repeatSettingsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, repeatSettings);
        newReminderBinding.repeat.setAdapter(repeatSettingsAdapter);
    }


    public void onDueDateClick(View view) {
        final Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (!newReminderBinding.dueDate.getText().toString().isEmpty()) {
            String[] dateParts = newReminderBinding.dueDate.getText().toString().split("-");
            currentYear = Integer.parseInt(dateParts[0]);
            currentMonth = Integer.parseInt(dateParts[1]) - 1;
            currentDay = Integer.parseInt(dateParts[2]);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    public void onSaveButtonClick(View view) {
        final String title = newReminderBinding.title.getText().toString();
        final String repeatSetting = newReminderBinding.repeat.getSelectedItem().toString();
        final String category = newReminderBinding.category.getSelectedItem().toString();
        final boolean alarm = newReminderBinding.alarm.isChecked();
        final String dueDate = newReminderBinding.dueDate.getText().toString();
        Reminder reminder = new Reminder(title, category, dueDate, alarm, repeatSetting);
        addReminder(reminder);
    }

    private void addReminder(Reminder reminder) {
        String key = database.child("reminders").push().getKey();
        Map<String, Object> values = reminder.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/reminders/" + key, values);
        childUpdates.put("/user-reminders/" + getFirebaseUserId() + "/" + key, values);
        database.updateChildren(childUpdates);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        newReminderBinding.dueDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
    }
}
