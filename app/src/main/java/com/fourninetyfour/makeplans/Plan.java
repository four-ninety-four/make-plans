package com.fourninetyfour.makeplans;

public class Plan {

    private int userid;
    private String title, description, startDate, endDate, location, image;
    private boolean hidden;


    public Plan(int userid, String title, String description, String startDate, String endDate, String location, String image, boolean hidden) {
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.image = image;
        this.hidden = hidden;
    }

    public int getUserid() {
        return userid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public boolean isHidden() {
        return hidden;
    }
}
