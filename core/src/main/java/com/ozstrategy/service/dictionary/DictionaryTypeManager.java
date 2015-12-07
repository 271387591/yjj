package com.ozstrategy.service.dictionary;

import com.ozstrategy.model.dictionary.DictionaryType;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-08-10.
*/
public interface DictionaryTypeManager extends BaseManager<DictionaryType> {
    void deleteType(Long id);
}
