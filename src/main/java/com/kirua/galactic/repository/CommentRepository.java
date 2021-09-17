package com.kirua.galactic.repository;

import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID>  {

    ArrayList<Comment> findCommentByGalacticPictures(GalacticPictures picture);

    ArrayList<Comment> findCommentByUser(User user);
}
