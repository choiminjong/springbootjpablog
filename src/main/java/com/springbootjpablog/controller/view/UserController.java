package com.springbootjpablog.controller.view;

import com.springbootjpablog.model.entity.Users;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        model.addAttribute("user", new Users());
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao")
    public String kakaoLogin() {
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=b513d8919eb20fb7c475f7474c840680&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code";
    }

    /*
     @ResponseBody 어노테이션 HTTP요청 바디를 자바객체로 변환하고 자바객체를 다시 HTTP 응답 바디로 변환해준다.
    */
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){
        /*
        MIME=application/x-www-form-urlencoded;charset=utf-8
        grant_type=authorization_code
        client_id=b513d8919eb20fb7c475f7474c840680
        redirect_uri=http://localhost:8080/auth/kakao/callback
        code=code
         */
        
        //POST 방식으로 key=value 데이터를 카카오 로그인 서버로 전달
        RestTemplate rt = new RestTemplate();
        
        //HttpHeaders 오브젝트 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        //params.add는 변수를 따로 설정해서 사용해야하는데 직관적으로 이해하기위해서 다이렉트로 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","b513d8919eb20fb7c475f7474c840680");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);
        
        //HttpHeaders와 HttpBody 오브젝트를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,httpHeaders);

        //Http 요청하기 -> Post 방식으로 요청하면 response 변수로 데이터를 응답 받음
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", //어떤 곳으로 전달할건지
                HttpMethod.POST,    //메소드 타입
                kakaoTokenRequest,  // HttpEntity 객체가
                String.class        //응답 데이터 타입
        );

        return "카카오 토큰 요청 : 토큰 요청 데이터  "+ response;
    }
}
