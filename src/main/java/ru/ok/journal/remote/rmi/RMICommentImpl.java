package ru.ok.journal.remote.rmi;

import org.springframework.stereotype.Service;
import ru.ok.journal.model.Comment;
import ru.ok.journal.model.Post;
import ru.ok.journal.remote.entity.RMIComment;
import ru.ok.journal.service.IPostService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RMICommentImpl implements IRMIComment {
    private IPostService postService;

    public RMICommentImpl(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public List<RMIComment> getByPost(List<Pair> postIds) {
        /*
          Pair - postID + lastCommentID
         */
        List<RMIComment> RMIComments = new ArrayList<>();
        List<Comment> tempComments = new ArrayList<>();
        List<Post> posts = new ArrayList<>(postService.getAllPosts());
        for (Post post : posts) {
            for (Pair postId : postIds) {
                if (post.getId() == postId.getPostId()){
                    tempComments.addAll(postService.getCommentsByPost(post));
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
