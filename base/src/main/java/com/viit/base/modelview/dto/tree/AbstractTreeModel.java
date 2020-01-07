package com.viit.base.modelview.dto.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 树模型适配器
 *
 * @author virit
 * @version 2019-10-31
 * @param <T> 实体类型
 */
public abstract class AbstractTreeModel<T> extends ArrayList<ITreeNode> implements TreeModel<T> {

    /**
     * 数据集合
     */
    private Collection<T> collection;
    /**
     * 排除id
     */
    private boolean excludeId = false;

    public AbstractTreeModel(Collection<T> collection) {
        this.collection = collection;
        generateTree();
    }

    public AbstractTreeModel(T[] array) {
        this.collection = new ArrayList<>(Arrays.asList(array));
        generateTree();
    }

    /**
     * 获取排除字段列表
     * @return 排除字段列表
     */
    public String[] getExcludeFields() {
        return new String[]{};
    }

    public String getChildrenFieldName() {
        return "children";
    }

    /**
     * 转换为节点
     * @param entity 实体
     */
    public abstract void mapData(T entity, TreeNode treeNode);

    /**
     * 生成树
     */
    private void generateTree() {
        if (collection == null) {
            return;
        }
        // 转换为树节点列表
        List<TreeNode> nodes = collection.stream().map(entity -> {
            TreeNode node = new TreeNode(getChildrenFieldName(), getExcludeFields());
            this.mapData(entity, node);
            return node;
        }).collect(Collectors.toList());
        Map<String, ITreeNode> idMap = new HashMap<>(10);
        nodes.forEach(node -> idMap.put(node.getId(), node));
        nodes.forEach(node -> {
            // 根节点
            if (TreeNode.ROOT.equals(node.getId())) {
                this.add(node);
            }
            // 其他节点
            ITreeNode parentNode = idMap.get(node.getParentId());
            if (parentNode != null) {
                parentNode.getChildren().add(node);
            } else {
                this.add(node);
            }
        });
    }

    @Override
    public <E> TreeModel<E> adapt(TreeAdapter<E> adapter) {
        return null;
    }

    @Override
    public <E> TreeModel<E> adapt(Class<? extends TreeAdapter<E>> type) {
        return null;
    }
}
