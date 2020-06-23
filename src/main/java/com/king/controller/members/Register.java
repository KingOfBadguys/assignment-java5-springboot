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
public class Register {
    @Autowired
    private IMemberService iMemberService;

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("member", new Member());
        return "member/register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("member") Member member,
                             @RequestParam("username") String username, @RequestParam("password") String password,
                             @RequestParam("c-password") String cPassword, @RequestParam("email") String email, Model model) {
        try {
//            member = iMemberService.findByName(member.getUsername());
            if (member.getUsername().equalsIgnoreCase(username)) {
                if (password.equals(cPassword)) {
                    member.setUsername(username);
                    member.setPassword(password);
                    member.setEmail(email);

                    iMemberService.save(member);

                    model.addAttribute("success", "Register successful, welcome: " + username);

                    /*Save register infor*/
                    SaveLogged.MEM = member;

                    return "member/updateInfor";
                } else {
                    model.addAttribute("error", "Confirm password is incorrect");
                    return "member/register";
                }
            } else {
                model.addAttribute("error", "Username already taken");
                return "member/register";
            }

        } catch (Exception e) {
            model.addAttribute("error", "Something wrong here, please check your input");
            return "member/register";
        }
    }
}
