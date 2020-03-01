package ru.ok.journal.remote.rmi;

import java.io.Serializable;

public class Pair implements Serializable {
    private long postId;
    private long lastCommentId;

    public Pair(long postId, long lastCommentId) {
        this.postId = postId;
        this.lastCommentId = lastCommentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getLastCommentId() {
        return lastCommentId;
    }

    public void setLastCommentId(long lastCommentId) {
        this.lastCommentId = lastCommentId;
    }
}
