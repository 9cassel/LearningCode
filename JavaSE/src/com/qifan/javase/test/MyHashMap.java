package com.qifan.javase.test;

import java.util.HashMap;

import static java.util.Objects.hash;

public class MyHashMap {

    private Node[] table;
    private int threshold;

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    float loadFactor;

    private int size;

    public int size() {
        return size;
    }

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity,DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity,float loadFactor) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity :" + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor < 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor :" + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);

    }

//    public Object put(Object key, Object value) {
//        return putValue(hash(key), key, value);
//    }

//    private Object putValue(int hash, Object key, Object value) {
//        if (table == null || size == 0) {
//
//        }
//    }

    public int hash(Object obj) {
        return obj.hashCode();
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

//    private Node[] resize() {
//
//        Node[] oldTable = table;
//        int oldCap = (oldTable == null)? 0 : oldTable.length;
//        int oldThr = threshold;
//        int newCap;
//        int newThr;
//
//        if ()
//
//        return newTable;
//    }

    private static class Node {

        final int hash;
        final Object key;
        Object value;
        Node next;

        public Node(int hash, Object key, Object value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Object setValue(Object newValue) {
            Object oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "key=" + key +
                    ", value=" + value;
        }
    }

}

