package com.example.sectionrv.retrofitpractice.model;

import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    //if the json key and var name is differ so we use SerializedName
    @SerializedName("body")
    private String text;


    //for image upload
    private String message;
    private String path;

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }


    public Posts(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
