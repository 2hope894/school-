package com.example.job_portal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        edtEmail = findViewById(R.id.emailEditText);
        edtPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Handle Login button click
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(email, password);
        });

        // Handle Register button click
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(String email, String password) {
        String url = "http://10.110.6.58/hope/job-portal/api/login_api.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    String res = response.trim();
                    if (res.equalsIgnoreCase("success")) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // ✅ Navigate to JobListActivity immediately after success
                        Intent intent = new Intent(LoginActivity.this, JobListActivity.class);
                        startActivity(intent);
                        //finish(); // prevent returning to login with back button
                    } else if (res.equalsIgnoreCase("invalid_credentials")) {
                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
                    } else if (res.equalsIgnoreCase("missing_parameters")) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Unexpected response: " + res, Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Network error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        queue.add(request);
    }
}
