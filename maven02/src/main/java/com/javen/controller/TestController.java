package com.javen.controller;

import com.javen.model.User;
import com.javen.service.IRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private IRegisterService iRegisterService;

    @RequestMapping("/register1")
    public String toIndex()    //页面跳转
    {
        return "register";
    }

    @ResponseBody
    @RequestMapping("/register")
    public String register(HttpServletRequest request)     //注册功能
    {
        String usernameString=request.getParameter("username");
        String passwordString=request.getParameter("password");
        String petnameString=request.getParameter("petname");
        String emailString=request.getParameter("email");
        String typeString=request.getParameter("type");
        Integer typeInt=Integer.valueOf(typeString);
        System.out.println("1:"+usernameString+" 2:"+passwordString+" 3:"+petnameString+" 4:"+emailString+" 5:"+typeInt);
        User aaa=new User();
        aaa.setUsername(usernameString);
        aaa.setPassword(passwordString);
        aaa.setPetname(petnameString);
        aaa.setEmail(emailString);
        aaa.setType(typeInt);

        iRegisterService.ifregister(aaa);

        String data="";
        data = "{\"data\":\"添加成功\"}";
        return data;
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request)
    {
        String usernameString=request.getParameter("username");
        String passwordString=request.getParameter("password");
        String typeString=request.getParameter("type");
        Integer typeInt=Integer.valueOf(typeString);
        System.out.println("用户名:"+usernameString+" 密码:"+passwordString+" 类型："+typeInt);
        User aaa=new User();
        aaa.setUsername(usernameString);
        aaa.setPassword(passwordString);
        aaa.setType(typeInt);


        String data="";
        data = "{\"data\":\"登录成功\"}";
        return data;

    }


}

