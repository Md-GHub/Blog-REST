package com.mdghu.blog.repository;

import com.mdghu.blog.entity.Post;
import com.mdghu.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String username);
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
    @Modifying
    @Query(
            value = "UPDATE USER SET PASSWORD = :password WHERE EMAIL = :email OR USER_NAME = :email",
            nativeQuery = true
    )
    void updatePassword(@Param("email") String email, @Param("password") String password);
}
