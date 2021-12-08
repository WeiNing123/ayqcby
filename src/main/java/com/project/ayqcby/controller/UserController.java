package com.project.ayqcby.controller;

import com.project.ayqcby.domain.User;
import com.project.ayqcby.service.UserService;
import com.project.ayqcby.tools.MD5;
import com.project.ayqcby.tools.ResponseResult;
import com.project.ayqcby.tools.TokenValidation;
import com.project.ayqcby.tools.layuipage.LayuiPageRequest;
import com.project.ayqcby.tools.layuipage.LayuiPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @PostMapping("/modifypassword")
    @ResponseBody
    public Object modifypassword(String id, String ids) throws Exception{
        String yhm = (String) session.getAttribute("menu_userId");
        User user = new User();
        user.setKl("{MD5}"+ MD5.BASE64AndMD5(id));
        user.setYhm(yhm);
        List<User> u = userService.queryUser(user);
        if (u.size() > 0){
            user.setKl("{MD5}"+ MD5.BASE64AndMD5(ids));
            userService.modifypassword(user);
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @GetMapping("/yhgl")
    public String yhgl(){
        return "user/yhgl";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object list(User user, LayuiPageRequest pageQuery){
        LayuiPageResult result = userService.findPage(pageQuery,user);
        return result;
    }

    @PostMapping("/vueList")
    @ResponseBody
    public Object vueList(@RequestBody User user,HttpServletRequest request){
        String[] vali = TokenValidation.validation(request);
        if(vali[2].equals("-1") || vali[1].equals("-1")){
            return ResponseResult.lose();
        }
        System.out.println("userId============================================>>>>"+vali[0]);
        LayuiPageRequest pageQuery = new LayuiPageRequest(request.getIntHeader("page"), request.getIntHeader("limit"));
        LayuiPageResult result = userService.findPage(pageQuery, user);
        return result;
    }

    @PostMapping("/role")
    @ResponseBody
    public Object role(){
        List<User> result = userService.loadJs();
        return result;
    }

    @PostMapping("/initPassword")
    @ResponseBody
    public Object initPassword(User user) throws Exception{
        String kl = "{MD5}"+MD5.BASE64AndMD5("123456");
        user.setKl(kl);
        userService.modifypassword(user);
        return ResponseResult.msg("操作成功，密码初始化为123456，请通知该用户尽快修改密码！");
    }

    @PostMapping("/delUser")
    @ResponseBody
    public Object delUser(String yhm){
        userService.delUser(yhm);
        return ResponseResult.success();
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Object addUser(User user) throws Exception{
        String kl = "{MD5}"+MD5.BASE64AndMD5("123456");
        user.setKl(kl);
        userService.addUser(user);
        return ResponseResult.success();
    }

    @PostMapping("/editUser")
    @ResponseBody
    public Object editUser(User user){
        userService.editUser(user);
        return ResponseResult.success();
    }
}
