package com.king.controller.members;

import com.king.controller.SaveLogged;
import com.king.model.Member;
import com.king.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("member")
public class MemberController {
    @Autowired
    private IMemberService iMemberService;

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    /*Edit member*/
    @GetMapping("/member/edit")
    public String edit(Model model) {
        return "member/updateInfor";
    }

    @PostMapping("/member/edit")
    public String doEdit(Model model, Member member) {
        return "";
    }
    /*-------End edit*/

    /*Update Member*/
    @GetMapping("/member/update")
    public String update(Model model, Member member) {
        if (SaveLogged.authenticated()) {
            model.addAttribute("member", SaveLogged.MEM);
            return "member/updateInfor";
        } else {
            model.addAttribute("autMessage", "You did not logged!");
            return "error";
        }

    }

    @PostMapping("/update")
    public String doUpdate(Model model, Member member) {
        member = SaveLogged.MEM;
        try {
            if (member != null) {
                iMemberService.save(member);
                model.addAttribute("uSuccess", "Your information have been updated");
                return "member/updateInfor";
            } else {
                model.addAttribute("uError", "You have no account, please register or login to your account");
                return "member/updateInfor";
            }
        } catch (Exception e) {
            model.addAttribute("uError", "You did not logged");
            return "member/updateInfor";
        }

    }
    /*End update*/
}
