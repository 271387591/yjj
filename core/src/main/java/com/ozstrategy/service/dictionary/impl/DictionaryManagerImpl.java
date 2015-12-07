package com.ozstrategy.service.dictionary.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.dictionary.DictionaryDao;
import com.ozstrategy.model.dictionary.Dictionary;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.dictionary.DictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("dictionaryManager")
public class DictionaryManagerImpl extends BaseManagerImpl<Dictionary> implements DictionaryManager {
    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public BaseDao<Dictionary> baseDao() {
        return dictionaryDao;
    }
}
