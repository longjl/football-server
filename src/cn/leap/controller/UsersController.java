package cn.leap.controller;

import cn.leap.DateUtil;
import cn.leap.model.Users;
import com.jfinal.core.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by longjianlin on 14-10-30.
 * V 1.0
 * *********************************
 * Desc:
 * *********************************
 */
public class UsersController extends Controller {


    public void index() {
        String today = DateUtil.format.format(new Date());
        List<Users> userses = Users.dao.todayUnOrderingUsers(today);

        setAttr("userses", userses);
        setAttr("today", today);

        render("/users.html");
    }

    /**
     * add user
     */
    public void addUsers() {
        String nickname = getPara("nickname");
        String email = getPara("email");
        String mobile = getPara("mobile");

        if (nickname == null || nickname.length() == 0) {
            redirect("/");
            return;
        }

        boolean checkUsers = Users.dao.checkUsersByName(nickname);
        if (!checkUsers){
            Users u = new Users();
            u.set("nickname", nickname);
            u.set("email", email);
            u.set("mobile", mobile);
            u.set("create_at", new Date());
            u.save();
        }
        redirect("/");
    }
}
