package com.sap.javaspringtrainingpoc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by I863409 on 04/08/2017.
 *
 * This class is intended to override the default spring security class
 * that handles the authetication success.
 *
 * It refers to a bean in the application-security-xml file.
 *
 * In that bean is declared which url to display when a successful
 * authentication happens
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        request.getSession().setMaxInactiveInterval(60 * 60); //one hour
        System.out.println("Session set up for 60min");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}