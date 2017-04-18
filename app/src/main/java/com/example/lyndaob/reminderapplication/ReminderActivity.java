package com.example.lyndaob.reminderapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.lyndaob.reminderapplication.adapter.ReminderListAdapter;
import com.example.lyndaob.reminderapplication.databinding.ActivityReminderBinding;
import com.example.lyndaob.reminderapplication.models.Reminder;

public class ReminderActivity extends BaseActivity {
    ActivityReminderBinding viewBinding;
    ReminderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_reminder);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewBinding.rvReminders.setHasFixedSize(false);
        viewBinding.rvReminders.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("reminders");
        adapter = new ReminderListAdapter(Reminder.class, R.layout.reminder_list_item, ReminderListAdapter.ViewHolder.class, database);

        viewBinding.rvReminders.setAdapter(adapter);
    }

    public void onNewButtonClick(View view) {
        startActivity(new Intent(this, NewReminderActivity.class));
    }

}
