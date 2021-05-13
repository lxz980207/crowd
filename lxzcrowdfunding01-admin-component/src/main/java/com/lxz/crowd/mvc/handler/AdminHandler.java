package com.lxz.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.lxz.crodw.entity.Admin;
import com.lxz.crowd.service.api.AdminService;
import com.lxz.crowd.util.CrowdUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPassword") String password,
            HttpSession httpSession
    ){
        Admin admin = adminService.getAdminByloginAcct(loginAcct,password);
        httpSession.setAttribute("loginAdmin",admin);
//      避免跳转到后台主页面再刷新浏览器导致  重复提交登录表单 （重新执行登录逻辑验证） ，重定向到目标页面
        return "redirect:/admin/to/main/page.html";
//        return "admin-main";
    }
    @RequestMapping("admin/do/logout.html")
    public String doLogout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            ModelMap modelMap
    ){
        //调用service获取PageInfo
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute("pageInfo",pageInfo);
        return "admin-page";
    }
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
            ){
        adminService.remove(adminId);
        //参数从何而来？？
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
    @RequestMapping("admin/save.html")
    public String add (HttpServletRequest request){
        String loginAcct = request.getParameter("loginAcct");
        String userPswd = request.getParameter("userPswd");
        String s = CrowdUtile.md5(userPswd);
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        Admin admin = new Admin(null, loginAcct, s, userName, email, null);
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("admin/to/edit/page.html")
    public String edit(
            @RequestParam("adminId") Integer adminId,
            HttpSession session
    ){
        Admin adminByStore = adminService.pickOne(adminId);
        adminService.remove(adminId);
        session.setAttribute("adminByStore",adminByStore);
        return "admin-add";
    }
}
