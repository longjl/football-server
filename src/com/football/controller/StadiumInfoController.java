package com.football.controller;

import com.football.Constant;
import com.football.model.StadiumInfo;
import com.jfinal.core.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longjianlin on 14-11-11.
 */
public class StadiumInfoController extends Controller {
    private int status = -1; //请求状态

    /**
     * 首页
     */
    public void index() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int gesture = getParaToInt("gesture");
            long id = getParaToLong("id");
            List<StadiumInfo> list = StadiumInfo.dao.queryAll(gesture, id, Constant.LIMIT);
            status = 200;
            map.put("status", status);
            map.put("data", list);
        } catch (Exception e) {
            status = -1;
            map.put("status", status);
        }
        renderJson(map);
    }

    /**
     * 发布信息
     */
    public void add() {
        Map<String, Object> map = new HashMap<String, Object>();
        status = -1;
        try {
            String title = getPara("title"); //标题
            String cost = getPara("cost");//场地费用
            int num = getParaToInt("num"); //人数要求
            int cons_patterns = getParaToInt("cons_patterns");//消费方式
            String address = getPara("address"); //场地地址
            int province = getParaToInt("province");//省份编码
            int city = getParaToInt("city");//城市编码
            int uid = getParaToInt("uid");//用户编号

            StadiumInfo info = new StadiumInfo();
            info.set("title", title);
            info.set("num", num);
            info.set("cost", cost);
            info.set("cons_patterns", cons_patterns);
            info.set("address", address);
            info.set("province", province);
            info.set("city", city);
            info.set("uid", uid);
            info.set("create_at", new Date());

            boolean flag = info.save();
            if (flag) {
                status = 200;
            }
        } catch (Exception e) {
            status = -1;
        }
        map.put("status", status);
        renderJson(map);
    }
}
