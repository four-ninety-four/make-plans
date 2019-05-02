package com.fourninetyfour.makeplans;

class Friend {

    private String first;
    private String last;
    private String email;
    private String city;
    private String state;
    private String image;

    public Friend() {
    }

    public Friend(String First, String friendLastName, String friendEmail, String friendCity, String friendState, String friendImage) {
        this.first = First;
        this.last = friendLastName;
        this.email = friendEmail;
        this.city = friendCity;
        this.state = friendState;
        this.image = friendImage;
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

