package com.kirua.galactic.service;

import com.kirua.galactic.dao.CommentDao;
import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Comment add(String content, User user, GalacticPictures galacticPictures) {
        Comment newComment = new Comment(UUID.randomUUID(), content, user, galacticPictures);
        this.commentDao.add(newComment);
        return newComment;
    }

    public ArrayList<Object> findCommentByGalacticPicture(GalacticPictures picture) {
        return this.commentDao.findByPictureId(picture);
    }
}
