package com.mdghu.blog.model;

import com.mdghu.blog.entity.Comment;
import com.mdghu.blog.entity.User;

import java.util.*;

public class PostModel {

    private String message;
    private Long like;
    private List<Comment> comment;
    private User user;
    private Date date;

    public PostModel(String message, Long like, List<Comment> comment, User user , Date date) {
        this.message = message;
        this.like = like;
        this.comment = comment;
        this.user = user;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
