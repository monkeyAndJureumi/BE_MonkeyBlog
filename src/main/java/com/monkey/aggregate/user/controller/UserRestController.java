package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.token.service.TokenService;
import com.monkey.aggregate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final TokenService tokenService;


}
