package cn.zzy.travel.dao;

import cn.zzy.travel.domain.Category;

import java.util.List;

public interface CategoyDao {
    List<Category> findAll();
}
