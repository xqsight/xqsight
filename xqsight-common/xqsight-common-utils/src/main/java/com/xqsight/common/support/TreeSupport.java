package com.xqsight.common.support;

import com.xqsight.common.model.AbstractTreeModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangganggang on 16/6/15.
 *
 * @since jdk1.8
 */
public class TreeSupport<T extends AbstractTreeModel<T>> {

    /**
     * 获取list的跟节点ID
     *
     * @param list
     * @return
     */
    public T getRoot(List<T> list) {
        if (list.isEmpty()) return null;
        return list.stream().collect(Collectors.minBy(Comparator.comparing(T::getParentId))).get();
    }

    public List<T> getRoots(List<T> list, String rootParentId) {
        if (list.isEmpty() || StringUtils.isBlank(rootParentId)) return null;
        return list.stream().filter(t -> rootParentId.equals(t.getParentId())).collect(Collectors.toList());
    }

    public List<T> generateTree(List<T> list) {
        T root = getRoot(list);
        if (root == null)
            return null;
        List<T> roots = getRoots(list,root.getParentId());
        roots.stream().forEach(t -> {
            t.setChildren(generateChildrenTree(list,t.getId()));
        });
        return roots;
    }

    public T generateFullTree(List<T> list) {
        T root = getRoot(list);
        if (root == null)
            return null;
        return treeList(list, root);
    }

    public List<T> generateChildrenTree(List<T> list, String rootId) {
        List<T> lists = new ArrayList<>();
        list.stream().filter(t -> t.getParentId().equals(rootId)).forEach(t -> {
            List<T> childrenList = generateChildrenTree(list, t.getId());
            t.setChildren(childrenList);
            lists.add(t);
        });
        return lists;
    }


    private T treeList(List<T> list, T root) {
        List<T> childrenList = generateChildrenTree(list, root.getId());
        root.setChildren(childrenList);
        return root;
    }

}
