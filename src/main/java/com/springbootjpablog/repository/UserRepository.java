package com.springbootjpablog.repository;

import com.springbootjpablog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


//DAO
//자동으로 Bean등록이된다,
//@Repository 생략이 가능하다.
public interface UserRepository extends JpaRepository<Users,Long> {

}
