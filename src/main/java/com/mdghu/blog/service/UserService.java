package com.mdghu.blog.service;

import com.mdghu.blog.entity.Post;
import com.mdghu.blog.entity.Role;
import com.mdghu.blog.entity.User;
import com.mdghu.blog.model.*;
import com.mdghu.blog.repository.PostRepo;
import com.mdghu.blog.repository.RoleRepo;
import com.mdghu.blog.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PostRepo postRepo;

    public UserModel  saveUser(User user) {
        Role defaultRole = roleRepo.findById(2L).get();
        user.setRole(defaultRole);
        user.setDateOfJoin(new Date());
        User userValue = userRepo.save(user);
        return convertToUserModel(userValue);
    }
    public UserModel loginUser(LoginModel loginModel) {
        if (loginModel == null || loginModel.getUserName() == null || loginModel.getPassword() == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        // Find the user by username
        User user = userRepo.findByUserName(loginModel.getUserName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Directly compare the password (plain text)
        if (!loginModel.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Convert User to UserModel or return user details
        return convertToUserModel(user);
    }
    public UserModel editUser(Long id, UserModel user) {
        if (id == null || user == null) {
            throw new IllegalArgumentException("User ID and User details must not be null");
        }

        if (!userRepo.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }

        User userPrevious = userRepo.findById(id).get();

        // Update fields only if they are provided (not null)
        if (user.getFirstName() != null) {
            userPrevious.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            userPrevious.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            userPrevious.setEmail(user.getEmail());
        }

        if (user.getMobile() != null) {
            userPrevious.setMobile(user.getMobile());
        }

        if (user.getUserName() != null) {
            userPrevious.setUserName(user.getUserName());
        }

        // Save the updated user back to the repository
        User updatedUser = userRepo.save(userPrevious);

        // Convert to UserModel and return
        return convertToUserModel(updatedUser);
    }

    @Transactional
    public String editUserPassword(ChangePasswordModel newPassword) {
        if (newPassword == null || newPassword.getOldPassword() == null || newPassword.getNewPassword() == null || newPassword.getEmail() == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        if(!userRepo.existsByEmail(newPassword.getEmail())){
            throw new IllegalArgumentException("User not found");
        }
        userRepo.updatePassword(newPassword.getEmail(),newPassword.getNewPassword());
        return "Password changed successfully";
    }

    public List<PostModel> getAllPost() {
        List<Post> allPosts = postRepo.findAll();
        List<PostModel> postModels = new ArrayList<>();
        convertToPostModel(postModels, allPosts);
        return postModels;
    }



    public PostModel postBlog(BlogModel message) {
        if (message == null || message.getMessage() == null) {
            throw new IllegalArgumentException("Post must not be null");
        }
        User user = userRepo.findByEmail(message.getEmail());
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }
        Post post =  new Post();
        post.setMessage(message.getMessage());
        post.setLikes(0L);
        post.setComments(null);
        post.setDate(new Date());
        post.setAuthor(user);
        postRepo.save(post);
        PostModel postModel = convertToPostModel(post);
        return postModel;
    }

    private PostModel convertToPostModel(Post post) {
        PostModel postModel =  new PostModel(
                post.getMessage(),
                post.getLikes(),
                post.getComments(),
                post.getAuthor(),
                post.getDate()
        );
        return postModel;
    }

    private void convertToPostModel(List<PostModel> postModels, List<Post> allPosts) {
        for (Post post : allPosts) {
            PostModel model = new PostModel(
                    post.getMessage(),
                    post.getLikes(),
                    post.getComments(),
                    post.getAuthor(),
                    post.getDate()
            );
            postModels.add(model);
        }
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
