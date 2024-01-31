package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}
