package com.springbootjpablog.controller.test;

import com.springbootjpablog.model.eunm.RoleType;
import com.springbootjpablog.model.entity.Board;
import com.springbootjpablog.model.entity.Users;
import com.springbootjpablog.repository.BoardRepository;
import com.springbootjpablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @Autowired //의존성 주입
    private BoardRepository boardRepository;


    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id) {

        try {
            userRepository.deleteById(id);
        } catch (IllegalStateException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id" + id;
    }


    @PutMapping("/dummy/user/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users requestUser) {

        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        Users user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("수정에 실패했습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        userRepository.save(user);
        return user;
    }

    //http://localhost:8080/dummy/user (요청)
    @GetMapping("/dummy/user")
    public List<Users> list() {
        return userRepository.findAll();
    }

    //한페이지당 2건 데이터를 리턴받아 볼 수 있도록 설정
    @GetMapping("/dummy/user/page")
    public Page<Users> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Users> users = userRepository.findAll(pageable);
        return users;
    }

    //한페이지당 2건 데이터를 리턴받아 볼 수 있도록 설정
    @GetMapping("/dummy/board/page")
    public Page<Board> boardPageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> board = boardRepository.findAll(pageable);
        return board;
    }


    //{id} 주소로 파마미터를 전달 받을 수 있음.
    //http://localhost:8080/dummy/user/1 (요청)
    @GetMapping("/dummy/user/{id}")
    public Users detail(@PathVariable Long id) {
        //user/1을 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null이 될 수 도 있기때문에
        //return null이 반환될수 있다 그러면 프러그램에서 문제가 발생할 수 있다.
        //Optional로 너의 Users 객체를 감싸서 가져오면 null인지 아닌지 판단해서 return 해줘

        //orElseGet : 값이 null일때만 호출된다.
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다."));
        return user;
    }


    //http://localhost:8080/dummy/join (요청)
    //http의 body에 username password email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(Users users) {
        System.out.println("id = " + users.getId());
        System.out.println("username = " + users.getUsername());
        System.out.println("password = " + users.getPassword());
        System.out.println("email = " + users.getEmail());
        System.out.println("Role = " + users.getRole());

        users.setRole(RoleType.USER);
        userRepository.save(users);

        return "화원가입이 완료되었습니다.";
    }

    @PostMapping("/dummy/join2")
    public String join2(String username, String password, String email) {

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        return "화원가입이 완료되었습니다.";
    }

    @PostMapping("/dummy/join3")
    public String join3(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        return "화원가입이 완료되었습니다.";
    }


}
