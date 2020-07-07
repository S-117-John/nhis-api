package com.zebone.modules.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@Controller
@RequestMapping("mobile/advice")
public class MedicalAdviceController {

    /**
     * 跳转医嘱页面
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("")
    public String index(String id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String pkPv= (String) session.getAttribute("pkPv");
        model.addAttribute("id",id);
        return "modules/mobile/medicalAdvice";
    }

    /**
     * 跳转医嘱搜索页面
     * @param value
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("search")
    public String search(String value, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        String pkPv = (String) session.getAttribute("pkPv");
        model.addAttribute("pkPv",pkPv);
        model.addAttribute("value",value);
        return "modules/mobile/medicalAdviceSearch";
    }


}
