package com.moutamid.e_learningapp.Models;

public class Model_Content {
    String title;
    int image;

    public Model_Content() {
    }

    public Model_Content(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
