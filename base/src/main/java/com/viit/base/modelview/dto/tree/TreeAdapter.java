package com.viit.base.modelview.dto.tree;

public interface TreeAdapter<T> {

    T handle(ITreeNode node);
}
