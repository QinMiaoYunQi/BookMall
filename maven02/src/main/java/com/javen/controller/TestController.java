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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @RequestMapping("/show")   //查看商品信息
    public String show(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Integer id=(Integer) session.getAttribute("id");
        System.out.println("show.id: "+id);
        if (id !=null)
        {
            return "show";
        }else {
            return "index";
        }
    }
    @RequestMapping(value = "/admin", method=RequestMethod.GET)    //跳转到商品管理页面
    public String admin(HttpServletRequest request) throws Exception    //页面跳转
    {
        //return "admin";
        HttpSession session=request.getSession();
        Integer id=(Integer) session.getAttribute("id");
        System.out.println("id: "+id);
        if (id==null){
            System.out.println("未登录");
            return null;
        }else{
            System.out.println("已登录");
            return "admin";
        }
    }
    @RequestMapping("/user")    //跳转到用户浏览页面
    public String user()    //页面跳转
    {
        return "user";
    }
    @RequestMapping("/gwc")
    public String gwc()
    {
        return "cart";
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
    @RequestMapping(value="/login",method= RequestMethod.POST)
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
        //Boolean temp=iRegisterService.ifLogin(aaa);
        User temp=iRegisterService.LoginSession(aaa);
        System.out.println(temp);
        System.out.println(temp.getId());
        Integer abc=temp.getId();
        Integer type=temp.getType();
        System.out.println("id : "+abc);
        System.out.println("type :"+type);
        String data="";
        if (type == 0) {
            data = "{\"message\":\"用户账号\"}";

            HttpSession session=request.getSession();
            session.setAttribute("id",abc);

            Integer id=(Integer) session.getAttribute("id");
            System.out.println("用户id!!!!:  "+id);
        }else if (type == 1){
            data = "{\"message\":\"管理账号\"}";
            HttpSession session=request.getSession();
            session.setAttribute("id",abc);
            Integer id=(Integer) session.getAttribute("id");
            System.out.println("管理员id!!!!:  "+id);
        }else{
            data = "{\"message\":\"查无此人\"}";
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
    public String update(HttpServletRequest request)   //修改功能
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

    @ResponseBody
    @RequestMapping("/showid")
    public String showid(HttpServletRequest request, HttpServletResponse response)    //将传入的id设为cookie
    {
        String idString=request.getParameter("id");
        Integer idInt=Integer.valueOf(idString);
        System.out.println("当前选中的id为："+idString);
        Cookie id=new Cookie("id",idString);
        response.addCookie(id);
        String data= "";
        data="{\"data\":\"id传入成功\"}";
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/selectById", method= RequestMethod.GET,produces = "text/plain;charset=utf-8") //根据id查询，显示商品具体信息
    public String selectById(HttpServletRequest request) throws Exception {
        String idString=request.getParameter("id");
        System.out.println("cookie值："+idString);
        Integer idInt=Integer.valueOf(idString);
        List<Goods> goods=iRegisterService.selectById(idInt);
        String[] colums = {"id","name","price","type","number","description","image"};
        String data = ObjtoLayJson.ListtoJson(goods, colums);
        System.out.println("返回的data: "+data);
        return data;
    }

    @ResponseBody
    @RequestMapping("/Cart")
    public String Cart(HttpServletRequest request)
    {
        String name=request.getParameter("name");
        System.out.println(name);
        //从session处获取购物车
        Map<String,Integer> map=(Map<String, Integer>) request.getSession().getAttribute("cart");
        Integer count=null;

        if (map==null)
        {
            map=new HashMap<String, Integer>();//创建购物车
            request.getSession().setAttribute("cart",map);  //把购物车放入session
            count=1;
        }else {
            count=map.get(name);   //购物车不为空 继续判断购物车是否有该商品

            if (count==null)  //购物车中没有该商品
            {
                count=1;
            }else {
                count++;
            }
        }
        map.put(name,count);  //将商品放入购物车
        System.out.println(map);
        System.out.println("map.key: "+map.keySet( ));
        System.out.println("map.value: "+map.values());
        return null;
    }

    @ResponseBody
    @RequestMapping("/CartShow")
    public String CartShow(HttpServletRequest request)
    {
        /*Map<String,Integer> cart=(Map<String, Integer>) request.getSession().getAttribute("cart");
        for (String map1 : cart.keySet())    //循环输出key和value值
        {
            String mm=cart.get(map1).toString();
            Integer aaa=Integer.valueOf(map1);
            List<Goods> goods=iRegisterService.selectById(aaa);
            System.out.println(goods);
            //System.out.println(map1+">>>>>>>>>>"+mm);
        }
        return null;*/
        //从session处获取购物车
        Map<String,Integer> cart=(Map<String, Integer>) request.getSession().getAttribute("cart");
        Integer count=0;
        Integer aaa=0;
        for (String map1 : cart.keySet())    //循环输出key和value值
        {
            //String mm=cart.get(map1).toString();
            aaa++;
        }
        System.out.println("key条数："+aaa);
        String data = "[{\"status\":0}, {\"message\": \"成功\" }, {\"count\": 1000},{\"rows\":{\"item\":[";
        java.util.Iterator it = cart.entrySet().iterator();
        while(it.hasNext()){
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            entry.getKey();      //返回对应的键
            entry.getValue();   //返回对应的值
            count++;
            data+="{\"name\":\""+entry.getKey()+"\","+"\"count\":"+entry.getValue()+"}";
            if (count != aaa){
                data+=",";
            }
        }
        data+="]}}]";
        System.out.println(data);
        /*System.out.println("map: "+cart.keySet());
        for (String map1 : cart.keySet())    //循环输出key和value值
        {
            String mm=cart.get(map1).toString();
            System.out.println(map1+">>>>>>>>>>"+mm);
        }*/
        return data;
    }
}

