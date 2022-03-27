package com.vasu.blog.springbootBlogApplication.repository;
import com.vasu.blog.springbootBlogApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
