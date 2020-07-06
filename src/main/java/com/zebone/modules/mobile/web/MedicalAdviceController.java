package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mobile/advice")
public class MedicalAdviceController {

    @RequestMapping("")
    public String index(String id, Model model){
        model.addAttribute("id",id);
        return "modules/mobile/medicalAdvice";
    }

    @RequestMapping("search")
    public String search(String value, Model model, HttpSession session){
        String pkPv = (String) session.getAttribute("pkPv");
        model.addAttribute("pkPv",pkPv);
        return "modules/mobile/medicalAdviceSearch";
    }
}
