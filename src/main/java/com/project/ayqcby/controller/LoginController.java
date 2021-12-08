package com.project.ayqcby.controller;

import com.project.ayqcby.domain.Menu;
import com.project.ayqcby.domain.User;
import com.project.ayqcby.service.MenuService;
import com.project.ayqcby.service.UserService;
import com.project.ayqcby.tools.CryptoUtil;
import com.project.ayqcby.tools.MD5;
import com.project.ayqcby.tools.ResponseResult;
import com.project.ayqcby.tools.SqlFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Object loginIn(User user) throws Exception{
        user.setYhm(SqlFilter.validation(MD5.jieMi(user.getYhm())));
        user.setKl("{MD5}"+MD5.BASE64AndMD5(SqlFilter.validation(MD5.jieMi(user.getKl()))));
        List<User> u = userService.queryUser(user);
        if (u.size() > 0){
            session.setAttribute("menu_username", u.get(0).getXm());
            session.setAttribute("menu_userId", u.get(0).getYhm());
            session.setAttribute("menu_userJs", u.get(0).getJsdm());
            loadUserData(u.get(0));
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

    @PostMapping("/login2")
    @ResponseBody
    public Object loginVue(@RequestBody User user) throws Exception{
        JSONObject json = new JSONObject();
        user.setYhm(SqlFilter.validation(user.getYhm()));
        user.setKl("{MD5}"+MD5.BASE64AndMD5(SqlFilter.validation(user.getKl())));
        List<User> u = userService.queryUser(user);
        if (u.size() > 0){
            json.put("code", 0);
            String token = CryptoUtil.encodeSrc(u.get(0).getYhm()+"&@&"+new Date().getTime()/1000);
            json.put("token", token);
            return json;
        }else{
            return ResponseResult.error();
        }
    }

    @GetMapping("/main")
    public String main(){
        if (null != session.getAttribute("menu_userId")){
            return "main";
        }else{
            session.invalidate();
            return "index";
        }
    }

    @GetMapping("/home")
    public String home(){
        String role = (String) session.getAttribute("menu_userJs");
        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }

    public void loadUserData(User user){
        JSONObject js = new JSONObject();
        JSONArray arr = new JSONArray();
        user.setYhlx("0");
        List<Menu>la = menuService.loadDataToMenu(user);
        for (Menu m1 : la) {
            js.put("mc", m1.getMc());
            js.put("path", m1.getPath());
            js.put("icon", m1.getIcon());

            JSONObject js1 = new JSONObject();
            JSONArray arr1 = new JSONArray();
            user.setYhlx(m1.getId());
            List<Menu>lb = menuService.loadDataToMenu(user);
            js.put("num", lb.size());
            for (Menu m2 : lb) {
                js1.put("mc", m2.getMc());
                js1.put("path", m2.getPath());
                js1.put("icon", m2.getIcon());

                JSONObject js2 = new JSONObject();
                JSONArray arr2 = new JSONArray();
                user.setYhlx(m2.getId());
                List<Menu>lc = menuService.loadDataToMenu(user);
                js1.put("num", lc.size());
                for (Menu m3 : lc) {
                    js2.put("mc", m3.getMc());
                    js2.put("path", m3.getPath());
                    js2.put("icon", m3.getIcon());
                    arr2.add(js2);
                }
                js1.put("list", arr2);
                arr1.add(js1);
            }
            js.put("list", arr1);
            arr.add(js);
        }
        session.setAttribute("main_menu", arr);
    }
}
