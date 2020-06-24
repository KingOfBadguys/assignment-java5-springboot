package com.king.controller.salon.member;

import com.king.model.Favorite;
import com.king.service.salon.ISalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteController {
    @Autowired
    private ISalonService iSalonService;

    @RequestMapping("member/salon/favorite2")
    public String index() {
        return "member/salon/favorite2";
    }

    @RequestMapping("/salon/{id}/like")
    public String like(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("favorite") == null) {
            List<Favorite> favorite = new ArrayList<Favorite>();
            favorite.add(new Favorite(iSalonService.findById(id)));
            session.setAttribute("favorite", favorite);
        } else {
            List<Favorite> favorite = (List<Favorite>) session.getAttribute("favorite");
            int index = isExists(id, favorite);
            if (index == -1) {
                favorite.add(new Favorite(iSalonService.findById(id)));
            }
            session.setAttribute("cart", favorite);
        }
        return "member/salon/favorite2";
    }

    @RequestMapping("/salon/dislike/{id}")
    public String dislike(@PathVariable("id") int id, HttpSession session) {
        List<Favorite> favorite = (List<Favorite>) session.getAttribute("favorite");
        int index = isExists(id, favorite);
        favorite.remove(index);
        session.setAttribute("favorite", favorite);
        return "redirect:/member/salon/favorite2";
    }

    private int isExists(int id, List<Favorite> favorite) {
        for (int i = 0; i < favorite.size(); i++) {
            if (favorite.get(i).getSalon().getSalonId() == id) {
                return i;
            }
        }
        return -1;
    }
}
