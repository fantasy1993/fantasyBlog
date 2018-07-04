package com.fantasy.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fantasy on 2017/12/24.
 */
@Controller
public class LoginController {

    //跳转登录页面
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/register")
    public String register (){
        return "register";
    }

    //跳转到登录以后的页面
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/article")
    public String article(){
        return "article";
    }

    @RequestMapping(value = "/articleContent")
    public String articleContent(){
        return "articleContent";
    }

    @RequestMapping(value = "/writeArticles")
    public String writeArticles(){
        return "writeArticles";
    }

    @RequestMapping(value = "/editArticles")
    public String editArticles(){
        return "editArticles";
    }

    @RequestMapping(value = "/backendIndex")
    public String backendIndex(){
        return "backendIndex";
    }

    @RequestMapping(value = "/tablesDatatable")
    public String tablesDatatable(){
        return "tablesDatatable";
    }


    //退出登录
   // @RequestMapping(value = "/logout")
}
