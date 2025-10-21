package com.example.job_portal;

public class Job {
    private String company_name;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String SalaryRange;
    private String postedDate;
    private String ExpiryDate;
    private String moreInformation;
    private String Region;
    private String Category;

    // ✅ Constructor including company_name
    public Job(String company_name, String title, String description, String requirements,
               String location, String SalaryRange, String postedDate, String ExpiryDate,
               String moreInformation, String Region, String Category) {
        this.company_name = company_name;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.SalaryRange = SalaryRange;
        this.postedDate = postedDate;
        this.ExpiryDate = ExpiryDate;
        this.moreInformation = moreInformation;
        this.Region = Region;
        this.Category = Category;
    }

    // ✅ Getters
    public String getCompany_name() {
        return company_name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getLocation() {
        return location;
    }

    public String getSalaryrange() {
        return SalaryRange;
    }

    public String getPosteddate() {
        return postedDate;
    }

    public String getExpirydate() {
        return ExpiryDate;
    }

    public String getMoreinformation() {
        return moreInformation;
    }

    public String getRegion() {
        return Region;
    }

    public String getCategory() {
        return Category;
    }
}
