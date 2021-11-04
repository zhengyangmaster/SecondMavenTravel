package cn.zzy.travel.dao.impl;

import cn.zzy.travel.dao.UserDao;
import cn.zzy.travel.domain.User;
import cn.zzy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUserName(String username) {
        User user=null;
        try{
        String sql="select * from tab_user where username=?";


         user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);}catch (Exception e){}

        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());

    }

    @Override
    public User findUserByCode(String code) {
        User user=null;
        try {
            String sql = "select * from tab_user where code=?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        //修改用户激活状态
        String sql="update tab_user set status='Y' where uid=?";
        jdbcTemplate.update(sql,user.getUid());

    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        User user = null;
        try {


        String sql="select * from tab_user where username=? and password=?";
     user=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);}catch (Exception e){}
    return user;
    }
}
