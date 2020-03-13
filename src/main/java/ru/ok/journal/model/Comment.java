package ru.ok.journal.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private Date time;
    @ManyToOne(fetch = FetchType.EAGER,
//            cascade = {CascadeType.MERGE})
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_comments",
            joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE})
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "comment_author",
            joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private User author;

}
