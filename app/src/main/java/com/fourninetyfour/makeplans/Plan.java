package com.fourninetyfour.makeplans;

public class Plan {

    private String documentID;
    private String userID, userName;
    private String title, description, start, end, location, image;
    private String isHidden;

    public Plan() {
    }

    public Plan(String documentID, String userid,String userName, String title, String description, String start, String end, String location, String image, String isHidden) {
        this.documentID = documentID;
        this.userID = userid;
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
        this.image = image;
        this.isHidden = isHidden;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
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
