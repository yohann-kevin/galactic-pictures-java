package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.CommentDao;
import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class CommentInDB implements CommentDao {
    private final CommentRepository commentRepository;

    @Override
    public void add(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public ArrayList findAll() {
        return null;
    }
}
