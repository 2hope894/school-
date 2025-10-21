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

        // ✅ Show company name in the job item
        holder.txtCompanyName.setText(job.getCompany_name());
        holder.txtJobTitle.setText(job.getTitle());
        holder.txtLocation.setText("Location: " + job.getLocation());
        holder.txtLocation.setText("Location: " + job.getLocation());
        holder.txtSalary.setText("Salary: " + job.getSalaryrange());
        holder.txtPostedDate.setText("Posted: " + job.getPosteddate());
        holder.txtCategoryRegion.setText("Category: "+job.getCategory()+"    "+"Region: "+job.getRegion());
        holder.txtShortDescription.setText("Description: "+job.getDescription());

        // ✅ Handle click to open details activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, JobDetailsActivity.class);
            intent.putExtra("company_name", job.getCompany_name());
            intent.putExtra("title", job.getTitle());
            intent.putExtra("description", job.getDescription());
            intent.putExtra("requirements", job.getRequirements());
            intent.putExtra("location", job.getLocation());
            intent.putExtra("SalaryRange", job.getSalaryrange());
            intent.putExtra("postedDate", job.getPosteddate());
            intent.putExtra("ExpiryDate", job.getExpirydate());
            intent.putExtra("moreinformation", job.getMoreinformation());
            intent.putExtra("Region", job.getRegion());
            intent.putExtra("Category", job.getCategory());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // ✅ Updated ViewHolder with company_name
    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView txtCompanyName, txtJobTitle, txtLocation, txtSalary,
                txtPostedDate, txtCategoryRegion, txtShortDescription;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            txtJobTitle = itemView.findViewById(R.id.txtJobTitle);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            txtPostedDate = itemView.findViewById(R.id.txtPostedDate);
            txtCategoryRegion = itemView.findViewById(R.id.txtCategoryRegion);
            txtShortDescription = itemView.findViewById(R.id.txtShortDescription);
        }
    }
}
