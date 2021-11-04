package cn.zzy.travel.service.impl;

import cn.zzy.travel.dao.FavoriteDao;
import cn.zzy.travel.dao.impl.FavoriteDaoImpl;
import cn.zzy.travel.domain.Favorite;
import cn.zzy.travel.service.FavoriteService;

public class FavoriteServicImpl implements FavoriteService {
private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite!=null;//如果有值则为true，没有值则为false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.addfavorite(Integer.parseInt(rid),uid);

    }
}
