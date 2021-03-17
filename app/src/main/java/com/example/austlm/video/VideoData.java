package com.example.austlm.video;

public class VideoData {

    private String videoTitle, videoUri;

    public VideoData() {
    }

    public VideoData(String videoTitle, String videoUri) {
        this.videoTitle = videoTitle;
        this.videoUri = videoUri;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }
}
