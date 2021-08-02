package com.javen.service.impl;


import com.javen.dao.RegisterDao;
import com.javen.model.User;
import com.javen.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRegisterServiceImpl implements IRegisterService {
    @Autowired
    private RegisterDao registerDao;


    public Integer ifregister(User user) {
        return this.registerDao.register(user);
    }
}
