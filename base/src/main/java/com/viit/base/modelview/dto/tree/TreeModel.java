package com.viit.base.modelview.dto.tree;

import java.util.Collection;

/**
 * 树型结构
 *
 * @author virit
 * @version 2019-10-32
 */
public interface TreeModel<T> extends Collection<ITreeNode> {

    <E> TreeModel<E> adapt(TreeAdapter<E> adapter);

    <E> TreeModel<E> adapt(Class<? extends TreeAdapter<E>> type);
}
