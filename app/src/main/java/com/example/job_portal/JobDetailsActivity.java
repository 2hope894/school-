package com.example.job_portal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    TextView detailCompanyName, detailTitle, detailCategory, detailRegion, detailDescription,
            detailRequirements, detailLocation, detailSalary,
            detailPosted, detailExpiry, detailMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // ✅ Initialize views
        detailCompanyName = findViewById(R.id.detailCompanyName);
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

        // ✅ Get data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detailCompanyName.setText("Company: " + extras.getString("company_name"));
            detailTitle.setText("Title: " + extras.getString("title"));
            detailCategory.setText("Category: " + extras.getString("Category"));
            detailRegion.setText("Region: " + extras.getString("Region"));
            detailDescription.setText("Description:\n" + extras.getString("description"));
            detailRequirements.setText("Requirements:\n" + extras.getString("requirements"));
            detailLocation.setText("Location: " + extras.getString("location"));
            detailSalary.setText("Salary Range: " + extras.getString("SalaryRange"));
            detailPosted.setText("Posted: " + extras.getString("postedDate"));
            detailExpiry.setText("Expires: " + extras.getString("ExpiryDate"));
            detailMoreInfo.setText("More Information: " + extras.getString("moreinformation"));
        }
    }
}
