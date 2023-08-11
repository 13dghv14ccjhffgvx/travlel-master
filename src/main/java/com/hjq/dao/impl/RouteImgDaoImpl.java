package com.hjq.dao.impl;

import com.hjq.dao.RouteDao;
import com.hjq.dao.RouteImgDao;
import com.hjq.domain.RouteImg;
import com.hjq.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-31  20:49
 * @Description: TODO
 * @Version: 1.0
 */
public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSources());
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";

        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
