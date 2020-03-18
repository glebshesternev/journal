package ru.ok.journal.artificial;

import java.io.IOException;

public interface IArtificialLoader  {
    public void stopLoader(); //остановить загрузку
    public void startLoader(); //начать добавление комментариев во все посты
    public void toPost(Integer postId); //добавлять комментарии только в выбранный пост
    public void createPost(); //создать рандомный пост (надо ли???)
    public void setMaxSentenceLength(Integer maxSentenceLength);
    public void refreshWordList() throws IOException;
    public boolean getStatus();
}
