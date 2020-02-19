package ru.ok.journal.remote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ru.ok.journal.remote.rmi.RMIComment;
import ru.ok.journal.remote.rmi.RMICommentImpl;

@Configuration
public class RemoteConfig {
//    long id;
//    long postId;
//    String text;

    @Bean
    RemoteExporter registerRMIExporter() {

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("PostCommentRMI");
        exporter.setServiceInterface(RMIComment.class);
        exporter.setService(new RMICommentImpl());
//        exporter.setService(new RMICommentImpl(id, postId, text));

        return exporter;
    }

}
