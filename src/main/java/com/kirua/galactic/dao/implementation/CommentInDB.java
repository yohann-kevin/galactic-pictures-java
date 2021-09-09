package com.kirua.galactic.dao.implementation;

import com.kirua.galactic.dao.CommentDao;
import com.kirua.galactic.domain.comment.Comment;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public ArrayList<Object> findByPictureId(GalacticPictures picture) {
        ArrayList<Comment> commentList = this.commentRepository.findCommentByGalacticPictures(picture);
        ArrayList<Object> sortedList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            HashMap<String, String> details = new HashMap<>();
            details.put("id", commentList.get(i).getId().toString());
            details.put("content", commentList.get(i).getContent());
            details.put("user_writer", commentList.get(i).getUser().getLogin());
            details.put("picture_id", commentList.get(i).getGalacticPictures().getId().toString());
            sortedList.add(details);
        }
        return sortedList;
    }
}
