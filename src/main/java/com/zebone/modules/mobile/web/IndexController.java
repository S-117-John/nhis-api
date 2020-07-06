package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@ApiIgnore
@Controller
@RequestMapping("mobile/index")
public class IndexController {

    @RequestMapping("")
    public String index(String id, Model model, HttpSession session){
        session.setAttribute("pkPv",id);
        model.addAttribute("id",id);
        return "modules/mobile/index";
    }
}
