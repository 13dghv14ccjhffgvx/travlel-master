package com.hjq.service;

import com.hjq.domain.Favorite;
import com.hjq.domain.Route;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-08-01  09:24
 * @Description: TODO
 * @Version: 1.0
 */
public interface FavoriteService {

    /**
     * 查询线路是否被收藏
     * @param rid 线路id
     * @param uid 当前登录的用户id
     * @return
     */
    public boolean isFavorite(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    public void addFavorite(int rid,int uid);

    /**
     * 查询受欢迎的线路
     * @return
     */
    public List<Route> popularRoute();
}
