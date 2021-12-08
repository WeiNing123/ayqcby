package com.project.ayqcby.controller;

import com.project.ayqcby.domain.Fwlxb;
import com.project.ayqcby.service.FwlxbService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/fwlx")
public class FwlxbController {

    @Autowired
    FwlxbService fwlxbService;

    @GetMapping("/load")
    @ResponseBody
    public Object loadData(Fwlxb fwlxb){
        JSONArray arr = new JSONArray();
        JSONObject js = new JSONObject();
        List<Fwlxb>list = fwlxbService.loadData(fwlxb);
        for (Fwlxb fwlx : list){
            js.put("", fwlx.getId());
            js.put("", fwlx.getId());
            js.put("", fwlx.getId());
            js.put("", fwlx.getId());
        }
        return list;
    }
}