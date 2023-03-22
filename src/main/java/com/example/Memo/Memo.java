package com.example.Memo;

import java.util.Optional; 

public class Memo {
    private String name;
    private Optional<String> text;

    public Memo(String name, Optional<String> text) {
        this.name = name;
        this.text = text;
    }
    
    public String getName() {
        return name;
    }
    public Optional<String> getText() {
        return text;
    }
}
