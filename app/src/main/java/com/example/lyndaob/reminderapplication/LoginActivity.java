package com.example.lyndaob.reminderapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import static android.R.attr.id;
import static com.example.lyndaob.reminderapplication.R.id.button;

public class LoginActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView reg;

        Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(loginIntent);
            }
        });

        reg = (TextView) findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }





}





   //b.setOnClickListener(new View.OnClickListener() {
       // @Override

        // Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        //    //    startActivity(intent);



         //   }



























