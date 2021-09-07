package com.kirua.galactic.dao;

import com.kirua.galactic.domain.comment.Comment;

import java.util.ArrayList;

public interface CommentDao {
    void add(Comment comment);

    ArrayList findAll();
}
