package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.service.UserService;
import com.monkey.aggregate.user.dto.user.UserSaveDto;
import com.monkey.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/test_login")
    public ResponseEntity<String> test(@RequestParam("id") Long id) {
        Long result = userService.getUserId(id);
        String token = JwtTokenUtils.CreateJwtToken(result);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> test(@RequestBody UserSaveDto req) {
        Long userId = userService.saveUser(req);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
