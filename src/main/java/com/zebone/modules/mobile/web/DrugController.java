package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@Controller
@RequestMapping("mobile/drug")
public class DrugController {

    /**
     * 进入药品录入页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String pkPv= (String) session.getAttribute("pkPv");
        model.addAttribute("pkPv",pkPv);
        return "modules/mobile/drugIndex";
    }
}
