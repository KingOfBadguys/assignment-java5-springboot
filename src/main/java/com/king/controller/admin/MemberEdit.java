package com.king.controller.admin;

import com.king.controller.SaveLogged;
import com.king.model.Member;
import com.king.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("member")
public class MemberEdit {
    @Autowired
    private IMemberService iMemberService;

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    /*Edit*/
    @GetMapping("admin/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("member", iMemberService.findById(id));
        return "admin/member-edit";
    }

    @PostMapping("admin/edit")
    public String doEdit(Model model, Member member, @RequestParam("gender") String gender) {
        try {
            if (gender.equalsIgnoreCase("♥")) {
                model.addAttribute("eError", "Please select your gender");
                return "admin/member-edit";
            } else {
                iMemberService.save(member);
                model.addAttribute("eSuccess", "Update successful!!!");
            }
        } catch (Exception e) {
            model.addAttribute("eError", "Update failed!!!");
        }
        return "admin/member-edit";
    }

    /*Delete*/
    @GetMapping("admin/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("member", iMemberService.findById(id));
        return "admin/member-delete";
    }

    @PostMapping("admin/delete")
    public String doDelete(Model model, Member member) {
        try {
            if (member.getRole() != 1) {
                iMemberService.remove(member.getMemberID());
                model.addAttribute("dSuccess", "Delete successful!!!");
            } else {
                model.addAttribute("dError", "You can not delete this admin");
            }
        } catch (Exception e) {
            model.addAttribute("dError", "There is nothing to delete, deleted fail!!!");
        }
        return "admin/member-delete";
    }

    /*Craete*/
    @GetMapping("admin/create")
    public String create(Model model) {
        model.addAttribute("member", new Member());
        return "admin/member-create";
    }

    @PostMapping("admin/create")
    public String doCreate(Model model, @ModelAttribute("member") Member member
            , @RequestParam("username") String username) {
        try {
            if (iMemberService.findByName(username)!=null)
                model.addAttribute("cError", "Username have already taken!!!");
                return "admin/member-create";

        } catch (Exception e) {
            if(e!=null){
                iMemberService.save(member);
                model.addAttribute("cSuccess", "Create successful!!!");
            }else{
                model.addAttribute("cError", "Create failed!!!");
            }
            return "admin/member-create";
        }
    }

    /*Reset*/
    @PostMapping("admin/reset")
    public String doReset(Model model) {
        model.addAttribute("member", new Member());
        return "admin/member-create";
    }
}
