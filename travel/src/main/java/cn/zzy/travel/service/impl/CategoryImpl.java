package cn.zzy.travel.service.impl;

import cn.zzy.travel.dao.CategoyDao;
import cn.zzy.travel.dao.impl.CategoryDaoImpl;
import cn.zzy.travel.domain.Category;
import cn.zzy.travel.service.CategoryService;
import cn.zzy.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryImpl implements CategoryService {
      CategoyDao dao=new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //通过redis进行优化查询
        //通过jedisUtil进行简化开发
        Jedis jedis = JedisUtil.getJedis();
        //先查询redis中是否存在数据，要使用带有顺序的进行排序。

       // Set<String> set = jedis.zrange("category", 0, -1);
        //按照分数进行排序查询防止出现问题
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> all=null;
        if (categorys==null || categorys.size()==0){
            System.out.println("从数据库中查询");
            //如果为空，从数据库中查询，并将数据存入redis
            all=dao.findAll();
            //将数据存储到redis中的 category的key
            for (int i=0;i<all.size();i++){
                jedis.zadd("category",all.get(i).getCid(),all.get(i).getCname());
            }

        }else {
            System.out.println("从redise中查询");
            //将set的数据之接放进list中
            all= new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                all.add(category);

            }
        }

        //如果不为空，直接将数据返回就行



        return  all;
    }
}
