package com.javen.dao;

import com.javen.model.User;

import java.util.List;

public interface RegisterDao {
    public Integer register(User user);

    public List<User> login(User user);
}
