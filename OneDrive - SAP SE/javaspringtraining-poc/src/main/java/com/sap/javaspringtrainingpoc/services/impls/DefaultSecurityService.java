package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.services.SecurityService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by I863409 on 03/08/2017.
 */
public class DefaultSecurityService implements SecurityService{

    @Override
    public boolean isLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return true;
        }
        return false;
    }
}
