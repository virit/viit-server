package com.viit.utils.collection;

@FunctionalInterface
public interface IndexConsumer<T> {

    void accept(int index, T item);
}
