package cn.zzy.travel.service;

import cn.zzy.travel.domain.User;

public interface UserService {

    //注册用户
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
