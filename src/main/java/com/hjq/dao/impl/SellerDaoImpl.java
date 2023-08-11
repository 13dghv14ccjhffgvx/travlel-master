package com.hjq.dao.impl;

import com.hjq.dao.SellerDao;
import com.hjq.domain.Seller;
import com.hjq.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-31  21:06
 * @Description: TODO
 * @Version: 1.0
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSources());

    @Override
    public Seller findOneById(int sid) {
        //编写sql语句
        String sql = "select * from tab_seller where sid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
