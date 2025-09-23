package com.example.job_portal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText Full_Name = findViewById(R.id.nameInput);
        EditText Email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);
        EditText Confirm_Password = findViewById(R.id.confirmpasswordInput);

        submit_button = findViewById(R.id.signupButton);

        // Password regex for strong validation
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).+$";

        // Real-time validation for Full Name
        Full_Name.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    Full_Name.setError("Full name is required");
                } else {
                    Full_Name.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Real-time validation for Email
        Email.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = s.toString().trim();
                if (emailText.isEmpty()) {
                    Email.setError("Email is required");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    Email.setError("Enter a valid email");
                } else {
                    Email.setError(null);
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

        // Real-time validation for Confirm Password
        Confirm_Password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = password.getText().toString().trim();
                String confirmPass = s.toString().trim();
                if (!confirmPass.equals(pass)) {
                    Confirm_Password.setError("Passwords do not match");
                } else {
                    Confirm_Password.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Final validation when clicking the button
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = Full_Name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confirmPass = Confirm_Password.getText().toString().trim();

                if (Full_Name.getError() != null ||
                        Email.getError() != null ||
                        password.getError() != null ||
                        Confirm_Password.getError() != null) {
                    Toast.makeText(RegisterActivity.this, "Fix errors before continuing", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fullName.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If all validations pass
                Toast.makeText(RegisterActivity.this, "Registration Successful for " + fullName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
