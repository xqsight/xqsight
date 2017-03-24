package com.xqsight.common.core.orm.builder;

import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 */
public class PropertyFilterBuilder {

    private List<PropertyFilter> propertyFilters;

    private MatchType matchType;
    private PropertyType propertyType;

    private PropertyFilterBuilder() {
        propertyFilters = new ArrayList<>();
    }

    public static PropertyFilterBuilder create() {
        return new PropertyFilterBuilder();
    }

    public PropertyFilterBuilder matchTye(MatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    public PropertyFilterBuilder propertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public PropertyFilterBuilder add(String fileName, String value) {
        PropertyFilter propertyFilter = new PropertyFilter(matchType, propertyType, fileName, value);
        propertyFilters.add(propertyFilter);
        return this;
    }

    public PropertyFilterBuilder addFilter(MatchType matchType, PropertyType propertyType, String fileName, String value) {
        PropertyFilter propertyFilter = new PropertyFilter(matchType, propertyType, fileName, value);
        propertyFilters.add(propertyFilter);
        return this;
    }

    public List<PropertyFilter> end() {
        return propertyFilters;
    }

}
