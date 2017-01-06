package com.xqsight.common.service;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.dao.Dao;
import com.xqsight.common.model.Model;
import com.xqsight.common.orm.Criterion;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.Sort;
import com.xqsight.commons.support.ParamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * 领域对象的业务管理类基类.
 * <p>
 * 提供了领域对象的简单CRUD方法.
 *
 * @param <T>  领域对象类型
 * @param <PK> 领域对象的主键类型
 */
@Transactional
public abstract class DefaultEntityService<T extends Model, PK extends Serializable> {

    protected Logger logger = LogManager.getLogger(getClass());

    /**
     * 在子类实现的回调函数,为EntityManager提供默认CRUD操作所需的DAO.
     */
    protected abstract Dao<T, PK> getDao();

    // CRUD函数 //
    @Transactional(readOnly = true, timeout = 30)
    public T get(PK id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Transactional(timeout = 30)
    public void save(T entity,boolean selective) {
        if (selective) {
            getDao().insertSelective(entity);
        }else{
            getDao().insert(entity);
        }
    }

    @Transactional(timeout = 30)
    public void update(T entity, boolean selective) {
        if (selective) {
            getDao().updateByPrimaryKeySelective(entity);
        } else {
            getDao().updateByPrimaryKey(entity);
        }
        //update(entity, false, selective);
    }

    @Transactional(timeout = 30)
    public void delete(PK id) {
        getDao().deleteByPrimaryKey(id);
    }

    /**
     * 按id逻辑删除对象.
     */
    @Transactional(timeout = 30)
    public void logicDel(final PK id) {
        T t = get(id);
        ParamSupport.setModelInvalidStatus(t);
        getDao().updateByPrimaryKey(t);
    }

    @Transactional(timeout = 30)
    public void delete(List<PK> ids) {
        if (ids != null && ids.size() > 0) {
            Dao<T, PK> dao = getDao();
            for (PK id : ids) {
                dao.deleteByPrimaryKey(id);
            }
        }
    }

    @Transactional(readOnly = true, timeout = 30)
    public List<T> getAll() {
        Criterion criterion = new Criterion();
        return getDao().find(criterion);
    }


    @Transactional(readOnly = true)
    public List<T> search(List<PropertyFilter> filters) {
        Criterion criterion = new Criterion();
        criterion.setCriteria(filters);
        //开始查询
        return getDao().find(criterion);
    }

    @Transactional(readOnly = true)
    public List<T> search(List<PropertyFilter> filters, List<Sort> sorts) {
        Criterion criterion = new Criterion(filters);

        if (sorts != null) {
            StringBuilder orderBySb = new StringBuilder();
            StringBuilder orderSb = new StringBuilder();

            int i = 0;
            for (Sort sort : sorts) {
                if (i != 0) {
                    orderBySb.append(Constants.COMMA_SIGN_SPLIT_NAME);
                    orderSb.append(Constants.COMMA_SIGN_SPLIT_NAME);
                }
                i++;
                orderBySb.append(sort.getOrderBy());
                orderSb.append(sort.getOrder());
            }
            criterion.setOrderBy(orderBySb.toString());
            criterion.setOrder(orderSb.toString());
        }

        return getDao().find(criterion);
    }
}
