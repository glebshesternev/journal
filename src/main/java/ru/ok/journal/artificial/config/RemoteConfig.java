package ru.ok.journal.artificial.rmi;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ru.ok.journal.artificial.ArtificialLoader;
import ru.ok.journal.artificial.IArtificialLoader;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;

import java.io.IOException;

@Configuration
public class RemoteConfig {
    private ICommentService commentService;
    private IPostService postService;

    public RemoteConfig(IPostService postService, ICommentService commentService){
        this.postService = postService;
        this.commentService = commentService;
    }

    @Bean
    RemoteExporter registerRMIExporter() throws IOException {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("rmi_artificial");
        exporter.setServiceInterface(IArtificialLoader.class);
        exporter.setService(new ArtificialLoader(this.postService, this.commentService));

        return exporter;
    }

}
