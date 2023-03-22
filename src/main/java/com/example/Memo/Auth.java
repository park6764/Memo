package com.example.Memo;

public class Auth {
    private String id;
    private String pw;

    public Auth(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
