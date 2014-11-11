package cn.leap.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by longjianlin on 14-10-30.
 * V 1.0
 * *********************************
 * Desc:
 * *********************************
 */
public class Users extends Model<Users> {
    public static final Users dao = new Users();

    /**
     * 查询今天没有订餐的人
     *
     * @param today
     * @return
     */
    public List<Users> todayUnOrderingUsers(final String today) {
        return dao.find("select u.id,u.nickname,u.mobile,u.email from users u where u.id not in (select o.uid from orders o where o.create_at='" + today + "')");
    }

    /**
     * 检测用户是否存在
     * @param name
     * @return
     */
    public boolean checkUsersByName(final String name) {
        List<Users> usersList = dao.find("select * from users u where u.nickname = '" + name + "'");
        if (usersList == null || usersList.size() == 0)
            return false;

        return true;
    }

}
