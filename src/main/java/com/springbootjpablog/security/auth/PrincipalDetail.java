package com.springbootjpablog.security.auth;

import com.springbootjpablog.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프핑 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {

    private Users users; //콤포지션

    public PrincipalDetail(Users users){
        this.users = users;
    }

    //계정 권한을 확인한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()-> { return "ROLE_"+users.getRole(); });
        return collectors;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return  users.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다.(true:만료가됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 만료되지 않았는지 리턴한다.(true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다.(true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화가(사용자)인지 리턴한다.(true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
