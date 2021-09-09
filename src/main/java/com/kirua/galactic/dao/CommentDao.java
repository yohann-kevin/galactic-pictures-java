package com.kirua.galactic.dao;

import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.domain.pictures.GalacticPictures;

import java.util.ArrayList;

public interface CommentDao {
    void add(Comment comment);

    ArrayList findAll();

    ArrayList findByPictureId(GalacticPictures picture);
}
