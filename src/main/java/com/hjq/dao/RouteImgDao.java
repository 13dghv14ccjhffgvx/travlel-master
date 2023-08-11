package com.hjq.dao;

import com.hjq.domain.RouteImg;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  22:22
 * @Description: TODO
 * @Version: 1.0
 */
public interface RouteImgDao {

    /**
     * 根据route的id查询图片集合
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);


}
