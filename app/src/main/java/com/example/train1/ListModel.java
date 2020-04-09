package com.example.train1;

import com.google.firebase.firestore.DocumentId;

public class ListModel{

    @DocumentId
    private String quiz_id;
    private String quiz_name, quiz_image, quiz_description, quiz_level;
    private Long quiz_number;

    public ListModel(){

    }

    public ListModel(String quiz_id, String quiz_name, String quiz_image, String quiz_description, String quiz_level, Long quiz_number) {
        this.quiz_id = quiz_id;
        this.quiz_name = quiz_name;
        this.quiz_image = quiz_image;
        this.quiz_description = quiz_description;
        this.quiz_level = quiz_level;
        this.quiz_number = quiz_number;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getQuiz_image() {
        return quiz_image;
    }

    public void setQuiz_image(String quiz_image) {
        this.quiz_image = quiz_image;
    }

    public String getQuiz_description() {
        return quiz_description;
    }

    public void setQuiz_description(String quiz_description) {
        this.quiz_description = quiz_description;
    }

    public String getQuiz_level() {
        return quiz_level;
    }

    public void setQuiz_level(String quiz_level) {
        this.quiz_level = quiz_level;
    }

    public Long getQuiz_number() {
        return quiz_number;
    }

    public void setQuiz_number(Long quiz_number) {
        this.quiz_number = quiz_number;
    }
}
