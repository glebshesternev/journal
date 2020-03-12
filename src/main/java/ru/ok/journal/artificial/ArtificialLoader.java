package ru.ok.journal.artificial;

import org.springframework.stereotype.Service;
import ru.ok.journal.dto.CommentDto;
import ru.ok.journal.dto.PostDto;
import ru.ok.journal.model.Post;
import ru.ok.journal.model.User;
import ru.ok.journal.service.ICommentService;
import ru.ok.journal.service.IPostService;
import ru.ok.journal.service.IUserServiceBack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ArtificialLoader implements IArtificialLoader {

    private volatile boolean flag;
    private volatile List<Post> posts; //TODO: здесь оставить переменную или только в методах хранить отдельно (toAllPost, toPost);
    private volatile List<String> words;

    private Random random;
    private Integer maxSentenceLength;
    private final User user;
    private List<User> users;

    private IPostService postService;
    private ICommentService commentService;
    private IUserServiceBack userServiceBack;


    public ArtificialLoader(IPostService postService, ICommentService commentService, IUserServiceBack userServiceBack) throws IOException {
        this.postService = postService;
        this.commentService = commentService;
        this.userServiceBack = userServiceBack;
        this.users = new ArrayList<>();

        flag = false;
        posts = new ArrayList<>();
        words = new ArrayList<>();

        random = new Random();
        maxSentenceLength = 5;
        users = userServiceBack.findAll();
        user = users.get(0);

        //Заполняем список слов из файла
        this.refreshWordList();
    }

    /**
     * maxSentenceLength = 5 +1;
     * @return String (sentence)
     */
    private String getSentence(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < random.nextInt(maxSentenceLength)+1; i++) {
            str.append(words.get(random.nextInt(words.size()))).append(" ");
        }
        return str.toString();
    }

    private void toAllPost(){

        this.posts = postService.getAll();
        Integer numberOfPosts = posts.size();
        Post post = posts.get(random.nextInt(numberOfPosts));

        CommentDto comment = new CommentDto();
        comment.setData(this.getSentence());
        commentService.add(user, post, comment);
        System.out.println(commentService.getByPost(post));
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
            commentService.add(user, post, comment);
        }
    }

    @Override
    public void createPost(){
        PostDto postDto = new PostDto();
        postDto.setName("<Artificial> " + words.get(random.nextInt(words.size())));
        postDto.setData(this.getSentence());
        postService.add(user, postDto);
    }

    @Override
    public void setMaxSentenceLength(Integer maxSentenceLength){
        if (maxSentenceLength > 0) this.maxSentenceLength = maxSentenceLength;
    }

    @Override
    public void refreshWordList() throws IOException{
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src/main/java/ru/ok/journal/artificial/data/word_rus")
                )
        );
        String str;
        while((str = reader.readLine()) != null) {
            this.words.add(str);
        }
        reader.close();
    }
}
