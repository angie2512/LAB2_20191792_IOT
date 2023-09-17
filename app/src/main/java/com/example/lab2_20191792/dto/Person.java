package com.example.lab2_20191792.dto;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    private List<Results> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    private Info info;
}
