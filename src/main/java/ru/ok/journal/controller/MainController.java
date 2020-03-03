package ru.ok.journal.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.NewPostDto;
import ru.ok.journal.dto.ShowPostsDto;
import ru.ok.journal.repository.PostRepository;
import ru.ok.journal.service.IMainControllerService;
import ru.ok.journal.service.IPostService;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    private IMainControllerService mainControllerService;
    private IPostService postService;
    private final PostRepository postRepository;

    public MainController(IMainControllerService mainControllerService, PostRepository postRepository) {
        this.mainControllerService = mainControllerService;
        this.postRepository = postRepository;
    }

    @GetMapping("/newPost")
    public String createPost(Model model) {
        NewPostDto newPostDto = new NewPostDto();
        model.addAttribute("post", newPostDto);
        return "newPost";
    }

    @PostMapping(value = "/newPost")
    public ModelAndView createPost(@ModelAttribute("post") final NewPostDto newPostDto,
                                   Model model) {
        String viewName = mainControllerService.createPost(newPostDto, model);
        return new ModelAndView(viewName, "post", newPostDto);
    }

    @GetMapping("/posts")
    public ModelAndView showPosts(Model model) {
        return mainControllerService.showAllPosts();
    }
}
