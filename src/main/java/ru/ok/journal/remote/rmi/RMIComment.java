package ru.ok.journal.remote.rmi;

public interface RMIComment {

    public long getId();

    public void setId(long id);

    public long getPostId();

    public void setPostId(long postId);

    public String getText();

    public void setText(String text);

}
