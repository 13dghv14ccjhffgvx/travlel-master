package com.hjq.dao;

import com.hjq.domain.Seller;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-31  21:06
 * @Description: TODO
 * @Version: 1.0
 */
public interface SellerDao {

    /**
     * 根据id查询商家信息
     *
     * @param sid
     * @return
     */
    public Seller findOneById(int sid);



}
