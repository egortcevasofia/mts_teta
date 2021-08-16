package com.example.demo.dao;

import com.example.demo.domain.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarImageRepository extends JpaRepository<AvatarImage, Long> {
    @Query("FROM AvatarImage ai WHERE ai.user.username = :username")
    Optional<AvatarImage> findByUsername(String username);
}

