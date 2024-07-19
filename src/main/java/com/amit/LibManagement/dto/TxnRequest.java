package com.amit.LibManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TxnRequest {
    @NotBlank(message = "book title should be not blank")
    private String userPhoneNo;

    @NotBlank(message = "book number should be not blank")
    private String bookNo;
}
