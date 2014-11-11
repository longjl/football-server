package com.football.controller;

import com.jfinal.core.Controller;

/**
 * Created by longjianlin on 14-11-11.
 */
public class UsersController extends Controller {
    public void index() {
        render("/users.html");
    }
}
