package com.springbootjpablog.controller.api;

import com.springbootjpablog.model.dto.ResponseDto;
import com.springbootjpablog.model.RoleType;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.security.auth.PrincipalDetail;
import com.springbootjpablog.security.auth.PrincipalDetailService;
import com.springbootjpablog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody Users user) {
        user.setRole(RoleType.USER);
        userService.accout(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody Users user) {
        userService.userUpdate(user);

        //DB내용이 변경전 세션을 요청하면 세션 변경이 불가합니다.
        //트랜잭션이 종료되기때문에 DB에 값은 변경되었지만,
        //세션값은 변경되지 않은 상태입니다. 직접 세션값을 변경해야합니다.
        System.out.println("user = " + user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}