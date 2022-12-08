package com.moutamid.e_learningapp.Models;

public class CourseIDs {
    String ID;
    boolean enroll;
    String sellerID;

    public CourseIDs() {
    }

    public CourseIDs(String ID, boolean enrolled, String sellerID) {
        this.ID = ID;
        this.enroll = enrolled;
        this.sellerID = sellerID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isEnrolled() {
        return enroll;
    }

    public void setEnrolled(boolean enrolled) {
        this.enroll = enrolled;
    }
}
