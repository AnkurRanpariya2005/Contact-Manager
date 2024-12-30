package com.scm.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.Helper;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.service.ContactService;
import com.scm.scm.service.ImageService;
import com.scm.scm.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;
    
    @GetMapping("/add")
    public String addContact(Model model){
        ContactForm form = new ContactForm();
        model.addAttribute("contactForm", form);
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,  Authentication authentication, HttpSession httpSession){
        
        if(result.hasErrors()){
            httpSession.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getByEmail(username);

        String fileUrl = imageService.uploadImage(contactForm.getPicture());

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setAddress(contactForm.getAddress());
        contact.setEmail(contactForm.getEmail());
        contact.setFavority(contactForm.isFavority());
        contact.setPhone(contactForm.getPhone());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileUrl);
        contact.setUser(user);
        contactService.save(contact);

        httpSession.setAttribute("message", Message.builder()
                    .content("Contact added Succesfully")
                    .type(MessageType.green)
                    .build());
      
        return "redirect:/user/contacts/add";
    }

    @GetMapping()
    public String viewContacts(Authentication authentication, Model model){
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getByEmail(username);
        List<Contact> contacts = contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        // System.out.println(contacts+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"  );

        
        return "user/contacts";
    }
}

