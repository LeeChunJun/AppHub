package com.licj.wehome.bean;

import java.io.Serializable;

public class VRHomeBean implements Serializable {
    private String title;

    public VRHomeBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
