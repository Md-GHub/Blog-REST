package com.mdghu.blog.service;

import com.mdghu.blog.entity.User;
import com.mdghu.blog.model.UserModel;
import com.mdghu.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


    @Autowired
    UserRepo userRepository;

    public UserModel findUserById(Long id){
        User user = userRepository.findById(id).get();
        if (user == null){
            throw new IllegalArgumentException("User not found");
        }
        return convertToUserModel(user);
    }
    public String deleteUserById(Long id){
        User user = userRepository.findById(id).get();
        if (user == null){
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
        return "User deleted";
    }
    private UserModel convertToUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setUserName(user.getUserName());
        userModel.setMobile(user.getMobile());
        return userModel;
    }
}
