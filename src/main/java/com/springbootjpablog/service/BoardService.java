package com.springbootjpablog.service;

import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Reply;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.repository.BoardRepository;
import com.springbootjpablog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void write(Board board, Users user) {
        board.setCount(0);
        board.setUsers(user);
        boardRepository.save(board);
    }

    @Transactional
    public void replyWrite( Users user, Long boardId, Reply requestReply) {
        //boardId만으로 오브젝트의 setBoard를 수정할 수 없기때문에
        //Board 오브젝트를 검색해서 리턴받은 오브젝트를 setBoard 변경합니다.
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패  : 게시글 id를 찾 을 수 없스니다.");
                });

        requestReply.setUsers(user);
        requestReply.setBoard(board);
        replyRepository.save(requestReply);
    }

    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
            return boardRepository.findById(id)
                .orElseThrow(()-> {
                return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
            });
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
