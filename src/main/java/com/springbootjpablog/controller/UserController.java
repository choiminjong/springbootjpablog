package com.springbootjpablog.controller;

import com.springbootjpablog.model.Board;
import com.springbootjpablog.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @GetMapping("/user/account")
    public String account(Model model) {
        model.addAttribute("user", new Users());
        return "user/account";
    }

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }
}
