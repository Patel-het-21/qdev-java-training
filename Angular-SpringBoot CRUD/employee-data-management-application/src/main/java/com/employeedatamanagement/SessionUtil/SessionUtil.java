package com.employeedatamanagement.SessionUtil;


import com.employeedatamanagement.entity.master.User;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("LOGGED_IN_USER");
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getLoggedInUser(session) != null;
    }

}
