package com.springbootjpablog.service;

import com.springbootjpablog.model.Users;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//스프링이 컴포넌트스캔을 통해서 Bean 등록을 해줍니다. IOC를 대신 해줍니다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int accout(Users users) {

        try {
            userRepository.save(users);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e = " + e);
        }
        return  -1;
    }
}
