package com.lxz.crowd.mvc.handler;

import com.lxz.crodw.entity.Admin;
import com.lxz.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class testHandler {
    @Autowired
    AdminService service;
    @RequestMapping("/ssm.html")
 public String findAll(ModelMap modelMap){
     List<Admin> adminList =service.findAll();
     modelMap.addAttribute("adminList",adminList);
        System.out.println(10/0);
//        String a=null;
//        System.out.println(a.length());
     return "hh";

 }
    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String Test(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(testHandler.class);
        for (Integer a:array) {
           logger.info(String.valueOf(a));

        }
        return "success";
    }

}
