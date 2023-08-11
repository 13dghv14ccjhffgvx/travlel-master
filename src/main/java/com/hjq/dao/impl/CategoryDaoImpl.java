package com.hjq.dao.impl;

import com.hjq.dao.CategoryDao;
import com.hjq.domain.Category;
import com.hjq.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  14:42
 * @Description: 分类
 * @Version: 1.0
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSources());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
