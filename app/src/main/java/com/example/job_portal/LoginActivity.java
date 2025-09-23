package com.example.job_portal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.emailEditText);
        EditText password = findViewById(R.id.passwordEditText);
        submit_button = findViewById(R.id.loginButton);

        // Strong password regex: At least 1 uppercase, 1 lowercase, 1 digit, 1 special char
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).+$";

        // Real-time validation for Email
        email.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = s.toString().trim();
                if (emailText.isEmpty()) {
                    email.setError("Email is required");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    email.setError("Enter a valid email");
                } else {
                    email.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Real-time validation for Password
        password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = s.toString().trim();
                if (pass.isEmpty()) {
                    password.setError("Password is required");
                } else if (pass.length() < 6) {
                    password.setError("At least 6 characters");
                } else if (!pass.matches(passwordPattern)) {
                    password.setError("Must contain upper, lower, digit & special char");
                } else {
                    password.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Final validation on Login button click
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (email.getError() != null || password.getError() != null) {
                    Toast.makeText(LoginActivity.this, "Fix errors before continuing", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If all validations pass
                Toast.makeText(LoginActivity.this, "Login Successful for " + emailText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
