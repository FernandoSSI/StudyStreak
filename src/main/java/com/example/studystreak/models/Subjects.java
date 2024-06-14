package com.example.studystreak.models;

import java.io.Serializable;

public class Subjects implements Serializable {

    private int id;
    private String name;
    private int qstCount;

    public Subjects() {
    }

    public Subjects(int id, String name, int qstCount) {
        this.id = id;
        this.name = name;
        this.qstCount = qstCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQstCount() {
        return qstCount;
    }

    public void setQstCount(int qstCount) {
        this.qstCount = qstCount;
    }
}
