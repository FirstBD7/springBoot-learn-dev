package com.example.springbootlearn.manage.util;

import com.example.springbootlearn.manage.model.User;

public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();
    public static void saveUser(User user)
    {
        tl.set(user);
    }
    public static User getUser()
    {
        return tl.get();
    }
    public static void remove()
    {
        tl.remove();
    }
}
