package com.ozstrategy.dao.dictionary.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.dictionary.DictionaryDao;
import com.ozstrategy.model.dictionary.Dictionary;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-13.
*/
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements DictionaryDao{

    public DictionaryDaoImpl() {
    super(Dictionary.class);
    }

}