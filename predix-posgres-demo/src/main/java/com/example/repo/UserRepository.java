package com.example.repo;

import com.example.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findById(Long userId);
	
    @Query("SELECT u.id from User u")
    public List<Long> getAllUserIds();
}
