package com.springbootjpablog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id                                                 //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 사용한다.
    private Long id;                                    //시퀀스

    @Column(nullable = false, length = 100)
    private String title;
    
    @Lob //대용량 데이터 사용
    private String content;      //섬머노트 라이브러리 사용

    @ColumnDefault("0")
    private Long count;          //조회수

    @ManyToOne(fetch = FetchType.EAGER )                  // Many=board , User=One
    @JoinColumn(name="userId")   //board 테이블에 User 테이블을 참조할 수 있는 FOREIGN KEY 자동으로 생성된다.
    private Users users;         //DB는 오브젝트를 저장할 수 없다.FK,자바는 오브젝트를 저장할 수 있다.

    /*
    Reply 클래스이 해당 필드를 가져온다.
    @ManyToOne                   // 하나의 게시글에 여러개의댓글을 가질수 있다.
    @JoinColumn(name="boardId")
    private Board board;
    */

    @OneToMany(mappedBy = "board" ,fetch = FetchType.EAGER)  //mappedBy 연관관계의 주인이 아니다(FK가 아니다) DB컬럼에 만들지 않아도된다. 명시
    private List<Reply> reply;

    @CreationTimestamp           // 저장시 시간이 자동 입력
    private Timestamp creatData;
}
