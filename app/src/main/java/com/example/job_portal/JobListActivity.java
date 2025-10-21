package com.example.job_portal;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView emptyText;
    ArrayList<Job> jobList;
    JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        recyclerView = findViewById(R.id.jobsRecyclerView);
        emptyText = findViewById(R.id.emptyText);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobList = new ArrayList<>();
        adapter = new JobAdapter(this, jobList);
        recyclerView.setAdapter(adapter);

        fetchJobs();
    }

    private void fetchJobs() {
        // ✅ Use your correct local IP
        String url = "http://10.110.6.58/hope/job-portal/api/fetch_jobs.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("JobListActivity", "Response: " + response.toString());
                    if (response.length() == 0) {
                        emptyText.setVisibility(TextView.VISIBLE);
                        recyclerView.setVisibility(RecyclerView.GONE);
                    } else {
                        parseJobs(response);
                    }
                },
                error -> {
                    Log.e("JobListActivity", "Volley Error: ", error);
                    Toast.makeText(this, "Error fetching jobs. Check your connection or API.", Toast.LENGTH_LONG).show();
                    emptyText.setVisibility(TextView.VISIBLE);
                });

        queue.add(request);
    }

    private void parseJobs(JSONArray response) {
        try {
            jobList.clear();
            for (int i = 0; i < response.length(); i++) {
                JSONObject jobObj = response.getJSONObject(i);

                // ✅ Match field names from your PHP/DB exactly
                String company_name = jobObj.optString("company_name");
                String title = jobObj.optString("title");
                String description = jobObj.optString("description");
                String requirements = jobObj.optString("requirements");
                String location = jobObj.optString("location");
                String SalaryRange = jobObj.optString("SalaryRange");
                String postedDate = jobObj.optString("postedDate");
                String ExpiryDate = jobObj.optString("ExpiryDate");
                String moreinformation = jobObj.optString("moreinformation");
                String Region = jobObj.optString("Region");
                String Category = jobObj.optString("Category");

                jobList.add(new Job(
                        company_name,
                        title,
                        description,
                        requirements,
                        location,
                        SalaryRange,
                        postedDate,
                        ExpiryDate,
                        moreinformation,
                        Region,
                        Category
                ));
            }

            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(RecyclerView.VISIBLE);
            emptyText.setVisibility(TextView.GONE);

        } catch (Exception e) {
            Log.e("JobListActivity", "JSON Parsing Error: ", e);
            Toast.makeText(this, "Error parsing job data.", Toast.LENGTH_SHORT).show();
        }
    }
}
