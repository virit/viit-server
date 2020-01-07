package com.viit.base.modelview.dto.tree;

import java.util.List;

/**
 * 树节点
 *
 * @author virit
 * @version 2019-10-32
 */
public interface ITreeNode {

    /**
     * 获取id
     * @return id id
     */
    String getId();

    /**
     * 获取文本
     * @return label 文本
     */
    String getLabel();

    /**
     * 获取父级id
     * @return 父级id
     */
    String getParentId();

    /**
     * 获取子级菜单
     * @return children 子级菜单
     */
    List<ITreeNode> getChildren();

    /**
     * 获取父节点
     * @return 父节点
     */
    ITreeNode getParentNode();
}
