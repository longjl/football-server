package com.football.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by longjianlin on 14-11-11.
 */
public class StadiumInfo extends Model<StadiumInfo> {
    public static final StadiumInfo dao = new StadiumInfo();

    /**
     * @param gesture 1:下拉,0:上推
     * @param id      内容id
     * @param limit   显示条数
     * @return
     */
    public List<StadiumInfo> queryAll(int gesture, long id, int limit) {
        StringBuffer query_sql = new StringBuffer("SELECT si.id,si.title,si.cons_patterns,si.address from stadium_info si where ");
        if (gesture == 1) {
            query_sql.append(" si.id > " + id);
        } else if (gesture == 0) {
            query_sql.append(" si.id < " + id);
        }
        query_sql.append(" LIMIT " + limit);
        return dao.find(query_sql.toString());
    }
}
