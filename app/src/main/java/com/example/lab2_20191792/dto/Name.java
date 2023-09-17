package com.example.lab2_20191792.dto;

import java.io.Serializable;

public class Name implements Serializable {
    private String title;
    private String first;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    private String last;
}
