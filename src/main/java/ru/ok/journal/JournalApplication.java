package ru.ok.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.ok.journal.artificial.IArtificialLoader;

@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
//		SpringApplication.run(JournalApplication.class, args);
		ApplicationContext app = SpringApplication.run(JournalApplication.class, args);
		IArtificialLoader loader = app.getBean(IArtificialLoader.class);
		loader.startLoader();
		loader.stopLoader();
	}

}
