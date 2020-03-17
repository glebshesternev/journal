package ru.ok.journal.artificial.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ru.ok.journal.artificial.ArtificialLoader;
import ru.ok.journal.artificial.IArtificialLoader;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;
import ru.ok.journal.service.IUserService;
import ru.ok.journal.service.IUserServiceBack;

import java.io.IOException;

/**
 * client: https://github.com/NShmendyuk/LoaderRMI
 */
@Configuration
public class RemoteConfig {
    private ICommentService commentService;
    private IPostService postService;
    private IUserServiceBack userServiceBack;

    public RemoteConfig(IPostService postService, ICommentService commentService, IUserServiceBack userServiceBack){
        this.postService = postService;
        this.commentService = commentService;
        this.userServiceBack = userServiceBack;
    }

    @Bean
    RemoteExporter registerRMIExporter() throws IOException {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("artificial");
        exporter.setServiceInterface(IArtificialLoader.class);
        exporter.setService(new ArtificialLoader(this.postService, this.commentService, this.userServiceBack));

        return exporter;
    }

}
