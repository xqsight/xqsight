package com.xqsight.common.support;

import com.google.common.collect.Lists;
import com.xqsight.common.model.TreeBaseModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangganggang on 16/6/15.
 * @since jdk1.8
 */
public class TreeSupport<T extends TreeBaseModel<T>> {

    /**
     * 获取list的跟节点ID
     * @param list
     * @return
     */
    public T getRoot(List<T> list){
        if(list.isEmpty()) return null;
        return list.stream().collect(Collectors.minBy(Comparator.comparing(T :: getParentId))).get();
    }



    public T generateFullTree(List<T> list) {
        T root = getRoot(list);
        if (root == null)
            return null;
        return treeList(list, root);
    }


    public List<T> generateTree(List<T> list) {
        T root = getRoot(list);
        if (root == null)
            return null;
        return generateTree(list, root.getId());
    }


    public List<T> generateTree(List<T> list, String rootId) {
        List<T> lists = new ArrayList<>();

        list.stream().filter(t -> t.getParentId().equals(rootId)).forEach(t -> {
            List<T> childrenList = generateTree(list, t.getId());
            t.setChildren(childrenList);
            lists.add(t);
        });

        return lists;
    }


    private T treeList(List<T> list, T root) {
        List<T> childrenList = generateTree(list, root.getId());
        root.setChildren(childrenList);
        return root;
    }


    public List<String> getChildrenId(List<T> list, String parentId) {
        List<String> longs = new ArrayList<String>();

        list.stream().filter(t -> t.getId().equals(parentId)).forEach(t -> {
            longs.add(t.getId());
            if (t.getChildren().size() > 0)
                longs.addAll(getChildrenId(t.getChildren(), t.getId()));
        });

        return longs;
    }


}
