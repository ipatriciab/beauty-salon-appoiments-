package com.beautySalon.service;

import com.beautySalon.dto.UserDto;
import com.beautySalon.mapper.UserMapper;
import com.beautySalon.model.Role;
import com.beautySalon.model.User;
import com.beautySalon.repository.RoleRepository;
import com.beautySalon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, 
                       UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Role role = roleRepository.findByName(userDto.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + userDto.getRole()));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
