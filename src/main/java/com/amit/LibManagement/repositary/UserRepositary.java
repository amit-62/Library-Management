package com.amit.LibManagement.repositary;

import com.amit.LibManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Integer> {
}
