package com.hjq.dao.impl;

import com.hjq.dao.FavoriteDao;
import com.hjq.domain.Favorite;
import com.hjq.domain.Route;
import com.hjq.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-08-01  09:29
 * @Description: TODO
 * @Version: 1.0
 */
public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSources());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        //1.编写sql语句
        String sql = "select * from tab_favorite where rid = ? and uid = ?";
        //2.执行sql
        Favorite favorite = null;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            System.out.println("FavoriteDaoImpl中的findByRidAndUid()方法");
        }
        return favorite;
    }

    @Override
    public int findFavoriteCount(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values (?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public List<Route> popularRoute() {
        String sql = "SELECT f.rid,r.rname,r.`price`,r.`routeIntroduce`,r.`rimage` FROM tab_favorite f JOIN tab_route r  ON f.`rid` = r.`rid` GROUP BY f.rid ORDER BY COUNT(*) DESC LIMIT 4";
        List<Route> list = template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
        return list;
    }


}
