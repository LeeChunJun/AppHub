package com.licj.apphub.commons.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by licj on 2018/3/8.
 */

public class BaseEntity<E> extends BaseModel {
    
    @SerializedName("data")
    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
