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

    /**
     * Get Post Date Method
     * @return
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * Set the Post Date Method
     * @param postDate
     */

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * Get Post Content Method
     * @return
     */

    public String getPostContent() {
        return postContent;
    }

    /**
     * Set Post Content Method
     * @param postContent
     */

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
