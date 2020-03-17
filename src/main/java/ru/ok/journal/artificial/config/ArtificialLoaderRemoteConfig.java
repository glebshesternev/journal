package ru.ok.journal.artificial.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ru.ok.journal.artificial.ArtificialLoader;
import ru.ok.journal.artificial.IArtificialLoader;
import ru.ok.journal.service.*;

import java.io.IOException;

/**
 * client: https://github.com/NShmendyuk/LoaderRMI
 */
@Configuration
public class ArtificialLoaderRemoteConfig {
    private ICommentService commentService;
    private IPostService postService;
    private IUserServiceBack userServiceBack;
    private IPostControllerService postControllerService;

    public ArtificialLoaderRemoteConfig(IPostControllerService postControllerService, IPostService postService, ICommentService commentService, IUserServiceBack userServiceBack){
        this.postService = postService;
        this.commentService = commentService;
        this.userServiceBack = userServiceBack;
        this.postControllerService = postControllerService;
    }

    @Bean
    RemoteExporter registerRMIExporter() throws IOException {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("artificial");
        exporter.setServiceInterface(IArtificialLoader.class);
        exporter.setService(new ArtificialLoader(this.postService, this.postControllerService, this.commentService, this.userServiceBack));

        return exporter;
    }

}
