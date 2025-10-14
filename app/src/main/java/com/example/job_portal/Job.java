package com.example.job_portal;

public class Job {
    private String title, description, requirements, location, salaryrange, posteddate, expirydate, moreinformation, region, category;

    public Job(String title, String description, String requirements, String location,
               String salaryrange, String posteddate, String expirydate,
               String moreinformation, String region, String category) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryrange = salaryrange;
        this.posteddate = posteddate;
        this.expirydate = expirydate;
        this.moreinformation = moreinformation;
        this.region = region;
        this.category = category;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getRequirements() { return requirements; }
    public String getLocation() { return location; }
    public String getSalaryrange() { return salaryrange; }
    public String getPosteddate() { return posteddate; }
    public String getExpirydate() { return expirydate; }
    public String getMoreinformation() { return moreinformation; }
    public String getRegion() { return region; }
    public String getCategory() { return category; }
}
