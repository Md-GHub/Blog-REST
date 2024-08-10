package com.mdghu.blog.controller;

import com.mdghu.blog.entity.User;
import com.mdghu.blog.model.UserModel;
import com.mdghu.blog.service.AdminService;
import com.mdghu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admincontroller")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        try {
            UserModel savedUser = adminService.findUserById(id);  // Save user through service
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);  // Return success response
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            String message = adminService.deleteUserById(id);  // Save user through service
            return new ResponseEntity<>(message, HttpStatus.CREATED);  // Return success response
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
}