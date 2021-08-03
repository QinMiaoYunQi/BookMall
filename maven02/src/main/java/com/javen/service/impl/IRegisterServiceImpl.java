package com.javen.service.impl;


import com.javen.dao.RegisterDao;
import com.javen.model.Goods;
import com.javen.model.User;
import com.javen.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IRegisterServiceImpl implements IRegisterService {
    @Autowired
    private RegisterDao abc;


    public Integer ifregister(User user) {
        return this.abc.register(user);
    }

    /*public Boolean ifLogin(User user) {
        List<String[]> users=abc.login(user);
        if(users.size()==1)
        {
            return true;
        }else{
            return false;
        }
    }*/

    public User LoginSession(User user) {
        User users=abc.login(user);
        return users;
    }

    public List<Goods> findAll(Integer pageIndex11, Integer pageSize11) {
        Integer index=(pageIndex11-1) * pageSize11;
        Integer size=pageSize11;
        return this.abc.findAll(index,size);
    }

    public Integer GetCount() {
        return this.abc.GetCount();
    }

    public Integer insert(Goods goods) {
        return this.abc.insert(goods);
    }

    public Integer delete(Integer id) {
        return this.abc.delete(id);
    }

    public Integer update(Goods goods) {
        return this.abc.update(goods);
    }
}
