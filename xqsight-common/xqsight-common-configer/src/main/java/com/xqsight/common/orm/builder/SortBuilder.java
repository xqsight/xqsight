package com.xqsight.common.orm.builder;


import com.xqsight.common.orm.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangganggang on 2017/1/3.
 */
public class SortBuilder {

    List<Sort> sorts = new ArrayList<>();

    private SortBuilder() {
    }

    public static SortBuilder create() {
        return new SortBuilder();
    }

    public SortBuilder addDesc(String fileName) {
        Sort sort = new Sort(fileName, Sort.DESC);
        sorts.add(sort);
        return this;
    }

    public SortBuilder addAsc(String fileName) {
        Sort sort = new Sort(fileName, Sort.ASC);
        sorts.add(sort);
        return this;
    }

    public List<Sort> end() {
        return sorts;
    }

}
