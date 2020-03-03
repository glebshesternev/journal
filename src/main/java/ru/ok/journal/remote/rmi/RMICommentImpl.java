package ru.ok.journal.remote.rmi;

import org.springframework.stereotype.Service;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.remote.entity.RMIComment;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RMICommentImpl implements IRMIComment {
//    @Resource
    private ICommentService commentService;

//    @Resource
    private IPostService postService;

    public RMICommentImpl(IPostService postService, ICommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public List<RMIComment> getByPost(List<Pair> postIds) {
        /**
         * Pair - postID + lastCommentID
         */
        List<Post> posts = new ArrayList<>();
        List<RMIComment> RMIComments = new ArrayList<>();
        List<Comment> tempComments = new ArrayList<>();
        posts.addAll(postService.getAll());
        for (Post post : posts) {
            for (Pair postId : postIds) {
                if (post.getId() == postId.getPostId()){
                    tempComments.addAll(commentService.getByPost(post));
                    for (Comment tempComment : tempComments) {
                        if (tempComment.getId() >=postId.getLastCommentId()){
                            RMIComments.add(
                                    new RMIComment(tempComment.getId(), tempComment.getPost().getId(), tempComment.getData())
                            );
                        }
                    }
                }
            }
        }
        return RMIComments;
    }

}
