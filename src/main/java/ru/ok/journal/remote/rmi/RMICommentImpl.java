package ru.ok.journal.remote.rmi;

import org.springframework.stereotype.Service;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;

import java.util.ArrayList;
import java.util.List;

@Service //???
public class RMICommentImpl implements RMIComment {
    ICommentService commentService;
    IPostService postService;
    private long id;
    private long postId;
    private String text;

    public RMICommentImpl(long id, long postId, String text) {
        this.id = id;
        this.postId = postId;
        this.text = text;
    }

    public RMICommentImpl() {
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

    public List<Comment> getByPost(List<Pair> postIds, ICommentService commentService, IPostService postService) {
        //Pair - postID + lastCommentID
        List<Post> posts = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Comment> tempComments = new ArrayList<>();
        this.postService = postService;
        this.commentService = commentService;
        posts.addAll(postService.getAll());
        for (Post post : posts) {
            for (Pair postId : postIds) {
                if (post.getId() == postId.getPostId()){
                    tempComments.addAll(commentService.getByPost(post));
                    for (Comment tempComment : tempComments) {
                        if (tempComment.getId() >=postId.getLastCommentId()){
                            comments.add(tempComment);
                        }
                    }
                }
            }
        }

        return comments;
    }

}
