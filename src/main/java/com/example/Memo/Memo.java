package com.example.Memo;

import java.util.Optional;

public class Memo {
    private String name;
    private Optional<String> text;

    public Memo(String name, Optional<String> text) {
        this.name = name;
        this.text = text;
    }

    public String getMemoName() {
        return name;
    }

    public Optional<String> getMemoText() {
        return text;
    }

    @Override
    public String toString() {
        return name + " : " + text;
    }
}
