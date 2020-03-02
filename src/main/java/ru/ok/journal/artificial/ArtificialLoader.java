package ru.ok.journal.artificial;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ArtificialLoader implements IArtificialLoader {
    private Random random = new Random();
    private List<String> words = new ArrayList<>();
    private Integer maxSentenceLength = 50;


    public ArtificialLoader() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("ru/ok/journal/artificial/data/word_rus")
                )
        );
        String str;
        while((str = reader.readLine()) != null) {
            words.add(str);
        }
        reader.close();
    }


    @Override
    public void add(/**Post post, Comment comment, User user*/){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < random.nextInt(maxSentenceLength); i++) {
            str.append(words.get(random.nextInt(words.size())));
        }
    }

}
