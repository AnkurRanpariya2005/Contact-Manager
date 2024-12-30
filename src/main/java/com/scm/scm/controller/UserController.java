package com.scm.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entity.User;
import com.scm.scm.helper.Helper;
import com.scm.scm.service.UserService;
import com.scm.scm.service.impl.UserServiceImpl;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    
    
    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication){
        

        return "user/profile";
    }
}
