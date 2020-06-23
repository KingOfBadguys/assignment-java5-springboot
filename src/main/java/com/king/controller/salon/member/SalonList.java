package com.king.controller.salon.member;

import com.king.model.Salon;
import com.king.service.salon.ISalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SalonList {
    @Autowired
    private ISalonService iSalonService;

    @RequestMapping("member/salon/list")
    public String salonList(Model model, Salon salon){
        List<Salon> salons = iSalonService.findAll();
        model.addAttribute("salon",iSalonService.findAll());
        model.addAttribute("index",iSalonService.findAll().size());
        return "member/salon/list";
    }
}
