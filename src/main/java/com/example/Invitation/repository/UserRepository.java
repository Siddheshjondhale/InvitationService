package com.example.Invitation.repository;


import com.example.Invitation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByGoogleId(String googleID);
}
