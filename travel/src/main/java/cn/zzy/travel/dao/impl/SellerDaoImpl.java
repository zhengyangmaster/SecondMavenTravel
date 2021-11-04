package cn.zzy.travel.dao.impl;

import cn.zzy.travel.dao.SellerDao;
import cn.zzy.travel.domain.Seller;
import cn.zzy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findBySellid(int id) {
        String sql ="select * from tab_seller where sid=?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
