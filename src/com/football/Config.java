package com.football;

import com.football.controller.CommentsController;
import com.football.controller.CommonController;
import com.football.controller.StadiumInfoController;
import com.football.controller.UsersController;
import com.football.model.Comments;
import com.football.model.StadiumInfo;
import com.football.model.Users;
import com.football.util.PropertiesContent;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * Created by longjianlin on 14-11-11.
 */
public class Config extends JFinalConfig {
    /**
     * 常量配置
     *
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        me.setError404View("/404.html");
        me.setError500View("/500.html");
    }

    /**
     * 路由配置
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        me.add("/", CommonController.class);
        me.add("/users", UsersController.class);
        me.add("/comments", CommentsController.class);
        me.add("/stadiuminfo", StadiumInfoController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        PropertiesContent pc = new PropertiesContent("db");
        //配置数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(pc.get("jdbc.url"),
                pc.get("jdbc.username"),
                pc.get("jdbc.password"),
                pc.get("jdbc.driver"));
        me.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(true);
        arp.addMapping("users", Users.class);
        arp.addMapping("comments", Comments.class);
        arp.addMapping("stadium_info", StadiumInfo.class);

        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

    @Override
    public void afterJFinalStart() {
//        try {
//            //在拦截器或Controller中通过 setAttrs("print_time", new PrintTime())也一样可以
//            //${print_time.call(time)}
//            FreeMarkerRender.getConfiguration().setSharedVariable("print_time", new PrintTime());
//        } catch (TemplateModelException e) {
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) throws Exception {
        JFinal.start("/Users/longjianlin/Documents/longjl-work/football/football-server/WebRoot", 8088, "/", 5);
    }
}
