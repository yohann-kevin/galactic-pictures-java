package com.kirua.galactic.exception;

public class PictureException extends Exception {
    public void pictureNotFoundException(String date) {
        System.out.println("this picture not found, date : ");
    }
}
