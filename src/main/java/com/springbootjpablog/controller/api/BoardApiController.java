package com.springbootjpablog.controller.api;

import com.springbootjpablog.model.dto.ReplySaveReqestDto;
import com.springbootjpablog.model.dto.ResponseDto;
import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Reply;
import com.springbootjpablog.security.auth.PrincipalDetail;
import com.springbootjpablog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> write(@RequestBody Board board,
                                      @AuthenticationPrincipal PrincipalDetail principal){

        boardService.write(board,principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteBoard(@PathVariable Long id){
        boardService.boardDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

//    @PostMapping("/api/board/{boardId}/reply")
//    public ResponseDto<Integer> replyWrite(@PathVariable Long boardId,
//                                           @RequestBody Reply reply,
//                                           @AuthenticationPrincipal PrincipalDetail principal){
//        boardService.replyWrite(principal.getUser(),boardId,reply);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replyWrite(@RequestBody ReplySaveReqestDto replySaveReqestDto){
        boardService.replyWrite(replySaveReqestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable Long replyId){
        boardService.replyDelete(replyId);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
