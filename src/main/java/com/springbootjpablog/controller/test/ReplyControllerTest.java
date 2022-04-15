package com.springbootjpablog.controller.test;

import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//무한 참조 문제 해결
@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/dummy/reply/{id}")
    public Board getBoard(@PathVariable Long id){
        return boardRepository.findById(id).get(); //jackson 라이브러리 실행 (오브젝트를 json으로 리턴)-> 모델의 getter실행
    }
}
