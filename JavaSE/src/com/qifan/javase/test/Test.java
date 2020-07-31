package com.qifan.javase.test;

public class Test {

    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();

        for (int i = 0; i < 10; i++) {
            mll.add(i);
        }

        for (int i = 0; i < mll.size(); i++) {
            System.out.println(mll.get(i));
        }

        System.out.println(mll.remove(0));
    }
}
