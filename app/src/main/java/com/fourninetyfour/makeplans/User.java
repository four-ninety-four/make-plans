package com.fourninetyfour.makeplans;

public class User {

    private String userID;
    private String first, last;
    private String email, phone;
    private String city, state;
    private String image;

    public User() {
    }

    public User(String userID, String first, String last, String email, String image, String city, String state, String phone) {
        this.userID = userID;
        this.first = first;
        this.last = last;
        this.email = email;
        this.image = image;
        this.city = city;
        this.state = state;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
