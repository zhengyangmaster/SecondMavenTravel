package cn.zzy.travel.service.impl;

import cn.zzy.travel.dao.UserDao;
import cn.zzy.travel.dao.impl.UserDaoImpl;
import cn.zzy.travel.domain.User;
import cn.zzy.travel.service.UserService;
import cn.zzy.travel.util.MailUtils;
import cn.zzy.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {

        User u =userDao.findUserByUserName(user.getUsername());
        //判断用户是否存在
        if (u !=null){
            return false;

        }
        //保存用户前
        //设置激活码唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);
        //发送邮件
        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'> 点击激活卢本伟广场</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;

    }
//激活用户
    @Override
    public boolean active(String code) {
       User user= userDao.findUserByCode(code);
       if (user!=null){
           userDao.updateStatus(user);
           return true;
       }else {

        return false;}
    }

    @Override
    public User login(User user) {

        return   userDao.findByUserNameAndPassword(user.getUsername(),user.getPassword());

    }
}
