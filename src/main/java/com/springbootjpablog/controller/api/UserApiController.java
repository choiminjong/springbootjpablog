package com.springbootjpablog.controller.api;

import com.springbootjpablog.model.dto.ResponseDto;
import com.springbootjpablog.model.eunm.RoleType;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody Users user) {
        userService.accout(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody Users user) {
        userService.userUpdate(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}