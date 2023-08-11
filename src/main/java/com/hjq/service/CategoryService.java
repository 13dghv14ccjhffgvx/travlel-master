package com.hjq.service;

import com.hjq.domain.Category;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  14:45
 * @Description: TODO
 * @Version: 1.0
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll();
}
