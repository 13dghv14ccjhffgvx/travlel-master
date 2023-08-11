package com.hjq.service.impl;

import com.hjq.dao.CategoryDao;
import com.hjq.dao.impl.CategoryDaoImpl;
import com.hjq.domain.Category;
import com.hjq.service.CategoryService;
import com.hjq.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  14:45
 * @Description: TODO
 * @Version: 1.0
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //实现缓存优化，分类数据每一次刷新请求都需要加载,对mysql的压力大
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2可使用sortedset排序查询
        //Set<String> category = jedis.zrange("category", 0, -1);
        //1.3 查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cg = null;
        //2.判断查询的集合是否为空
        if (category == null || category.size() == 0){

            System.out.println("从数据库Mysql中查询......");
            //3.如果为空，需要从数据库查询，在将数据存入redis
            //3.1 从数据库查询
            cg = categoryDao.findAll();
            //3.2 将集合数据存储到redis中的 category的key
            for (int i = 0;i<cg.size();i++){
                jedis.zadd("category",cg.get(i).getCid(),cg.get(i).getCname());
            }
        }else{
            System.out.println("从redis中查询......");
            //4.如果不为空，将set的数据存入list
            cg = new ArrayList<Category>();
            for (Tuple tuple : category){
                Category category1 = new Category();
                category1.setCname(tuple.getElement());
                category1.setCid((int) tuple.getScore());
                cg.add(category1);
            }
        }

        return cg;
    }
}
