package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entity.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/services")
    public String service(){
        return "service";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model){

        UserForm userForm = new UserForm();
        
        model.addAttribute("userForm", userForm);

        return "register";
    }


    @PostMapping("/do-register")
    public String doRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session){
        
        if(rBindingResult.hasErrors()){
            return "register";
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhone(userForm.getPhone());
        user.setProfilePic("https://th.bing.com/th/id/OIP.TpqSE-tsrMBbQurUw2Su-AHaHk?rs=1&pid=ImgDetMain");                    

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        Message message = Message.builder().content("Registered Succcesfully!!").type(MessageType.green).build();

        session.setAttribute("message", message);

        return "redirect:/register";
    }
}

