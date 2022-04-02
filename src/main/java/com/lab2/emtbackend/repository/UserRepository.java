package com.lab2.emtbackend.repository;

import com.lab2.emtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
