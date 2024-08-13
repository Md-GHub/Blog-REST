package com.mdghu.blog.controller;

import java.util.List;

import com.mdghu.blog.entity.ConfirmationMail;
import com.mdghu.blog.entity.User;
import com.mdghu.blog.model.*;
import com.mdghu.blog.service.MailService;
import com.mdghu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consumer")
public class UserController {

    @Autowired
    private UserService userService; // Inject the service layer

    @Autowired
    private MailService mailService;
    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody User user) {
        try {
            UserModel savedUser = userService.saveUser(user);  // Save user through service
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);  // Return success response
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<UserModel> editUser(@PathVariable Long id,@RequestBody UserModel user) {
        try {
            UserModel userModel = userService.editUser(id,user);
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @PutMapping("/user/edit/password")
    public ResponseEntity<String> editUserPassword(@RequestBody ChangePasswordModel newPassword) {
        try{
            String message = userService.editUserPassword(newPassword);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @GetMapping("/user/post")
    public ResponseEntity<List<PostModel>> getPost() {
        try{
            List<PostModel> post = userService.getAllPost();
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @PostMapping("/user/post")
    public ResponseEntity<PostModel> addPost(@RequestBody BlogModel post) {
        try{
            PostModel postModel = userService.postBlog(post);
            return new ResponseEntity<>(postModel, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @PostMapping("/user/confirmation")
    public ResponseEntity<UserModel> confirmUser(@RequestBody ConfirmationMail confirmationMail) {
        try{
            UserModel model = userService.verifyTheOtp(confirmationMail);
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }
    @PostMapping("/user/confirmation/resend")
    public ResponseEntity<String> reSendOtp(@RequestBody ConfirmationMail confirmationMail) {
        try{
            String message = userService.reSendOtp(confirmationMail);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }
}
