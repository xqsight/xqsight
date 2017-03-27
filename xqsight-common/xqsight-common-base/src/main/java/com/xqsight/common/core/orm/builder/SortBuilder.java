package com.xqsight.common.core.orm.builder;


import com.xqsight.common.core.orm.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * author wangganggang
 * @Date 2017/3/23
 *
 */
public class SortBuilder {

    private List<Sort> sorts = new ArrayList<>();

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
