package com.qifan.javase.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyList {

    /**
     * 定义list实现类，实现如下方法：
     * lastIndexOf
     * indexOf
     * remove
     * void add(int index, E element);
     * E set(int index, E element);
     * E get(int index);
     * void clear();
     * boolean add(E e);
     * int size();
     * boolean isEmpty();
     * boolean contains(Object o);
     */

    private int capacity = 20;
    Object[] objects = new Object[capacity];
    int count = 0;

    public void ensureCapacity() {

    }

    public void rangeCheck(int index) {

    }

    public int size() {
        return count;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = count - 1; i >= 0; i--) {
                if (objects[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = count - 1; i >= 0; i--) {
                if (o.equals(objects[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < count; i++) {
                if (objects[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (o.equals(objects[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Object remove(int index) {
        rangeCheck(index);
        Object o = objects[index];
        for (int i = index + 1; i < count; i++) {
            objects[i - 1] = objects[i];
        }
        count--;
        return o;
    }

    public Object remove(Object o) {
        if (o == null) {
            for (int i = 0; i < count; i++) {
                if (objects[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (o.equals(objects[i])) {
                    return remove(i);
                }
            }
        }
        return -1;
    }

    public void add(int index, Object o) {
        rangeCheck(index);
        ensureCapacity();
        for (int i = count - 1; i >= index; i++) {
            objects[i + 1] = objects[i];
        }
        count++;
        objects[index] = o;
    }

    public boolean add(Object o){
        ensureCapacity();
        objects[count] = o;
        count++;
        return true;
    }

    public Object get(int index) {
        return objects[index];
    }

    public void set(int index, Object o) {
        rangeCheck(index);
        objects[index] = o;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void clear() {
        for (int i = 0; i < count - 1; i++) {
            objects[i] = null;
        }
        count = 0;
    }

    public boolean contains(Objects o) {
        return indexOf(o) < 0? false : true;
    }
}
