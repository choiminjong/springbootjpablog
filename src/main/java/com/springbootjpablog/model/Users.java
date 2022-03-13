package com.springbootjpablog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> JAVA Object기반으로 자동으로 테이블로 생성한다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity          //User 클래스가 데이터베이스 테이블을 생성이된다.
//@DynamicInsert  //insert 이벤트시 null 필드를 제외시켜준다.
public class Users {

    @Id                                                 //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 사용한다.
    private Long id;                                    //시퀀스

    @Column(nullable = false, length = 30)
    private String username;                            //아이디

    @Column(nullable = false, length = 100)             //패스워드를 해쉬하면 늘어나기때문에
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB는 RoleType 이라는 포맷이없다
    @Enumerated(EnumType.STRING)
    private RoleType role;                              // Enum을 쓰는게 좋다.//AMDIN,USER
    //private String role;

    @CreationTimestamp                                  // 저장시 시간이 자동 입력
    private Timestamp creatData;
}
