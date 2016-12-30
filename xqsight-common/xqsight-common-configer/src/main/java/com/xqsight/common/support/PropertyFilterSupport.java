package com.xqsight.common.support;

import com.xqsight.common.orm.MatchType;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.PropertyType;
import com.xqsight.commons.utils.DateFormatUtils;
import com.xqsight.commons.utils.ReflectionUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PropertyFilterSupport {
    /**
     * 根据对象ID集合,整理合并集合.
     * <p>
     * 默认对象主键的名称名为"id".
     *
     * @see #mergeByCheckedIds(Collection, Collection, Class, String)
     */
    public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects,
                                                 final Collection<ID> checkedIds, final Class<T> clazz) {
        mergeByCheckedIds(srcObjects, checkedIds, clazz, "id");
    }

    /**
     * 根据对象ID集合,整理合并集合.
     * <p>
     * 页面发送变更后的子对象id列表时,删除原来的子对象集合再根据页面id列表创建一个全新的集合这种看似最简单的做法是不行的.
     * 因此采用如此的整合算法：在源集合中删除id不在目标集合中的对象,根据目标集合中的id创建对象并添加到源集合中.
     * 因为新建对象只有ID被赋值, 因此本函数不适合于cascade-save-or-update自动持久化子对象的设置.
     *
     * @param srcObjects 源集合,元素为对象.
     * @param checkedIds 目标集合,元素为ID.
     * @param clazz      集合中对象的类型
     * @param idName     对象主键的名称
     */
    public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects, final Collection<ID> checkedIds,
                                                 final Class<T> clazz, final String idName) {

        //参数校验
        Assert.notNull(srcObjects, "scrObjects can not be null");
        Assert.hasText(idName, "idName can not be null");
        Assert.notNull(clazz, "clazz can not be null");

        //目标集合为空, 删除源集合中所有对象后直接返回.
        if (checkedIds == null) {
            srcObjects.clear();
            return;
        }

        //遍历源集合,如果其id不在目标ID集合中的对象,进行删除.
        //同时,在目标集合中删除已在源集合中的id,使得目标集合中剩下的id均为源集合中没有的id.
        Iterator<T> srcIterator = srcObjects.iterator();
        try {

            while (srcIterator.hasNext()) {
                T element = srcIterator.next();
                Object id;
                id = PropertyUtils.getProperty(element, idName);

                if (!checkedIds.contains(id)) {
                    srcIterator.remove();
                } else {
                    checkedIds.remove(id);
                }
            }

            //ID集合目前剩余的id均不在源集合中,创建对象,为id属性赋值并添加到源集合中.
            for (ID id : checkedIds) {
                T obj = clazz.newInstance();
                PropertyUtils.setProperty(obj, idName, id);
                srcObjects.add(obj);
            }
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
     * 默认Filter属性名前缀为filter_.
     *
     * @see #buildPropertyFilters(HttpServletRequest, String)
     */
    public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request) {
        return buildPropertyFilters(request, "filter_");
    }

    /**
     * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
     * PropertyFilter命名规则为Filter属性前缀_比较类型属性类型_属性名.
     * <p>
     * eg.
     * filter_EQS_name
     * filter_LIKES_name_OR_email
     */
    public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request, final String filterPrefix) {
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

        //从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
        Map<String, Object> filterParamMap = ParamSupport.getParametersStartingWith(request, filterPrefix);

        //分析参数Map,构造PropertyFilter列表
        for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
            String filterName = entry.getKey();
            Object valueObj = entry.getValue();
            String value = null;
            if (valueObj instanceof String) {
                value = (String) valueObj;
            }
            //如果value值为空,则忽略此filter.
            if (StringUtils.isNotBlank(value)) {
                PropertyFilter filter = new PropertyFilter(filterName, value);
                filterList.add(filter);
            }
        }
        return filterList;
    }

    public static String propertyFilter2SqlString(PropertyFilter filter) {
        String[] propertyNames = filter.getPropertyNames();

        StringBuilder sb = new StringBuilder();
        if (filter.isMultiProperty()) {
            sb.append(" (");
        }

        boolean isFirst = true;
        for (String propertyName : propertyNames) {
            if (StringUtils.isNotBlank(propertyName)) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("OR");
                }
                sb.append(propertyFilter2SqlString(propertyName, filter));
            }
        }

        if (filter.isMultiProperty()) {
            sb.append(") ");
        }
        return sb.toString();
    }

    protected static String propertyFilter2SqlString(String propertyName, PropertyFilter filter) {
        PropertyType propertyType = filter.getPropertyType();
        Object propertyValue = filter.getPropertyValue();
        MatchType matchType = filter.getMatchType();

        StringBuilder sb = new StringBuilder(" " + propertyName);
        sb.append(" " + matchType.express() + " ");
        if (matchType.outputValue()) {
            sb.append(propertyType.expressStart());
            sb.append(matchType.expressStart());
            if (propertyValue != null && propertyValue instanceof Date) {
                Date date = (Date) propertyValue;
                sb.append(DateFormatUtils.yyyy_MM_dd_SPACE_HH_mm_ss.format(date));
            } else {
                sb.append(propertyValue);
            }
            sb.append(matchType.expressEnd());
            sb.append(propertyType.expressEnd() + " ");
        }

        return sb.toString();
    }
}
