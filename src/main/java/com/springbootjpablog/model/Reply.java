package com.springbootjpablog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

    @Id                                                 //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 사용한다.
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne                   // 하나의 게시글에 여러개의댓글을 가질수 있다.
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne            // 하나의 유저는 여러개 댓글을 작성할 수 있따.
    @JoinColumn(name="userId")
    private Users users;

    @CreationTimestamp           // 저장시 시간이 자동 입력
    private Timestamp creatData;
}
