package ru.ok.journal.artificial;

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
