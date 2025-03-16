package com.grocery.shop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    List<RoleDto> roles = new ArrayList<>();
    private int userId;
    @Size(min = 3, max = 20, message = "Enter Name with min 3 and max 10 length")
    private String name;
    @Pattern(regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}", message = "Enter valid Email")
    private String email;
    @NotBlank(message = "Enter Password!!")
    private String password;
    @Size(min = 4, max = 6, message = "Enter proper Gender")
    private String gender;
    @NotBlank(message = "Enter about information")
    private String about;
}
