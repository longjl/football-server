package com.football.controller;

import com.jfinal.core.Controller;

/**
 * Created by longjianlin on 14-11-11.
 * V 1.0
 * *********************************
 * Desc:
 * *********************************
 */
public class CommonController extends Controller {
    public void index(){
        render("/index.html");
    }
}
