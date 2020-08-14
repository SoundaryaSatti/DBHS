package com.integro.dbhs.model;

import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("v_id")
    private String videoId;

    @SerializedName("updated_at")
    private String updatedAt;

    private String id;

    private String title;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



