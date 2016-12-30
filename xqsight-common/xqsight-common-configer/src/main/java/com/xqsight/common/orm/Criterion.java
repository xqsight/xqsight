package com.xqsight.common.orm;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.xqsight.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 查询共用类
 */
public class Criterion implements Serializable {
    private static final long serialVersionUID = -9094478488987769373L;

    private static Logger logger = LogManager.getLogger(Criterion.class);

    private String order = Constants.DESC;
    private String orderBy;

    @SuppressWarnings("unchecked")
    private List<PropertyFilter> criteria = Collections.EMPTY_LIST;

    private String customSql;

    //嵌套的扩展查询条件
    @SuppressWarnings("unchecked")
    private List<PropertyFilter> extraCriteria = Collections.EMPTY_LIST;

    public Criterion() {

    }

    public Criterion(List<PropertyFilter> criteria) {
        this.criteria = criteria;
    }

    public Criterion(List<PropertyFilter> criteria, String orderBy) {
        this.criteria = criteria;
        this.orderBy = orderBy;
    }

    public Criterion(List<PropertyFilter> criteria, String orderBy, String order) {
        this.criteria = criteria;
        this.orderBy = orderBy;
        this.order = order;
    }

    /**
     * Property accessor of order
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Property accessor of orderBy
     *
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * Property accessor of criteria
     *
     * @return the criteria
     */
    public List<PropertyFilter> getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(List<PropertyFilter> criteria) {
        this.criteria = criteria;
    }

    public String getWhereSqlString() {
        return constructSqlString(criteria);
    }

    public void setCustomCriteria(String sql) {
        this.customSql = sql;
    }

    public String getCustomSqlString() {
        return customSql;
    }

    public String getExtraWhereSqlString() {
        return constructSqlString(extraCriteria);
    }

    public String getOrderBySqlString() {
        if (StringUtils.isNotBlank(orderBy)) {
            //拼装order条件
            StringBuilder sb = new StringBuilder(" ORDER BY ");
            String[] orderBys = StringUtils.split(orderBy, ',');
            if (StringUtils.isBlank(order)) {
                order = Constants.DESC;
            }
            String[] orders = StringUtils.split(order, ',');

            int i = 0;
            for (String orderBy : orderBys) {
                if (i != 0) {
                    sb.append(" " + Constants.MYSQL_LONG_TIME_FORMAT + " ");
                }
                sb.append(orderBy);

                String curOrder = Constants.DESC;
                if (orders.length == 1) {
                    curOrder = orders[0];
                } else {
                    if ((i + 1) > orders.length) {
                        curOrder = Constants.DESC;
                    } else {
                        curOrder = orders[i];
                    }
                }
                sb.append(" " + curOrder);
                i++;
            }
            logger.debug("getOrderBySqlString:{}", sb.toString());
            return sb.toString();
        }

        return "";
    }

    public List<PropertyFilter> getExtraCriteria() {
        return extraCriteria;
    }

    public void setExtraCriteria(List<PropertyFilter> extraCriteria) {
        this.extraCriteria = extraCriteria;
    }

    protected String constructSqlString(List<PropertyFilter> in) {
        //查询参数
        if (in != null && in.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (PropertyFilter filter : in) {
                sb.append("and" + filter.getSqlString());
            }
            logger.debug("getWhereSqlString:{}", sb.toString());
            return sb.toString();
        }
        return "";
    }
}
