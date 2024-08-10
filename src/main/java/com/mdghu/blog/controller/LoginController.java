package com.mdghu.blog.controller;

import com.mdghu.blog.model.LoginModel;
import com.mdghu.blog.model.UserModel;
import com.mdghu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userlogin")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody @Validated LoginModel user) {
        try {
            UserModel userModel = userService.loginUser(user);
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return error response
        }
    }
}
