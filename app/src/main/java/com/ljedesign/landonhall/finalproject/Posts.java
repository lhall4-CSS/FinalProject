package com.ljedesign.landonhall.finalproject;

/**
 * Created by lhall4 on 5/11/2018.
 */

public class Posts {
    private String postDate, postContent;

    public Posts(String postContent, String postDate) {
        this.setPostContent(postContent);
        this.setPostDate(postDate);
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
