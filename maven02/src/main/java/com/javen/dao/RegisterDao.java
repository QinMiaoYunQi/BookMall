package com.javen.dao;

import com.javen.model.Goods;
import com.javen.model.User;

import java.util.List;

public interface RegisterDao {
    public Integer register(User user);

    public List<User> login(User user);

    List<Goods> findAll(Integer pageIndex,Integer pageSize);

    Integer GetCount();

    Integer insert(Goods goods);

    Integer delete(Integer id);

    Integer update(Goods goods);
}
