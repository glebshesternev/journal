package ru.ok.journal.remote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ru.ok.journal.remote.rmi.IRMIComment;
import ru.ok.journal.remote.rmi.RMIComment;

@Configuration
public class RemoteConfig {
//    long id;
//    long postId;
//    String text;

    @Bean
    RemoteExporter registerRMIExporter() {

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("PostCommentRMI");
        exporter.setServiceInterface(IRMIComment.class);
        exporter.setService(new RMIComment());
//        exporter.setService(new RMICommentImpl(id, postId, text));

        return exporter;
    }

}
