package ru.ok.journal.artificial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ok.journal.artificial.ArtificialLoader;

@Controller
@RequestMapping("/")
public class ArtificialLoaderController {


    ArtificialLoader loader;

    @GetMapping("/startLoader")
    public String startLoader(){
        loader.startLoader();
        return "startLoader";
    }

    @GetMapping("/stopLoader")
    public String stopLoader(){
        loader.stopLoader();
        return "stopLoader";
    }
    /**
     * ...
     * TODO: other methods
     * ...
     */

}
