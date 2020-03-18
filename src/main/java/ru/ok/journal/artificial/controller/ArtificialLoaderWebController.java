package ru.ok.journal.artificial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ok.journal.artificial.ArtificialThreadLoader;
import ru.ok.journal.artificial.IArtificialLoader;

@Controller
@RequestMapping("/")
public class ArtificialLoaderWebController {

    IArtificialLoader loader;

    public ArtificialLoaderWebController(IArtificialLoader loader){
        this.loader = loader;
    }

    @PostMapping("startLoader")
    public void startLoader(){
        loader.startLoader();
    }

    @PostMapping("stopLoader")
    public void stopLoader(){
        loader.stopLoader();
    }

    @PostMapping("loaderThreads")
    public void startThreadsLoader(Long threads){
        for (int i = 0; i < threads; i++) {
            new ArtificialThreadLoader("ArtificialThreadLoader", loader);
        }
    }

    @GetMapping("loaderStatus")
    public boolean getStatus(){
        System.out.println(loader.getStatus());
        if (loader.getStatus()) return true;
        else return false;
    }

}
