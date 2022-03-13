package com.springbootjpablog.controller;

import com.springbootjpablog.model.RoleType;
import com.springbootjpablog.model.Users;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    //{id} 주소로 파마미터를 전달 받을 수 있음.
    //http://localhost:8080/dummy/user/1 (요청)
    @GetMapping("/dummy/user/{id}")
    public Users detail(@PathVariable Long id){
        //user/1을 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null이 될 수 도 있기때문에
        //return null이 반환될수 있다 그러면 프러그램에서 문제가 발생할 수 있다.
        //Optional로 너의 Users 객체를 감싸서 가져오면 null인지 아닌지 판단해서 return 해줘

        //orElseGet : 값이 null일때만 호출된다.
        Users user = userRepository.findById(id)
                     .orElseThrow(() -> new IllegalStateException("해당 유저는 없습니다."));
        return user;
    }


    //http://localhost:8080/dummy/join (요청)
    //http의 body에 username password email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(Users users){
        System.out.println("id = " + users.getId());
        System.out.println("username = " + users.getUsername());
        System.out.println("password = " + users.getPassword());
        System.out.println("email = " + users.getEmail());
        System.out.println("Role = " + users.getRole());

        users.setRole(RoleType.USER);
        userRepository.save(users);

        return "화원가입이 완료되었습니다.";
    }

    @PostMapping("/dummy/join2")
    public String join2(String username,String password,String email){

        System.out.println("username = " + username);
        System.out.println("password = " +password);
        System.out.println("email = " + email);

        return "화원가입이 완료되었습니다.";
    }

    @PostMapping("/dummy/join3")
    public String join3(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {

        System.out.println("username = " + username);
        System.out.println("password = " +password);
        System.out.println("email = " + email);

        return "화원가입이 완료되었습니다.";
    }


}
