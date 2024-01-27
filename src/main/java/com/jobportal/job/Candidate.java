package com.jobportal.job;

import java.sql.Timestamp;

public class Candidate {
    private String email;
    private Timestamp appliedTime;
    private String resumeUrl;
    private String phone;
    private String cover;



    public Candidate(String email, Timestamp appliedTime, String resumeUrl, String phone, String cover) {
        this.email = email;
        this.appliedTime = appliedTime;
        this.resumeUrl = resumeUrl;
        this.phone=phone;
        this.cover=cover;    }



    public String getEmail() {
        return email;
    }
    public String getCover() {
        return cover;
    }
    public String getPhone() {
        return phone;
    }
    public String setPhone() {
        return phone;
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
