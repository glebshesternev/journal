package ru.ok.journal.remote.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RMIComment implements Serializable {
    private long id;
    private long postId;
    private String text;

    public RMIComment(long id, long postId, String text) {
        this.id = id;
        this.postId = postId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return "(id: " + this.id + ") (postId: " + this.postId + ") (text: " + this.text + ")";
    }
}