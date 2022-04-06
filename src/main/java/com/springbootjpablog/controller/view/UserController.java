package com.springbootjpablog.controller.view;

import com.springbootjpablog.model.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String join(Model model) {

        model.addAttribute("user", new Users());

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String login() {
        return "user/loginForm";
    }
}
