package com.viit.utils.collection;

import com.google.common.base.Preconditions;

import java.util.Collection;

/**
 * 集合处理工具
 * @param <T> T
 */
public class CollectionHandler<T> {

    private Collection<T> collection;

    private CollectionHandler(Collection<T> collection) {
        Preconditions.checkNotNull(collection);
        this.collection = collection;
    }

    /**
     * 带索引的forEach
     * @param consumer consumer
     */
    public void forEachWithIndex(IndexConsumer<T> consumer) {
        forEachWithIndex(true, consumer);
    }

    /**
     * 带索引的forEach
     * @param consumer consumer
     */
    public void forEachWithIndex(boolean flag, IndexConsumer<T> consumer) {
        if (!flag) {
            return;
        }
        int index = 0;
        for (T item : collection) {
            consumer.accept(index, item);
            index ++;
        }
    }

    public static <T>CollectionHandler<T> of(Collection<T> collection) {
        return new CollectionHandler<>(collection);
    }
}
