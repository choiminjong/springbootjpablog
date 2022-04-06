package com.springbootjpablog.service;

import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.repository.BoardRepository;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, Users user) {

        board.setCount(0);
        board.setUsers(user);
        boardRepository.save(board);

    }
}
