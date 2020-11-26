package com.example.codeittestingtask;

public interface SimpleLinkedList<E> {

    int size();

    void add(int index, E element) throws RuntimeException;

    void remove(int index) throws RuntimeException;

    void clear();

    int indexOf(E element) throws RuntimeException;
}
