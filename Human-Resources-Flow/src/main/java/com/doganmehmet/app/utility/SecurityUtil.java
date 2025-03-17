package com.doganmehmet.app.utility;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getUsername()
    {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) ? auth.getName() : "anonymous";
    }

}
