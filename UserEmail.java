package com.example.gmailmodule;

public class UserEmail {
    int userID;
    String userName;
    String emailDescription;
    String emailContent;
    String emailSubject;
    boolean isFavourite;

    public UserEmail(){

    }

    public UserEmail(int userID, String userName, String emailSubject, String emailDescription, String emailContent){
        this.userID = userID;
        this.emailDescription = emailDescription;
        this.userName = userName;
        this.emailContent = emailContent;
        this.emailSubject = emailSubject;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailDescription() {
        return emailDescription;
    }

    public void setEmailDescription(String emailDescription) {
        this.emailDescription = emailDescription;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isFavoriteItem(String searchText){
        if (this.isFavourite){
            return searchContent(searchText);
        }
        return false;
    }

    public boolean searchContent(String searchText){
        if(this.userName.contains(searchText))
            return true;

        if(this.emailContent.contains(searchText))
            return true;

        if(this.emailSubject.contains(searchText))
            return true;

        if(this.emailDescription.contains(searchText))
            return true;

        return false;
    }
}
