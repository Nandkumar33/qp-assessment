package com.grocery.shop.services;

import com.grocery.shop.dtos.PageableResponse;
import com.grocery.shop.dtos.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, int userId);

    void deleteUser(int userId);

    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(int userId);
}
