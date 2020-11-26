package com.example.codeittestingtask;

import androidx.annotation.NonNull;

class SimpleDoublyLinkedList<E> implements SimpleLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SimpleDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) throws RuntimeException {

        if (index > size || index < 0) {
            throw new RuntimeException("Illegal index");
        }

        if (element == null) {
            throw new RuntimeException("Null is not accepted");
        }

        Node<E> newNode = new Node<>(element, null, null);

        if (size == 0) {            //if list is empty
            head = newNode;
            tail = newNode;

            size++;
            return;
        }

        if (index == 0) {           //insert at first position
            head.prev = newNode;
            newNode.next = head;
            head = newNode;

            size++;
            return;
        }

        if (index == size) {            //insert at last position
            newNode.prev = tail;
            l.next = newNode;
            tail = newNode;

            size++;
            return;
        }

        Node<E> curr = head;

        for (int i = 1; i < size; i++) {
            if (i == index) {
                Node<E> temp = curr.next;
                curr.next = newNode;
                newNode.prev = curr;
                newNode.next = temp;
                temp.prev = newNode;
            }
            curr = curr.next;
        }
        size++;
    }

    @Override
    public void remove(int index) throws RuntimeException {
        if (size == 0 || index < 0 || index >= size) throw new RuntimeException("Illegal index");

        if (size == 1) {          //there is only 1 element in list
            head = null;
            tail = null;
            size = 0;
            return;
        }

        if (index == 0) {            //if index points at first element
            head = head.next;
            head.prev = null;
            size--;
            return;
        }

        if (index == size - 1) {           //if index points at last element
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }

        Node<E> temp = head.next;
        for (int i = 1; i < size - 1; i++) {
            if (i == index) {
                Node<E> before = temp.prev;
                Node<E> after = temp.next;

                before.next = after;
                after.prev = before;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    @Override
    public void clear() {
        Node<E> curr = head;
        while (curr != null) {
            Node<E> nextNode = curr.next;
            curr.next = null;
            curr.element = null;
            curr.prev = null;
            curr = nextNode;
        }
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int indexOf(E element) throws RuntimeException {

        Node<E> curr = head;
        int result = 0;

        while (curr != null) {
            if (curr.element.equals(element)) {
                return result;
            }
            result++;
            curr = curr.next;
        }

        throw new RuntimeException("No such element in list");
    }

    @NonNull
    @Override
    public String toString() {

        Node<E> curr = head;
        StringBuilder str = new StringBuilder();

        while (curr != null) {
            str.append(curr.element.toString());
            str.append(" ");
            curr = curr.next;
        }
        return str.toString();
    }

}
