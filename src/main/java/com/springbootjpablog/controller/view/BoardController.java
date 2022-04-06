package com.springbootjpablog.controller.view;

import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.security.auth.PrincipalDetail;
import com.springbootjpablog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boards = boardService.list(pageable);

        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 8);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 8);

        model.addAttribute("boards",boards);
        model.addAttribute("endPage",endPage);
        model.addAttribute("startPage", startPage);

        return "index";
    }

    @GetMapping("/board/form")
    public String saveForm( Model model, @RequestParam(required = false) Long id){

        if(id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardService.findById(id);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

}
