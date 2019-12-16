package com.viit.base.modelview.dto.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viit.base.utils.Setterable;

import java.util.*;

/**
 * 树节点实现
 *
 * @author virit
 * @version 2019-10-31
 */
public class TreeNode extends HashMap<String, Object> implements ITreeNode, Setterable<String, Object> {

    public static final String ROOT = "__root__";

    /**
     * id
     */
    private String id;
    /**
     * label
     */
    private String label;
    /**
     * 父级Id
     */
    @JsonIgnore
    private String parentId;
    /**
     * 父节点
     */
    @JsonIgnore
    private TreeNode parentNode;

    @JsonIgnore
    private List<String> excludeFields;

    @JsonIgnore
    private Map<String, Object> excludeMap = new HashMap<>(1);

    private String childrenFieldName;

    public TreeNode() {
        this( "children", new String[] {});
        this.set("children", new ArrayList<ITreeNode>());
    }

    public TreeNode(String childrenFieldName, String... excludeFields) {
        this.excludeFields = new ArrayList<>(Arrays.asList(excludeFields));
        this.childrenFieldName = childrenFieldName;
        this.set("children", new ArrayList<ITreeNode>());
    }

    public void setId(String id) {
        this.put("id", id);
    }

    public void setLabel(String label) {
        this.put("label", label);
    }

    public void setParentId(String parentId) {
        this.put("parentId", parentId);
    }

    public void setChildren(List<ITreeNode> children) {
        this.put(childrenFieldName, children);
    }

    @Override
    public String getId() {
        return (String) get("id");
    }

    @Override
    public String getLabel() {
        return (String) this.get("label");
    }

    @Override
    public String getParentId() {
        return (String) this.get("parentId");
    }

    @Override
    public List<ITreeNode> getChildren() {
        List<ITreeNode> children =  (List<ITreeNode>) this.get(childrenFieldName);
        if (children == null) {
            children = new ArrayList<>();
            this.setChildren(children);
        }
        return children;
    }

    @Override
    public ITreeNode getParentNode() {
        return this.parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public Setterable<String, Object> set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    @Override
    public Object put(String key, Object value) {
        if (excludeFields.contains(key)) {
            return this.excludeMap.put(key, value);
        } else {
            return super.put(key, value);
        }
    }

    @Override
    public Object get(Object key) {
        if (excludeFields.contains(key)) {
            return this.excludeMap.get(key);
        } else {
            return super.get(key);
        }
    }
}
