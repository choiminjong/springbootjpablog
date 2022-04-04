package com.springbootjpablog.controller.api;

import com.springbootjpablog.dto.ResponseDto;
import com.springbootjpablog.model.RoleType;
import com.springbootjpablog.model.Users;
import com.springbootjpablog.repository.UserRepository;
import com.springbootjpablog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody Users users){
        System.out.println("UserApiController : save 호출");
        users.setRole(RoleType.USER);
        userService.accout(users); //실제로 DB에 Insert
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody Users users, HttpSession session){
        Users principal = userService.login(users);

        if(principal != null){
            session.setAttribute("principal",principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}