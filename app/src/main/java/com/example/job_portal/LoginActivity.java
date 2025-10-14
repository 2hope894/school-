package com.example.job_portal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView registerTextView;

    // Hardcoded credentials for testing
    private final String testEmail = "student@example.com";
    private final String testPassword = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.emailEditText);
        edtPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);


        // Login validation
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.equals(testEmail) && password.equals(testPassword)) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
            }
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}
