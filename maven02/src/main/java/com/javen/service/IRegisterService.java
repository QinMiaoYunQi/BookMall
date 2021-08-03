package com.javen.service;

import com.javen.model.Goods;
import com.javen.model.User;

import java.util.List;

public interface IRegisterService {
    //所有你想要实现的方法一定要在这个地方定义

    public Integer ifregister(User user);

    public Boolean ifLogin(User user);

    List<Goods> findAll(Integer pageIndex11,Integer pageSize11);

    Integer GetCount();

    Integer insert(Goods goods);

    Integer delete(Integer id);

    Integer update(Goods goods);
}
