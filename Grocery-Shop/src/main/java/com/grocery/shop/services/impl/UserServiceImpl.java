package com.grocery.shop.services.impl;

import com.grocery.shop.dtos.PageableResponse;
import com.grocery.shop.dtos.UserDto;
import com.grocery.shop.entities.Role;
import com.grocery.shop.entities.User;
import com.grocery.shop.exceptions.ResourceNotFoundException;
import com.grocery.shop.helpers.Helper;
import com.grocery.shop.repositories.RoleRepository;
import com.grocery.shop.repositories.UserRepository;
import com.grocery.shop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        SecureRandom random = new SecureRandom();
        int userId = random.nextInt(1000);
        userDto.setUserId(userId);
        User user = mapper.map(userDto,User.class);
        // set encrypted password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Set Role as User By Default
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        Role roleUser = roleRepository.findByName("ROLE_USER").orElse(role);
        user.setRoles(List.of(roleUser));
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for provided ID"));
        user.setName(userDto.getName());
        // email update optional
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = mapper.map(updatedUser, UserDto.class);
        return updatedDto;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for provided ID"));
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        // pageNumber default starts from 0
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);
        return pageableResponse;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for provided ID"));
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
}
