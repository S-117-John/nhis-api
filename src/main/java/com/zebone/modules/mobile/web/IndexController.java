package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mobile/index")
public class IndexController {

    @RequestMapping("")
    public String index(String id, Model model){
        model.addAttribute("id",id);
        return "modules/mobile/index";
    }
}
