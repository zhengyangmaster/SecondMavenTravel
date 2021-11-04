package cn.zzy.travel.service;

public interface FavoriteService {
    //判断是否收藏
    public boolean isFavorite(String rid,int uid);
//添加收藏
    void add(String rid, int uid);
}
