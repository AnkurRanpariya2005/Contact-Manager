package com.scm.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm.entity.User;
import com.scm.scm.helper.Helper;
import com.scm.scm.service.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(RootController.class);
    
    @ModelAttribute
    public void addUserLoginInInformation(Model model, Authentication authentication){

        if(authentication==null){
            return;
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getByEmail(username);
        // logger.info(user.getName());
        // logger.info(user.getEmail());

        model.addAttribute("LogginUser", user);
    }
}
