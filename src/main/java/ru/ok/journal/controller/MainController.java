package ru.ok.journal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.service.*;

@Controller
public class MainController {

    private IMainControllerService mainControllerService;

    public MainController(IMainControllerService mainControllerService) {
        this.mainControllerService = mainControllerService;

    }

    @GetMapping("/newPost")
    public String createPost(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("address", postDto);
        return "addressForm";
    }

    @PostMapping(value = "/newPost")
    public ModelAndView createPost(@ModelAttribute("post") final PostDto postDto,
                                   Model model) {
        String viewName = mainControllerService.createPost(postDto, model);
        return new ModelAndView(viewName, "post", postDto);
    }

    @PostMapping("/posts")
    public ModelAndView showPosts() {
        return mainControllerService.showAllPosts();
    }

}
