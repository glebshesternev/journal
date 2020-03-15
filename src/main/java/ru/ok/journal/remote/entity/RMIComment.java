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

    @Override
    public String toString(){
        return "(id: " + this.id + ") (postId: " + this.postId + ") (text: " + this.text + ")";
    }
}