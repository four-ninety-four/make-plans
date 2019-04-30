package com.fourninetyfour.makeplans;

public class Plan {

    private int userid, eventType;
    private String title, description, date;
    private int image;

    public Plan(int userid, int eventType, String title, String description, int image) {
        this.userid = userid;
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return userid;
    }

    public int getEventType() {
        return eventType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }
}
