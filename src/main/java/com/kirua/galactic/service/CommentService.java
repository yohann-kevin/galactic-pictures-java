package com.kirua.galactic.service;

import com.kirua.galactic.dao.CommentDao;
import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Comment add(String content, User user, GalacticPictures galacticPictures) {
        Date date = new Date();
        Comment newComment = new Comment(UUID.randomUUID(), content, date, user, galacticPictures);
        this.commentDao.add(newComment);
        return newComment;
    }

    public ArrayList<Object> findCommentByGalacticPicture(GalacticPictures picture) {
        return this.commentDao.findByPictureId(picture);
    }

    public ArrayList findCommentByUser(User user) {
        return this.commentDao.findPictureByUser(user);
    }

    public void deleteCommentById(String id) {
        this.commentDao.deletePictureById(id);
    }
}
