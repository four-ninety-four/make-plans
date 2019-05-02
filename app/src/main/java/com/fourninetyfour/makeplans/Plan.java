package com.fourninetyfour.makeplans;

public class Plan {

    private String userID;
    private String title, description, start, end, location, image;
    private String isHidden;

    public Plan() {
    }

    public Plan(String userid, String title, String description, String start, String end, String location, String image, String isHidden) {
        this.userID = userid;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
        this.image = image;
        this.isHidden = isHidden;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsHidden() { return isHidden; }

    public void setIsHidden(String hidden) { isHidden = hidden; }
}
