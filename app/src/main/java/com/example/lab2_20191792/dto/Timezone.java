package com.example.lab2_20191792.dto;

import java.io.Serializable;

public class Timezone implements Serializable {
    private String offset;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
