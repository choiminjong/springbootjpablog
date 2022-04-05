package com.springbootjpablog.controller.view;

import com.springbootjpablog.security.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class BoardController {

    @GetMapping("/")
    public String index( ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetail detail = (PrincipalDetail)auth.getPrincipal();

        System.out.println("detail.getUsername() = " + detail.getUsername());

        for(GrantedAuthority role : detail.getAuthorities()){
            System.out.println("role = " + role.getAuthority());
        }

        return "index";
    }
}
