package com.amit.LibManagement.repositary;

import com.amit.LibManagement.model.User;
import com.amit.LibManagement.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Integer> {

    User findByPhoneNoAndUserType(String userPhoneNo, UserType userType);
}
