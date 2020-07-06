package ru.ok.journal.remote.rmi;

import ru.ok.journal.remote.entity.RMIComment;

import java.util.List;

public interface IRMIComment  {
    public List<RMIComment> getByPost(List<Pair> postIds);
    public void delete(Long commentId);
}
