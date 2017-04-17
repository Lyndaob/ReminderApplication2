package com.example.lyndaob.reminderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button ReminderButton = (Button) findViewById(R.id.remBtn);
        Button TimerButton = (Button) findViewById(R.id.TimeBtn);
        Button AboutButton = (Button) findViewById(R.id.abtBtn);
        Button NoteButton = (Button) findViewById(R.id.NteBtn);
        Button PeerTalkButton = (Button) findViewById(R.id.shareBtn);

        ReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewReminderActivity.class);
                startActivity(intent);
            }
        });

        TimerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, TimerActivity.class);
                startActivity(intent1);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }));
        AboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        NoteButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent1);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }));
        PeerTalkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PeerTalkActivity.class);
                startActivity(intent);
            }
        });
    }
}

