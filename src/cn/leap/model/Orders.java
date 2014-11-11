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
public class Orders extends Model<Orders> {
    public static final Orders dao = new Orders();


    public List<Orders> todayOrders(final String today) {
        return dao.find("select o.id,o.uid,o.create_at,u.nickname,u.email,u.mobile from orders o left JOIN users u on o.uid = u.id where o.create_at='" + today + "'");
    }
}
