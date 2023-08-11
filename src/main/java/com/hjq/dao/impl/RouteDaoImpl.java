package com.hjq.dao.impl;

import com.hjq.dao.RouteDao;
import com.hjq.domain.Route;
import com.hjq.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  22:25
 * @Description: TODO
 * @Version: 1.0
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSources());

    @Override
    public int findTotalCount(int cid,String routeName) {
        //1.编写sql语句
        //String sql = "select count(*) from tab_route where cid = ? and rname like ?";//因为这两个参数不一定都有
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder str = new StringBuilder(sql);
        //接收参数集合，因为这两个参数并不确定是否有值
        List params = new ArrayList<>();
        //判断参数是否有值拼接
        if (cid != 0){
            str.append(" and cid = ? ");
            params.add(cid);
        }
        if(routeName != null && routeName.length() > 0){
            str.append(" and rname like ?");
            params.add("%"+routeName+"%");
        }
        sql = str.toString();

        int result = 0;
        try {
             result = template.queryForObject(sql,Integer.class,params.toArray());
        } catch (DataAccessException e) {
            System.out.println("RouteDaoImpl findTotalCount()方法有异常");
        }
        return result;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String routeName) {

        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        //1.定义sql模板
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder str = new StringBuilder(sql);
        //参数集合
        List params = new ArrayList<>();
        //2.判断参数是否有值
        if(cid != 0){
            str.append(" and cid = ? ");
            params.add(cid);//添加占位符?对应的值
        }
        if (routeName != null && routeName.length() > 0){
            str.append(" and rname like ? ");
            params.add("%"+routeName+"%");
        }
        str.append(" limit ? , ? ");//分页条件
        sql = str.toString();

        //添加分页条件参数
        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());//为什么要toArray()转为数组呢
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);

    }

    @Override
    public List<Route> newestRoute() {
        String sql = "SELECT * FROM tab_route ORDER BY rdate DESC LIMIT 4;";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Override
    public List<Route> themeRoutes() {
        //根据时间排序的主题旅游推荐
        String sql = "SELECT * FROM tab_route WHERE isThemeTour = 1 ORDER BY rdate DESC LIMIT 4;";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Override
    public List<Route> domesticTours() {
        String sql = "SELECT rname,price,rimage FROM tab_route WHERE nation IN ('中国','china','China') ORDER BY rdate DESC LIMIT 6";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Override
    public List<Route> abroadTours() {
        String sql = "SELECT rname,price,rimage FROM tab_route WHERE nation NOT IN ('中国','china','China','') ORDER BY rdate DESC LIMIT 6";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
}
