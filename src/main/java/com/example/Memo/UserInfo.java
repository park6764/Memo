package com.example.Memo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;

public class UserInfo {
    private String name;
    private LocalDate birth;
    private Auth auth;
    private Optional<LinkedList<Memo>> memo;

    public UserInfo(String name, LocalDate birth, Auth auth, Optional<LinkedList<Memo>> memo) {
        this.name = name;
        this.birth = birth;
        this.auth = auth;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Auth getAuth() {
        return auth;
    }

    public Optional<LinkedList<Memo>> getMemo() {
        return memo;
    }
}