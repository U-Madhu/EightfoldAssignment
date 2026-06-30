package com.eightfold.model;

public class Experience {

    private String company;
    private String title;
    private String start;
    private String end;
    private String summary;

    public Experience() {
    }

    public Experience(String company, String title, String start, String end, String summary) {
        this.company = company;
        this.title = title;
        this.start = start;
        this.end = end;
        this.summary = summary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}