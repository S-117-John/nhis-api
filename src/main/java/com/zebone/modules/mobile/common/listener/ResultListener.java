package com.zebone.modules.mobile.common.listener;

public interface ResultListener {

    void success(Object object);

    void error(Object object);

    void exception(Object object);
}
