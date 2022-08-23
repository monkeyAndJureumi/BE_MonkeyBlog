package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.service.UserService;
import com.monkey.aggregate.user.view.UserSaveReq;
import com.monkey.utils.TokenUtils;
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
    public ResponseEntity<String> test() {
        Long userId = userService.getUserId();
        String token = TokenUtils.CreateJwtToken(userId);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/test_save")
    public ResponseEntity<HttpStatus> test(@RequestBody UserSaveReq req) {
        userService.saveUser(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
