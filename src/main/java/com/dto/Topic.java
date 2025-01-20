package com.dto;

public class Topic {
    private String name="";
    private String lectureFile="";

    public void setId(int id) {
        this.id = id;
    }

    private int id=0;

    public Topic(int id,String name, String lectureFile) {
        this.id=id;
        this.name = name;
        this.lectureFile = lectureFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLectureFile() {
        return lectureFile;
    }

    public void setLectureFile(String lectureFile) {
        this.lectureFile = lectureFile;
    }

    public int getId() {
        return id;
    }
}
