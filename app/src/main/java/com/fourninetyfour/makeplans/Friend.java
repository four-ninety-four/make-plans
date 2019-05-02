package com.fourninetyfour.makeplans;

class Friend {

    private String friendFirstName;
    private String friendLastName;
    private String friendEmail;
    private String friendCity;
    private String friendState;
    private String friendImage;

    public Friend() {
    }

    public Friend(String friendFirstName, String friendLastName, String friendEmail, String friendCity, String friendState, String friendImage) {
        this.friendFirstName = friendFirstName;
        this.friendLastName = friendLastName;
        this.friendEmail = friendEmail;
        this.friendCity = friendCity;
        this.friendState = friendState;
        this.friendImage = friendImage;
    }

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public String getfriendCity() {
        return friendCity;
    }

    public void setFriendCity(String friendCity) {
        this.friendCity = friendCity;
    }

    public String getFriendState() {
        return friendState;
    }

    public void setFriendState(String friendState) {
        this.friendState = friendState;
    }

    public String getFriendImage() {
        return friendImage;
    }

    public String setFriendImage(String friendImage) {
        this.friendImage = friendImage;
    }
}
