package com.javen.service.impl;


import com.javen.dao.RegisterDao;
import com.javen.model.Register;
import com.javen.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRegisterServiceImpl implements IRegisterService {
    @Autowired
    private RegisterDao registerDao;


    public Integer ifregister(Register register) {
        return this.registerDao.register(register);
    }
}
