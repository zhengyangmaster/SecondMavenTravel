package cn.zzy.travel.dao.impl;

import cn.zzy.travel.dao.CategoyDao;
import cn.zzy.travel.domain.Category;
import cn.zzy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoyDao {
    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        List<Category> all = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return all;
    }
}
