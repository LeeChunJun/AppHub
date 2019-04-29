package com.licj.apphub.commons.mvp;

/**
 * Created by licj on 2018/3/19.
 */

public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();
}
