package ru.ok.journal.artificial;

/**
 * Here Loader can add threads which have to adding comments to all posts
 */
public class ArtificialThreadLoader extends Thread{
    IArtificialLoader loader;

    public ArtificialThreadLoader(String name, IArtificialLoader loader){
        super(name);
        this.loader = loader;
    }

    @Override
    public void run() {
        loader.startLoader();
    }
}
