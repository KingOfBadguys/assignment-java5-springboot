package com.king.controller;

import com.king.model.Member;
import org.springframework.stereotype.Controller;

@Controller
public class SaveLogged {
    //save USER's information after login successfully
    public static Member MEM = null;

    //remove User's information after log out
    public static void logoff() {
        SaveLogged.MEM = null;
    }

    //Check isLogged or isNotLogged
    public static boolean authenticated() {
        return SaveLogged.MEM != null;
    }
}
