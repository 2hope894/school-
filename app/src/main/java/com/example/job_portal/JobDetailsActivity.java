package com.example.job_portal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    TextView detailTitle, detailCategory, detailRegion, detailDescription,
            detailRequirements, detailLocation, detailSalary,
            detailPosted, detailExpiry, detailMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // Initialize views
        detailTitle = findViewById(R.id.detailTitle);
        detailCategory = findViewById(R.id.detailCategory);
        detailRegion = findViewById(R.id.detailRegion);
        detailDescription = findViewById(R.id.detailDescription);
        detailRequirements = findViewById(R.id.detailRequirements);
        detailLocation = findViewById(R.id.detailLocation);
        detailSalary = findViewById(R.id.detailSalary);
        detailPosted = findViewById(R.id.detailPosted);
        detailExpiry = findViewById(R.id.detailExpiry);
        detailMoreInfo = findViewById(R.id.detailMoreInfo);

        // Get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detailTitle.setText(extras.getString("title"));
            detailCategory.setText("Category: " + extras.getString("category"));
            detailRegion.setText("Region: " + extras.getString("region"));
            detailDescription.setText("Description:\n" + extras.getString("description"));
            detailRequirements.setText("Requirements:\n" + extras.getString("requirements"));
            detailLocation.setText("Location: " + extras.getString("location"));
            detailSalary.setText("Salary Range: " + extras.getString("salaryrange"));
            detailPosted.setText("Posted: " + extras.getString("posteddate"));
            detailExpiry.setText("Expires: " + extras.getString("expirydate"));
            detailMoreInfo.setText("More Information: " + extras.getString("moreinformation"));
        }
    }
}
