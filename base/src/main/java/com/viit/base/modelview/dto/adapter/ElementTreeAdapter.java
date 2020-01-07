package com.viit.base.modelview.dto.adapter;

import com.viit.base.modelview.dto.tree.ITreeNode;
import com.viit.base.modelview.dto.tree.TreeAdapter;

/**
 * 树适配器
 *
 * @author virit
 * @version 2019-10-31
 */
public class ElementTreeAdapter implements TreeAdapter<ITreeNode> {

    @Override
    public ITreeNode handle(ITreeNode node) {
        return node;
    }
}
