package com.javen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/register1")
    public String toIndex()    //页面跳转
    {
        return "register";
    }

    @ResponseBody
    @RequestMapping("register")
    public String login(HttpServletRequest request)
    {
        String usernameString=request.getParameter("username");
        String passwordString=request.getParameter("password");
        String petnameString=request.getParameter("petname");
        String emailString=request.getParameter("email");
        String typeString=request.getParameter("type");
        Integer typeInt=Integer.valueOf(typeString);

        System.out.println("1:"+usernameString+" 2:"+passwordString+" 3:"+petnameString+" 4:"+emailString+" 5:"+typeInt);
        /*Register aaa=new Register();
        aaa.setUsername(usernameString);
        aaa.setPassword(passwordString);
        aaa.setPetname(petnameString);
        aaa.setEmail(emailString);
        aaa.setType(typeInt);

        Boolean abc=iRegisterService.ifregister(aaa);
        String data="";
        if (abc){
            data = "{\"data\":\"添加成功\"}";
        }else {
            data = "{\"data\":\"添加失败\"}";
        }
        return data;*/
        return null;
    }


}
