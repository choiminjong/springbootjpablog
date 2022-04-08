package com.springbootjpablog.service;

import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트스캔을 통해서 Bean 등록을 해줍니다. IOC를 대신 해줍니다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void accout(Users users) {
        users.setPassword((passwordEncoder.encode(users.getPassword())));
        System.out.println("users = " + users);
        userRepository.save(users);
    }

    @Transactional
    public void userUpdate(Users user) {
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //select를해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기위해서
        //영속화된 오브젝트를 변경하면 자동으로 DB에 Update문을 실행한다.

        Users persistance = userRepository.findById(user.getId())
                .orElseThrow(()-> {
                    return new IllegalArgumentException("회원 찾기 실패");
                });
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        //회원수정 함수 종료시=서비스 종료=트랜잭션 종료=commit이 자동으로 실행됩니다.
        //영속화된 persistance 객체의 변화가 감지되면 더디체킹이 되어 변환된 데이터를 업데이트문을 실행한다.
    }
}
