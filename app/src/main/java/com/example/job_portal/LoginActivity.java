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

        edtEmail = findViewById(R.id.emailEditText);
        edtPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Login button click
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(email, password);
        });

        // Register button click → open RegisterActivity
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        // 🔧 Change this to your local XAMPP or live server IP address
        String url = "http://10.0.2.16/job-portal/api/login_api.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    switch (response.trim()) {
                        case "success":
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show();

                            // Go to next activity after login (optional)
                            // startActivity(new Intent(this, DashboardActivity.class));
                            // finish();
                            break;

                        case "invalid_credentials":
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
                            break;

                        case "missing_parameters":
                            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
                            break;

                        default:
                            Toast.makeText(this, "Unexpected response: " + response, Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(this, "Network error: " + error.getMessage(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        startActivity(new Intent(this, JobListActivity.class));


        queue.add(request);
    }
}
