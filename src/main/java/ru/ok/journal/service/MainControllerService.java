package ru.ok.journal.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Post;

@Service
public class MainControllerService implements IMainControllerService {

    private IPostService postService;
    private ICommentService commentService;
    private IUserService userService;


    public MainControllerService(IPostService postService, ICommentService commentService, IUserService userService){
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @Override
    public String createPost(PostDto postDto, Model model) {
        postService.add(userService.currentUser(), postDto);
        return "newPost";
    }

    @Override
    public ModelAndView showAllPosts() {
        ModelAndView modelAndView = new ModelAndView("result", "allPosts", postService.getAll());
        return modelAndView;
    }

    @Override
    public ModelAndView showCurrentPost(Post post) {
        ModelAndView modelAndView = new ModelAndView("result", "post", post);
        return modelAndView;
    }
}
