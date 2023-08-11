package com.hjq.dao;

import com.hjq.domain.Category;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  14:41
 * @Description:
 * @Version: 1.0
 */
public interface CategoryDao {
    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll();
}
