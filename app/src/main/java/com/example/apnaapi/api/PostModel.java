package com.example.apnaapi.api;


import java.io.Serializable;

public class PostModel implements Serializable {

    private int id;
    private String Title, created_at, Description, PostImage;


    public PostModel(String title, String description, String postImage) {
        Title = title;
        Description = description;
        PostImage = postImage;
    }

    public PostModel(int id, String title, String description, String postImage) {
        this.id = id;
        Title = title;
        Description = description;
        PostImage = postImage;
    }

    public PostModel(int id, String title, String description) {
        this.id = id;
        Title = title;
        Description = description;
    }

    public PostModel(String title, String description) {
        Title = title;
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPostImage() {
        return PostImage;
    }

    public void setPostImage(String postImage) {
        PostImage = postImage;
    }
}