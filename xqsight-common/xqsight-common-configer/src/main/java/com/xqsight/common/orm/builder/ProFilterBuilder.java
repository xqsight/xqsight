package com.xqsight.common.orm.builder;

import com.xqsight.common.orm.MatchType;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.PropertyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangganggang on 2017/1/3.
 */
public class ProFilterBuilder {
    public static List<PropertyFilter> propertyFilters = new ArrayList<>();

    private MatchType matchType;
    private PropertyType propertyType;

    private ProFilterBuilder() {
    }

    public static ProFilterBuilder create() {
        return new ProFilterBuilder();
    }

    public ProFilterBuilder matchTye(MatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    public ProFilterBuilder propertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public ProFilterBuilder add(String fileName, String value) {
        PropertyFilter propertyFilter = new PropertyFilter(matchType, propertyType, fileName, value);
        propertyFilters.add(propertyFilter);
        return this;
    }

    public ProFilterBuilder addFilter(MatchType matchType, PropertyType propertyType, String fileName, String value) {
        PropertyFilter propertyFilter = new PropertyFilter(matchType, propertyType, fileName, value);
        propertyFilters.add(propertyFilter);
        return this;
    }

    public List<PropertyFilter> end() {
        return propertyFilters;
    }

}
