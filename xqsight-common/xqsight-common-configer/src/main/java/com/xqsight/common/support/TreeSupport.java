package com.xqsight.common.support;

import com.xqsight.common.model.TreeBaseModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangganggang on 16/6/15.
 */
public class TreeSupport<T extends TreeBaseModel<T>> {


    public T generateFullTree(List<T> list) {
        T root = getRoot(list, "0");
        if (root == null)
            return null;
        return treeList(list, root);
    }


    public List<T> generateTree(List<T> list) {
        T root = getRoot(list, "0");
        if (root == null)
            return null;
        return generateTree(list, root.getId());
    }


    public List<T> generateTree(List<T> list, String rootId) {
        List<T> lists = new ArrayList<>();
        for (T t : list) {
            if (StringUtils.equals(t.getParentId(), rootId)) {
                List<T> childrenList = generateTree(list, t.getId());
                t.setChildren(childrenList);
                lists.add(t);
            }
        }
        return lists;
    }


    private T treeList(List<T> list, T root) {
        List<T> childrenList = generateTree(list, root.getId());
        root.setChildren(childrenList);
        return root;
    }


    private T getRoot(List<T> list, String parentId) {
        T ret = null;
        if (list == null)
            return ret;

        for (T t : list) {
            if (StringUtils.equals(t.getParentId(), parentId))
                ret = t;
        }
        return ret;
    }


    public List<String> getChildrenId(List<T> list, String parentId) {
        List<String> longs = new ArrayList<String>();
        for (T t : list) {
            if (StringUtils.equals(t.getId(), parentId)) {
                longs.add(t.getId());
                if (t.getChildren().size() > 0)
                    longs.addAll(getChildrenId(t.getChildren(), t.getId()));
            }
        }
        return longs;
    }


}
