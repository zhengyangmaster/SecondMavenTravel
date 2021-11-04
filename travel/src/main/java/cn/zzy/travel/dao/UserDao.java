package cn.zzy.travel.dao;

import cn.zzy.travel.domain.User;

public interface UserDao {
    User findUserByUserName(String username);

    void save(User user);

    User findUserByCode(String code);

    void updateStatus(User user);

    User findByUserNameAndPassword(String username, String password);
}
