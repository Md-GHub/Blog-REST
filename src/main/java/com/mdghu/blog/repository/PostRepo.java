package com.mdghu.blog.repository;

import com.mdghu.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
