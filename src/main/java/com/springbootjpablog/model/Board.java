package com.springbootjpablog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id     //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 사용한다.
    private Long id; //시퀀스

    @Column(nullable = false, length = 100)
    private String title;
    
    @Lob //대용량 데이터 사용
    private String content; //섬머노트 라이브러리 사용

    @ColumnDefault("0")
    private Long count; //조회수

    @ManyToOne  // Many=board , User=One
    @JoinColumn(name="userId")   //board 테이블에 User 테이블을 참조할 수 있는 FOREIGN KEY 자동으로 생성된다.
    private User user; //DB는 오브젝트를 저장할 수 없다.FK,자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp creatData;

}
