package com.dto;

public class Example {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String md;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String fxml) {
        this.md = fxml;
    }

    public Example(int id,String name, String md) {
        this.id=id;
        this.name = name;
        this.md = md;
    }

}
