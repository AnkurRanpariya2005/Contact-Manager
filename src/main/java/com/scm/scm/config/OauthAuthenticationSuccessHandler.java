package com.scm.scm.config;

import java.io.IOException;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm.entity.Providers;
import com.scm.scm.entity.User;
import com.scm.scm.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepo; 


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        logger.info("OauthwnuxION AUXCCWAA");

        DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
        logger.info(user.getName()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+user.toString());
        

        String email = user.getAttribute("email").toString();
          String name = user.getAttribute("name").toString();
          String picture = user.getAttribute("picture").toString();
          
          // create user and save in database
          
          User user1 = new User();
          user1.setEmail(email);
          user1.setName(name);
          user1.setProfilePic(picture);
          user1.setPassword("password");
          user1.setUserId(UUID.randomUUID().toString());
          user1.setProvider(Providers.GOOGLE);
          user1.setEnabled(true);
          
          user1.setEmailVerified(true);
          user1.setProviderUserId(user.getName());
          user1.setRoleList(List.of("ROLE_USER"));
          user1.setAbout("This account is created using google..");
          
          User user2 = userRepo.findByEmail(email).orElse(null);
          if (user2 == null) {
          
            userRepo.save(user1);
            logger.info("User saved:" + email);
          }

          response.sendRedirect("/user/profile");
    }
    
}
