package ru.ok.journal.artificial;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO: добавить потоки?;
@Service
public class ArtificialLoader implements IArtificialLoader {

    private volatile boolean flag = false;
    private volatile List<Post> posts; //TODO: здесь оставить переменную или только в методах хранить отдельно (toAllPost, toPost);
    private volatile List<String> words = new ArrayList<>();

    private Random random = new Random();
    private Integer maxSentenceLength = 50;
    private final User user;

    private IPostService postService;
    private ICommentService commentService;

    //    @Override
    private String getSentence(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.random.nextInt(maxSentenceLength); i++) {
            str.append(this.words.get(this.random.nextInt(this.words.size())));
        }
        return str.toString();
    }

    //TODO: делать рандомно по одному или в цикле по порядку все посты проходить
    // (последнее быстрее наверное, зато первое случайно);
//    @Override
    private void toAllPost(){
        this.posts = postService.getAll(); //TODO: вынести в отдельный метод как refreshPostsSize();
        //количество постов
        Integer numberOfPosts = this.posts.size();
        Post post = posts.get(this.random.nextInt(numberOfPosts));
        CommentDto comment = new CommentDto();
        comment.setData(this.getSentence());
        commentService.add(this.user, post, comment);
    }


    public ArtificialLoader(IPostService postService, ICommentService commentService) throws IOException {
//        flag = false;
        this.postService = postService;
        this.commentService = commentService;

        this.user = new User(); //2, "Artifact", "$2y$12$uxplL27Pvf3Bs7j5giGoYeSiQFKfCHTbziui/SRo9fJCc4SI7P4ra", 1
        //id. login, password, enabled
        this.user.setId(2);
        this.user.setLogin("Artificial");
        this.user.setPassword("$2y$12$uxplL27Pvf3Bs7j5giGoYeSiQFKfCHTbziui/SRo9fJCc4SI7P4ra"); //test
        this.user.setEnabled(true);

        /**Заполняем список слов из файла //<todo>: может оформить отдельным методом?*/
        //лучше eng список слов?
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("ru/ok/journal/artificial/data/word_rus")
                )
        );
        String str;
        while((str = reader.readLine()) != null) {
            this.words.add(str);
        }
        reader.close();
        //</todo>
    }

    @Override
    public void stopLoader(){
        this.flag = false;
    }

    @Override
    public void startLoader(){
        this.flag = true;
        while (this.flag){
            toAllPost();
        }
    }

    @Override
    public void toPost(Integer postId){
        this.flag = true;
        while(this.flag){
            this.posts = postService.getAll(); //TODO: вынести в отдельный метод как refreshPostsSize();
            Post post = posts.get(postId);
            CommentDto comment = new CommentDto();
            comment.setData(this.getSentence());
            commentService.add(this.user, post, comment);
        }
    }

    @Override
    public void createPost(){
        PostDto postDto = new PostDto();
        postDto.setName("<Artificial> " + this.words.get(this.random.nextInt(this.words.size())));
        postDto.setData(this.getSentence());
        postService.add(this.user, postDto);
    }

    @Override
    public void setMaxSentenceLength(Integer maxSentenceLength){
        if (maxSentenceLength > 0) this.maxSentenceLength = maxSentenceLength;
    }

}
