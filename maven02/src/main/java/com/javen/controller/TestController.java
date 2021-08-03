package com.javen.controller;

import com.javen.model.Goods;
import com.javen.model.User;
import com.javen.service.IRegisterService;
import com.javen.util.ObjtoLayJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private IRegisterService iRegisterService;

    @RequestMapping("/register1")    //跳转到注册页面
    public String toIndex()    //页面跳转
    {
        return "register";
    }
    @RequestMapping("/goods")    //跳转到商品管理页面
    public String goods()    //页面跳转
    {
        return "goods";
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
    public String login(HttpServletRequest request)     //登录功能
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
        Boolean temp=iRegisterService.ifLogin(aaa);
        System.out.println(temp);
        String data="";
        if (temp==true) {
            data = "{\"data\":\"登录成功\"}";
        }else{
            data = "{\"data\":\"登录失败\"}";
        }
        return data;
    }

    @ResponseBody
    @RequestMapping(value="/findAll", method= RequestMethod.GET,produces = "text/plain;charset=utf-8")
    public String findAll(HttpServletRequest request) throws Exception   //查询商品并返回
    {
        String pageString=request.getParameter("page");
        System.out.println("当前页数："+pageString);
        String limitString=request.getParameter("limit");
        System.out.println("限制条数："+limitString);
        Integer pageInteger=Integer.valueOf(pageString);
        Integer limitInteger=Integer.valueOf(limitString);
        //System.out.println(pageInteger+limitInteger);

        List<Goods> goods = iRegisterService.findAll(pageInteger,limitInteger);
        String[] colums = {"id","name","price","type","number","description","image"};
        String data = ObjtoLayJson.ListtoJson(goods, colums);
        System.out.println(data);
        return data;
    }

    @ResponseBody      //查询数据库中所有数据的条数
    @RequestMapping(value="/GetCount",method= RequestMethod.GET,produces = "text/plain;charset=utf-8")
    public String GetCount(HttpServletRequest request)
    {
        Integer count=iRegisterService.GetCount();
        System.out.println("cout: "+count);
        String data=String.valueOf(count);
        String json= "{"+"\"count\":"+data+"}";
        return json;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(HttpServletRequest request)   //添加功能
    {
        String nameString=request.getParameter("name");
        String priceString=request.getParameter("price");
        String typeString=request.getParameter("type");
        String numberString=request.getParameter("number");
        String descriptionString=request.getParameter("description");
        String imageString=request.getParameter("image");
        Integer numberInt=Integer.valueOf(numberString);
        System.out.println(nameString+" 添加 "+priceString+" "+typeString+" "+numberInt+" "+descriptionString+" "+imageString);
        Goods aaa=new Goods();
        aaa.setName(nameString);
        aaa.setPrice(priceString);
        aaa.setType(typeString);
        aaa.setNumber(numberInt);
        aaa.setDescription(descriptionString);
        aaa.setImage(imageString);
        Integer count=iRegisterService.insert(aaa);
        System.out.println(count);
        String data= "";
        data="{\"data\":\"添加成功\"}";
        return data;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request)   //删除功能
    {
        String idString=request.getParameter("id");
        Integer idInt= Integer.valueOf(idString);
        System.out.println("删除的id为："+idInt);
        iRegisterService.delete(idInt);
        return null;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(HttpServletRequest request)
    {
        String idString=request.getParameter("id");
        String nameString=request.getParameter("name");
        String priceString=request.getParameter("price");
        String typeString=request.getParameter("type");
        String numberString=request.getParameter("number");
        String descriptionString=request.getParameter("description");
        String imageString=request.getParameter("image");
        Integer idInt=Integer.valueOf(idString);
        Integer numberInt=Integer.valueOf(numberString);
        System.out.println(idInt+" "+nameString+" 修改 "+priceString+" "+typeString+" "+numberInt+" "+descriptionString+" "+imageString);
        Goods aaa=new Goods();
        aaa.setId(idInt);
        aaa.setName(nameString);
        aaa.setPrice(priceString);
        aaa.setType(typeString);
        aaa.setNumber(numberInt);
        aaa.setDescription(descriptionString);
        aaa.setImage(imageString);
        Integer count=iRegisterService.update(aaa);
        System.out.println(count);
        String data= "";
        data="{\"data\":\"添加成功\"}";
        return data;
    }
}

