package cn.leap.controller;

import cn.leap.DateUtil;
import cn.leap.model.Orders;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.excel.PoiRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by longjianlin on 14-10-30.
 * V 1.0
 * *********************************
 * Desc:
 * *********************************
 */
public class OrdersController extends Controller {
    public void index() {
        String today = DateUtil.format.format(new Date());
        List<Orders> orderses = Orders.dao.todayOrders(today);
        setAttr("orderses", orderses);
        setAttr("today", today);
        render("/index.html");
    }


    public void addOrders() {
        try {
            long uid = getParaToLong(0);
            if (uid > 0) {
                Orders o = new Orders();
                o.set("uid", uid);
                o.set("create_at", new Date());
                o.save();
            }
        } catch (Exception e) {

        }
        redirect("/");
    }

    public void delOrders() {
        try {
            long id = getParaToLong(0);
            if (id > 0) {
                Orders o = new Orders();
                o.set("id", id);
                o.delete();
            }
        } catch (Exception e) {

        }
        redirect("/");
    }

    /**
     * 导出Excel
     */
    public void exportExcel() {
        String today = DateUtil.format.format(new Date());
        String sql = "select o.id,o.uid,o.create_at,u.nickname,u.email,u.mobile from orders o left JOIN users u on o.uid = u.id where o.create_at='" + today + "'";
        List<Record> data = Db.find(sql);

        String[] columns = {"nickname", "mobile"};
        String[] headers = {"名字", "手机号"};
        try {
            PoiRender excel = new PoiRender(data);
            render(excel.sheetName("all").headers(headers).columns(columns).fileName(today + ".xls"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
