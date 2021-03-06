package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@Controller
@RequestMapping("mobile/index")
public class IndexController {

    /**
     * 首页
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("")
    public String index(String id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("pkPv",id);
        model.addAttribute("id",id);
        return "modules/mobile/index";
    }
}
