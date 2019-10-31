package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
public class HelloSpringController {
    String message = "Welcome to Spring MVC!";
 
    //登录
    @RequestMapping("/login")
    public String showMessage() {
        System.out.println("this is a simple update");
        System.out.println("第一次创建分支");
       
        return "login";
    }
    
    //检查登录
    @RequestMapping("/checkLogin")
    public String checkLogin(Model model,String username,String password) {
    	// 判断用户名和密码是否匹配
    	if(username.equals("zhangsan")&&password.equals("666")){ // 匹配，进入主页
    		return "index";
    	} else // 不匹配  返回登录页面
    		return "login";
        
    }
    
    //首页
    @RequestMapping("/index")
    public String index(Model model,String username) {
    	
        return "index";
    }
    
    //转账
    @RequestMapping("/transfer")
    public String transfer(Model model,String username) {
    	
        return "success";
    }
    
    //取款
    @RequestMapping("/withdraw")
    public String withdraw(Model model,String username) {
    	
        return "success";
    }
}