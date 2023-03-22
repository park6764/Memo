package com.example.Memo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {
    LinkedList<UserInfo> users = new LinkedList<>();
    LinkedList<Memo> memos = new LinkedList<>();

    @GetMapping("/")
    public String view() {
        return "Please Login";
    }

    @GetMapping("/signUp")
    public String signUp(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "birth") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birth,
        @RequestParam(name = "id") String id,
        @RequestParam(name = "pw") String pw
    ) {
        var a = findElem(i -> i.getAuth().getId().equals(id), users);
        if(a.get().getAuth().getId().equals(id)) return "이미 존재하는 id 입니다.";
        else {
            users.add(new UserInfo(name, birth, new Auth(id, pw), Optional.empty()));
            return "\"" + name + "\"" + " 님 환영합니다.";
        }
    }

    @GetMapping("/findId")
    public String findId(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "birth") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birth
    ) {
        final var findUserInfo = findElem(u -> u.getName().equals(name) && u.getBirth().equals(birth), users);
        
        if(findUserInfo.isEmpty()) return "잘못된 정보 또는 회원이 아닙니다."; // null값이겠지?
        else return "회원님의 id는 " + "\"" + findUserInfo.get().getAuth().getId() + "\"" + " 입니다.";
    }

    @GetMapping("/findPw")
    public String findPw(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "id") String id
    ) {
        final var findUserInfo = findElem(u -> u.getName().equals(name) && u.getAuth().getId().equals(id), users);
        
        if(findUserInfo.isEmpty()) return "잘못된 정보 또는 회원이 아닙니다.";
        else return "회원님의 pw는 " + "\"" + findUserInfo.get().getAuth().getPw() + "\"" + " 입니다.";
    }

    private <T> Optional<T> findElem(Function<T, Boolean> p, LinkedList<T> ls) {
        if(ls.isEmpty()) return Optional.empty();
        else {
            var l = (LinkedList<T>) ls.clone();
            var a = l.removeFirst(); // a and l (l -> l - a )

            if(p.apply(a)) return Optional.of(a);
            else return findElem(p, l);
        }
    }

    @GetMapping("/login")
    public String login(
        @RequestParam(name = "id") String id,
        @RequestParam(name = "pw") String pw
    ) {
        final var u = findElem(i -> i.getAuth().getId().equals(id) && i.getAuth().getPw().equals(pw), users);
        
        if(u.isPresent()) return "\"" + u.get().getName() + "\"" + " 님 환영합니다.";
        else return "잘못된 정보 또는 회원이 아닙니다.";
    }

    @GetMapping("/login/addMemo")
    public LinkedList<Memo> addMeme(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "text", required = false) String text 
    ) {
        final var t = Optional.ofNullable(text);

        memos.add(new Memo(name, t));
        return memos;
    }

    @GetMapping("/login/delete")
    public LinkedList<Memo> delMemo(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "text", required = false) String text
    ) {
        final var t = Optional.ofNullable(text);

    }
}

/*
 * 회원가입_아이디, 비번 찾기, 비밀번호 변경? Q. setter만들면 어떻게 변경시킬 수 있지? 그냥 값을 변경시키나?
 * memo_ 추가, 삭제, 변경
 */