package com.ozstrategy.dao.dictionary.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.dictionary.DictionaryTypeDao;
import com.ozstrategy.model.dictionary.DictionaryType;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-10.
*/
@Repository("dictionaryTypeDao")
public class DictionaryTypeDaoImpl extends BaseDaoImpl<DictionaryType> implements DictionaryTypeDao{

    public DictionaryTypeDaoImpl() {
    super(DictionaryType.class);
    }

}