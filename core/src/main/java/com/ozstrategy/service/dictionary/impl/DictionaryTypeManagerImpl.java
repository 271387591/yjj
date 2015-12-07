package com.ozstrategy.service.dictionary.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.dictionary.DictionaryDao;
import com.ozstrategy.dao.dictionary.DictionaryTypeDao;
import com.ozstrategy.model.dictionary.DictionaryType;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.dictionary.DictionaryTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("dictionaryTypeManager")
public class DictionaryTypeManagerImpl extends BaseManagerImpl<DictionaryType> implements DictionaryTypeManager {
    @Autowired
    private DictionaryTypeDao dictionaryTypeDao;
    @Autowired
    private DictionaryDao dictionaryDao;


    @Override
    public BaseDao<DictionaryType> baseDao() {
        return dictionaryTypeDao;
    }

    @Override
    public void deleteType(Long id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_typeId_EQ",id);
        dictionaryDao.deleteByParam(map);
        dictionaryTypeDao.deleteById(id);
    }
}
