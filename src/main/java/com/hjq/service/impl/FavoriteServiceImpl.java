package com.hjq.service.impl;

import com.hjq.dao.FavoriteDao;
import com.hjq.dao.impl.FavoriteDaoImpl;
import com.hjq.domain.Favorite;
import com.hjq.domain.Route;
import com.hjq.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-08-01  09:25
 * @Description: TODO
 * @Version: 1.0
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null; //如果对象不为空，则为true,反之为false
    }

    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.addFavorite(rid,uid);
    }

    @Override
    public List<Route> popularRoute() {

        List<Route> list = new ArrayList<Route>();
        //调用dao层完成查询
        list = favoriteDao.popularRoute();
        return list;


    }


}
