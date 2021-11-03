package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("epidemic")
public class EpidemicController extends BaseController {

    @RequestMapping("index")
    public String toindex(){
        return "epidemicdata/sale_chance";
    }

    @RequestMapping("data")
    public String todata(){
        return "epidemicdata/data";
    }
}
