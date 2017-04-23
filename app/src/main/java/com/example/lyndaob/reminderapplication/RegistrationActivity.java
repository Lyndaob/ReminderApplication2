package com.example.lyndaob.reminderapplication;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyndaob.reminderapplication.models.User;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegistrationActivity";
    TextView tvEmail, tvPassword, tvConfirmPassword, tvName, tvPhone;
    View registrationForm;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        mAuth = FirebaseAuth.getInstance();
        Button RegisterButton = (Button) findViewById(R.id.register_btn);
        registrationForm = findViewById(R.id.registration_form);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        RegisterButton.setOnClickListener(this);

        tvEmail = (TextView) findViewById(R.id.email);
        tvPassword = (TextView) findViewById(R.id.password);
        tvConfirmPassword = (TextView) findViewById(R.id.confirm_password);
        tvPhone = (TextView) findViewById(R.id.phone);
        tvName = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:

                String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();
                String confirmedPassword = tvConfirmPassword.getText().toString();
                if (!password.equals(confirmedPassword)) {
                    Toast.makeText(this, "The two passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                registrationForm.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                createUserWithEmail(email, password);
                break;
        }
    }

    private void createUserWithEmail(final String email, String password) {
        Toast.makeText(this, "Registering User...", Toast.LENGTH_LONG).show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            registrationForm.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            Log.w(TAG, "RegisterWithEmail:failed", task.getException());
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                Log.d(TAG, "onComplete: " + e.getMessage(), e);
                                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        Log.d(TAG, "onComplete: Registration successful");
                        String name = tvName.getText().toString();
                        String phoneNumber = tvPhone.getText().toString();
                        User user = new User(name, email, phoneNumber);
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        database.child("users").child(task.getResult().getUser().getUid()).setValue(user);
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
