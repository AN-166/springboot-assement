package com.interview.controller;

import com.interview.dto.UserDto;
import com.interview.entity.UserEntity;
import com.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<UserEntity>> getUsers(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(userService.getUsers(page, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/external/{id}")
    public ResponseEntity<String> callExternalApi(@PathVariable Long id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + id;
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}
