package com.king.controller.members;

import com.king.controller.SaveLogged;
import com.king.model.Member;
import com.king.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("member")
public class Login {
    @Autowired
    private IMemberService iMemberService;

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    @RequestMapping(value = "member/login",method = RequestMethod.GET)
    public String login(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
            SaveLogged.MEM = new Member();
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("login",null);
            model.addAttribute("member",SaveLogged.MEM);
            model.addAttribute("role",null);
            return "member/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("member") Member member, @CookieValue(value = "setUser",defaultValue = "") String setUser, Model model,
                          @RequestParam("username") String username, @RequestParam("password") String password,
                          HttpServletResponse response, HttpServletRequest request) {
        try {
            member = iMemberService.findByName(username);
            if (member.getPassword().equals(password)) {
                setUser = member.getUsername();
                // create cookie and set it in response
                Cookie cookie = new Cookie("setUser", setUser);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);

                //get all cookies
                Cookie[] cookies = request.getCookies();
                //iterate each cookie
                for (Cookie ck : cookies) {
                    //display only the cookie with the name 'setUser'
                    if (ck.getName().equals("setUser")) {
                        model.addAttribute("cookieValue", ck);
                        break;
                    } else {
                        ck.setValue("");
                        model.addAttribute("cookieValue", ck);
                        break;
                    }
                }
                model.addAttribute("logged", "Login success. Welcome!!! ");
                model.addAttribute("username", setUser);

                //save login's information
                SaveLogged.MEM = member;
                int role = member.getRole();

                model.addAttribute("login",SaveLogged.MEM);
                model.addAttribute("role",role);

                return "home";
            } else {
                member.setUsername("");
                Cookie cookie = new Cookie("setUser", setUser);
                model.addAttribute("cookieValue", cookie);
                model.addAttribute("message", "password is incorrect");
                return "member/login";
            }
        } catch (Exception e) {
            member.setUsername("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Username is incorrect");
            return "member/login";
        }
    }

}
