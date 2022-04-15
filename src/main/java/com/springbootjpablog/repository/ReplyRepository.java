package com.springbootjpablog.repository;

import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

}
