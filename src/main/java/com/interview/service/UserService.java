package com.interview.service;

import com.interview.dto.UserDto;
import com.interview.entity.UserEntity;
import com.interview.repositary.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity createUser(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity updateUser(Long id, UserDto dto) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public Page<UserEntity> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public UserEntity getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
