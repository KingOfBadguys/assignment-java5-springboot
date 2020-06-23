package com.king.controller.admin;

import com.king.controller.SaveLogged;
import com.king.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("member/")
public class MemberList {
    @Autowired
    private IMemberService iMemberService;

    @GetMapping("list")
    public ModelAndView list(ModelAndView modelAndView) {
        if (SaveLogged.authenticated()) {
            if (SaveLogged.MEM.getRole() == 1) {
                modelAndView = new ModelAndView("/member/list", "member", iMemberService.findAll());
                return modelAndView;
            } else {
                modelAndView = new ModelAndView("error", "message", "You have no permit to reach this page" +
                        ",only admin can reach this page ");
                return modelAndView;
            }
        } else {
            modelAndView = new ModelAndView("error", "message", "You have no permit to reach this page" +
                    ",only admin can reach this page ");
            return modelAndView;
        }
    }
}
