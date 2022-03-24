package com.springbootjpablog.controller;

import com.springbootjpablog.model.Board;
import com.springbootjpablog.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
