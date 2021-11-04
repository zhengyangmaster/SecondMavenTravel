package cn.zzy.travel.dao;

import cn.zzy.travel.domain.Favorite;

public interface FavoriteDao {

    //将收藏的线路查找出来
    public Favorite findByRidAndUid(int rid,int uid);
    //根据rid获取收藏次数
    int findCountByRid(int rid);

    void addfavorite(int rid, int uid);
}
