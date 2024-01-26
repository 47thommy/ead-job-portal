package com.jobportal.job;

import java.sql.Timestamp;

public class Candidate {
    private String email;
    private Timestamp appliedTime;
    private String resumeUrl;

    

    public Candidate(String email, Timestamp appliedTime, String resumeUrl) {
        this.email = email;
        this.appliedTime = appliedTime;
        this.resumeUrl = resumeUrl;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getAppliedTime() {
        return appliedTime;
    }

    public void setAppliedTime(Timestamp appliedTime) {
        this.appliedTime = appliedTime;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
}
