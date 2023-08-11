package com.hjq.dao;

import com.hjq.domain.Favorite;
import com.hjq.domain.Route;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-08-01  09:28
 * @Description: TODO
 * @Version: 1.0
 */
public interface FavoriteDao {

    /**
     * 根据线路id和用户id查询线路是否被收藏
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据线路id查询该线路的收藏次数
     * @param rid
     * @return
     */
    public int findFavoriteCount(int rid);

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
