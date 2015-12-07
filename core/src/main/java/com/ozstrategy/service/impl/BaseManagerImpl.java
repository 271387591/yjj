package com.ozstrategy.service.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.service.BaseManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/8/15.
 */
public  abstract class BaseManagerImpl<T> implements BaseManager<T> {
    public abstract BaseDao<T> baseDao();

    public List<T> list(Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().listPage(map, start, limit);
    }

    public Integer count(Map<String, Object> map) {
        return baseDao().listPageCount(map);
    }

    public List<T> listAll() {
        return baseDao().listAll();
    }

    public List<T> listAll(Map<String, Object> map) {
        return baseDao().listAll(map);
    }

    public T get(Serializable id) {
        return baseDao().get(id);
    }

    public T getByParam(Map<String, Object> map) {
        return baseDao().getByParam(map);
    }

    @Override
    public Map<String, Object> queryMap(Serializable id) {
        return baseDao().queryMap(id);
    }

    @Override
    public Map<String, Object> queryMap(Map<String, Object> params) {
        return baseDao().queryMap(params);
    }

    @Override
    public Map<String, Object> queryMap(Map<String, Object> params, String... columns) {
        return baseDao().queryMap(params, columns);
    }

    public T save(T obj) {
        return baseDao().save(obj);
    }

    public T saveOrUpdate(T obj) {
        return baseDao().saveOrUpdate(obj);
    }

    public int update(T obj) {
        return baseDao().update(obj);

    }

    public int delete(T obj) {
        return baseDao().delete(obj);

    }

    public int deleteById(Serializable id) {
        return baseDao().deleteById(id);

    }

    @Override
    public Integer max(String field,Map<String,Object> map) {
        return baseDao().max(field,map);
    }

    public void batchSave(List<T> list) {
        for(T obj:list){
            baseDao().save(obj);
        }
    }

    public void batchUpdate(List<T> list) {
        for(T obj:list){
            baseDao().update(obj);
        }
    }

    public void batchDelete(List<T> list) {
        for(T obj:list){
            baseDao().delete(obj);
        }
    }

    public <D> List<D> findByNamedQuery(String queryName, Class<D> dClass) {
        return baseDao().findByNamedQuery(queryName, dClass);
    }

    public <D> List<D> findByNamedQuery(String queryName, Class<D> dClass, Map<String, Object> map) {
        return baseDao().findByNamedQuery(queryName, dClass,map);
    }

    public <D> List<D> findByNamedQueryPage(String queryName, Class<D> dClass, Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().findByNamedQueryPage(queryName, dClass, map, start, limit);
    }

    public <D> D findByNamedQueryBean(String queryName, Class<D> dClass, Map<String, Object> map) {
        return baseDao().findByNamedQueryBean(queryName, dClass, map);
    }

    public <D> D findByNamedQueryClass(String queryName, Class<D> dClass, Map<String, Object> map) {
        return baseDao().findByNamedQueryClass(queryName, dClass, map);
    }

    public Map<String, Object> findByNamedQueryMap(String queryName, Map<String, Object> map) {
        return baseDao().findByNamedQueryMap(queryName, map);
    }

    public List<Map<String, Object>> findByNamedQuery(String queryName, Map<String, Object> map) {
        return baseDao().findByNamedQuery(queryName, map);
    }

    public List<Map<String, Object>> findByNamedQuery(String queryName, Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().findByNamedQuery(queryName, map, start, limit);
    }

    @Override
    public List<Map<String, Object>> listMap(Map<String, Object> params) {
        return baseDao().listMap(params);
    }

    @Override
    public List<Map<String, Object>> listPageMap(Map<String, Object> params, Integer start, Integer limit) {
        return baseDao().listPageMap(params,start,limit);
    }

    @Override
    public List<Map<String, Object>> listPageMap(Map<String, Object> params, Integer start, Integer limit, String... columns) {
        return baseDao().listPageMap(params, start, limit, columns);
    }

    @Override
    public void idx(List<T> list) {
        for(T o:list){
            baseDao().update(o);
        }
    }


}
