package com.monkey.domain.user.root.controller;

import com.monkey.domain.user.root.service.UserService;
import com.monkey.domain.user.root.view.UserSaveReq;
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
    public ResponseEntity<String> test(@RequestParam("id") Long id) {
        Long result = userService.getUserId(id);
        String token = TokenUtils.CreateJwtToken(result);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> test(@RequestBody UserSaveReq req) {
        Long userId = userService.saveUser(req);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
