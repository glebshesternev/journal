package ru.ok.journal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ok.journal.service.IPostControllerService;
import ru.ok.journal.service.IUserService;

@Controller
@RequestMapping("/")
public class MainController {
    private IPostControllerService postControllerService;
    private IUserService userService;

    @Value("${spring.profiles.active}")
    private String profile;

    public MainController(IPostControllerService postControllerService, IUserService userService) {
        this.postControllerService = postControllerService;
        this.userService = userService;
    }

    @GetMapping("posts")
    public String showPosts(Model model) {
        model.addAttribute("profile", userService.getProfile());
        model.addAttribute("postsPage", postControllerService.getPostsPage());
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}