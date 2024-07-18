package com.amit.LibManagement.dto;

import com.amit.LibManagement.model.User;
import com.amit.LibManagement.model.UserStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserRequest {

    private String name;

    @NotBlank(message = "phone no. should be not blank")
    private String phoneNo;

    @NotBlank(message = "email should be not blank")
    private String email;

    @NotBlank(message = "address should be not blank")
    private String address;

    public User toUser() {
        return User.builder().
                name(this.name).
                phoneNo(this.phoneNo).
                email(this.email).
                address(this.address).
                userStatus(UserStatus.ACTIVE).
                build();
    }
}
