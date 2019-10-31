package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
public class HelloSpringController {
    String message = "Welcome to Spring MVC!";
 
    //��¼
    @RequestMapping("/login")
    public String showMessage() {
        System.out.println("this is a simple update");
        System.out.println("��һ�δ�����֧");
       
        return "login";
    }
    
    //����¼
    @RequestMapping("/checkLogin")
    public String checkLogin(Model model,String username,String password) {
    	// �ж��û����������Ƿ�ƥ��
    	if(username.equals("zhangsan")&&password.equals("666")){ // ƥ�䣬������ҳ
    		return "index";
    	} else // ��ƥ��  ���ص�¼ҳ��
    		return "login";
        
    }
    
    //��ҳ
    @RequestMapping("/index")
    public String index(Model model,String username) {
    	
        return "index";
    }
    
    //ת��
    @RequestMapping("/transfer")
    public String transfer(Model model,String username) {
    	
        return "success";
    }
    
    //ȡ��
    @RequestMapping("/withdraw")
    public String withdraw(Model model,String username) {
    	
        return "success";
    }
}