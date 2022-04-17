package com.springbootjpablog.repository;

import com.springbootjpablog.model.dto.ReplySaveReqestDto;
import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO reply(userId,boardId,content) VALUES(?1,?2,?3)", nativeQuery=true)
    int replySave(Long userId, Long boardId, String content);
    //리턴값을 업데이트된 행의 개수를 리턴합니다.
}
