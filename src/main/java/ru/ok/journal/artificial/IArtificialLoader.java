package ru.ok.journal.artificial;

public interface IArtificialLoader {
    public void stopLoader(); //остановить загрузку
    public void startLoader(); //начать загрузку
//    public String getSentence(); //отдаёт предложение TODO: определить надо ли или удалить;
    public void toPost(Integer postId); //добавлять комментарии только в выбранный пост
//    public void toAllPost(Integer numberOfPosts); //добавлять комментарии во все посты
    public void createPost(); //создать рандомный пост
    public void setMaxSentenceLength(Integer maxSentenceLength);
}
