package ru.ok.journal.remote.rmi;


import ru.ok.journal.model.Comment;

import java.util.List;

public interface IRMIComment  {
    public List<Comment> getByPost(List<Pair> postIds);

}
