package com.springbootjpablog.repository;

import com.springbootjpablog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


//DAO
//자동으로 Bean등록이된다,
//@Repository 생략이 가능하다.
public interface UserRepository extends JpaRepository<Users,Long> {
    //JPA Naming 전략
    //SELECT * FROM users WHERE username=?1 AND password = ?2;
    Users findByUsernameAndPassword(String username , String password);

    //@Query(value="SELECT * FROM users WHERE username=?1 AND password = ?2",nativeQuery = true)
    //Users login(String username , String password);


}
