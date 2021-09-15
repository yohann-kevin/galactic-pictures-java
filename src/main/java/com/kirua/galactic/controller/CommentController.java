package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.service.CommentService;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    private GalacticPicturesService galacticPicturesService;

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestParam String content, String uuid, Principal principal) throws PictureNotFoundException {
        GalacticPictures currentPicture = this.galacticPicturesService.findById(uuid);
        User currentUser = this.userService.getUserByName(principal.getName());
        this.commentService.add(content, currentUser, currentPicture);
    }

    @GetMapping("/{pictureId}")
    public ArrayList<Object> findCommentByGalacticPicture(@PathVariable String pictureId) throws PictureNotFoundException {
        GalacticPictures currentPicture = this.galacticPicturesService.findById(pictureId);
        return this.commentService.findCommentByGalacticPicture(currentPicture);
    }

    @DeleteMapping
    public void deleteCommentById(@RequestParam(value = "id") String id) {
        this.commentService.deleteCommentById(id);
    }
}
