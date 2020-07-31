package com.qifan.javase.test;

import java.util.HashMap;

public class MyLinkedList {

    private int size = 0;
    private Node header = null;
    private Node tail = null;

    public MyLinkedList() {

    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object ret = null;
        if (index == 0) {
            ret = header.getElement();
            header = header.next;
        } else {
            Node temp = header;
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            if (index == size - 1) {
                tail = temp;
            }
            ret = temp.next.getElement();
            temp.next = temp.next.next;
        }

        size--;
        return ret;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index <= 0) {
            return false;
        }
        remove(index);
        return true;
    }

    public void clear() {
        size = 0;
        header = null;
        tail = null;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) > 0;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            Object obj = get(i);
            if (null == o) {
                if (obj == null) {
                    return i;
                }
            } else {
                if (o.equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexOf(Object o) {
        Node temp = header;
        if (check(temp, o)) {
            return 0;
        }

        for (int i = 1; i < size; i++) {
            temp = temp.next;
            if (check(temp, o)) {
                return i;
            }
        }
        return -1;
    }

    private boolean check(Node temp, Object o) {
        if (null == o) {
            if (temp.getElement() == null) {
                return true;
            }
        } else {
            if (o.equals(temp.getElement())) {
                return true;
            }
        }
        return false;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = header;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.getElement();
    }

    public void add(Object o) {
        Node node = new Node(o);
        if (size == 0) {
            header = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    private class Node {
        private Node next;
        private Object element;// 节点存储的真实数据

        public Node(Object element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }
    }



}

