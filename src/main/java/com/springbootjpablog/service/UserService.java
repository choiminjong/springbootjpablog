package com.springbootjpablog.service;

import com.springbootjpablog.model.Users;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트스캔을 통해서 Bean 등록을 해줍니다. IOC를 대신 해줍니다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void accout(Users users) {
        userRepository.save(users);
    }

}
