package com.example.job_portal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private Context context;
    private List<Job> jobList;

    public JobAdapter(Context context, List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);

        holder.txtJobTitle.setText(job.getTitle());
        holder.txtLocation.setText("Location: " + job.getLocation());
        holder.txtSalary.setText("Salary: " + job.getSalaryrange());
        holder.txtPostedDate.setText("Posted: " + job.getPosteddate());
        holder.txtCategoryRegion.setText(job.getCategory() + " • " + job.getRegion());
        holder.txtShortDescription.setText(job.getDescription());

        // ✅ Handle click to open details activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, JobDetailsActivity.class);
            intent.putExtra("title", job.getTitle());
            intent.putExtra("description", job.getDescription());
            intent.putExtra("requirements", job.getRequirements());
            intent.putExtra("location", job.getLocation());
            intent.putExtra("salaryrange", job.getSalaryrange());
            intent.putExtra("posteddate", job.getPosteddate());
            intent.putExtra("expirydate", job.getExpirydate());
            intent.putExtra("moreinformation", job.getMoreinformation());
            intent.putExtra("region", job.getRegion());
            intent.putExtra("category", job.getCategory());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // ✅ Updated ViewHolder
    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView txtJobTitle, txtLocation, txtSalary, txtPostedDate, txtCategoryRegion, txtShortDescription;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJobTitle = itemView.findViewById(R.id.txtJobTitle);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            txtPostedDate = itemView.findViewById(R.id.txtPostedDate);
            txtCategoryRegion = itemView.findViewById(R.id.txtCategoryRegion);
            txtShortDescription = itemView.findViewById(R.id.txtShortDescription);
        }
    }
}
