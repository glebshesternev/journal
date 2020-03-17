package ru.ok.journal.artificial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/startLoader")
    public void startLoader(){
        loader.startLoader();
    }

    @GetMapping("/stopLoader")
    public void stopLoader(){
        loader.stopLoader();
    }

    @GetMapping("/loaderThreads")
    public void startThreadsLoader(Long threads){
        for (int i = 0; i < threads; i++) {
            new ArtificialThreadLoader("ArtificialThreadLoader", loader);
        }
    }

    @GetMapping("/loaderStatus")
    public String getStatus(){
        if (loader.getStatus()) return "started";
        else return "stopped";
    }

}
