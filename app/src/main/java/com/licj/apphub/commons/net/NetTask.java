package com.licj.apphub.commons.net;

public interface NetTask<T> {
    void execute(T data, LoadTasksCallBack callBack);
}
