package com.sap.javaspringtrainingpoc.controllers;

import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.services.SecurityService;
import com.sap.javaspringtrainingpoc.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.validation.Valid;

/**
 * Created by I863409 on 02/08/2017.
 */


@Controller
public class UserController {

    @Resource
    private UserService userService;


    @Resource
    private SecurityService securityService;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        User user = new User();


        model.addAttribute("user", user);
        return "login";
    }
/*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            //bindingResult.rejectValue("general","general","Error: "+ bindingResult.getAllErrors());
            return "login";
        }

        if(userService.userEmailExists(user.getEmail())){
            if(userService.confirmPassword(user)) {
                //redirectAttributes.addFlashAttribute("success", true);
                return "redirect:/restaurants/";

            }
            else{
                bindingResult.rejectValue("password","password","Wrong password");
                return "login";
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"User doesn't exists. Redirecting to registration area");
            return "redirect:/registration";
        }
    }
*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        user.setId(1);

        model.addAttribute("user", user);

        return "register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid User user, BindingResult bindingResult, Model model) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.userEmailExists(user.getEmail())) {
            bindingResult.rejectValue("email","email","Email already registered");
            return "register";
        }

        if(! user.getPassword().equals(user.getPasswordConfirm())){
            bindingResult.rejectValue("passwordConfirm","passwordConfirm","Passwords should match");
            return "register";
        }

        userService.saveUser(user);

        JOptionPane.showMessageDialog(null,"User registered");
        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }





    @RequestMapping(value = {"/accessdenied"}, method = RequestMethod.GET)
    public String accessdenied(Model model) {

        return "accessdenied";
    }

    @RequestMapping(value="/pagenotfound", method = RequestMethod.GET)
    public String pagenotfound(){
        return "404";
    }

}