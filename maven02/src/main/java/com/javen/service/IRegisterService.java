package com.javen.service;

import com.javen.model.User;

public interface IRegisterService {
    //所有你想要实现的方法一定要在这个地方定义

    public Integer ifregister(User user);

    public Boolean ifLogin(User user);

}
