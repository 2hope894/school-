package com.example.job_portal;

import android.os.Bundle;
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
        String url = "http://10.0.2.16/job-portal/api/fetch_jobs.php"; // your server address

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    if (response.length() == 0) {
                        emptyText.setVisibility(TextView.VISIBLE);
                        recyclerView.setVisibility(RecyclerView.GONE);
                    } else {
                        parseJobs(response);
                    }
                },
                error -> {
                    Toast.makeText(this, "Error fetching jobs: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    emptyText.setVisibility(TextView.VISIBLE);
                });

        queue.add(request);
    }

    private void parseJobs(JSONArray response) {
        try {
            jobList.clear();
            for (int i = 0; i < response.length(); i++) {
                JSONObject jobObj = response.getJSONObject(i);
                jobList.add(new Job(
                        jobObj.getString("title"),
                        jobObj.getString("description"),
                        jobObj.getString("requirements"),
                        jobObj.getString("location"),
                        jobObj.getString("salaryrange"),
                        jobObj.getString("posteddate"),
                        jobObj.getString("expirydate"),
                        jobObj.getString("moreinformation"),
                        jobObj.getString("region"),
                        jobObj.getString("category")
                ));
            }

            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(RecyclerView.VISIBLE);
            emptyText.setVisibility(TextView.GONE);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error parsing job data", Toast.LENGTH_SHORT).show();
        }
    }
}
