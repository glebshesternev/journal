package ru.ok.journal.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.User;
import ru.ok.journal.repository.PostRepository;
import ru.ok.journal.service.*;

import java.util.HashMap;

@Controller
public class MainController {
    private IMainControllerService mainControllerService;
    private final PostRepository postRepository;

    public MainController(IMainControllerService mainControllerService, PostRepository postRepository) {
        this.mainControllerService = mainControllerService;
        this.postRepository = postRepository;
    }

    @GetMapping("/newPost")
    public String createPost(Model model, @AuthenticationPrincipal User user) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "newPost";
    }

    @PostMapping(value = "/newPost")
    public ModelAndView createPost(@ModelAttribute("post") final PostDto postDto,
                                   Model model) {
        String viewName = mainControllerService.createPost(postDto, model);
        return new ModelAndView(viewName, "post", postDto);
    }

    @GetMapping("/posts")
    public ModelAndView showPosts(Model model, @AuthenticationPrincipal User user) {
        return mainControllerService.showAllPosts();
    }

}
