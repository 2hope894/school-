package com.example.job_portal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput, confirmPasswordInput;
    Button signupButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Link XML elements
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmpasswordInput);

        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);

        // Handle Sign Up button click
        signupButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Basic input validation
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
                return;
            }

            // For now, just show success message
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

            // Optionally, clear fields after signup
            nameInput.setText("");
            emailInput.setText("");
            passwordInput.setText("");
            confirmPasswordInput.setText("");
        });

        // Handle Login button click
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: close register screen
        });
    }
}
