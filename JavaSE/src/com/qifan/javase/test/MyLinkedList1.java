package com.qifan.javase.test;

public class MyLinkedList1 {

    private int size = 0;
    private Node header = null;
    private Node tail = null;

    private class Node {

        private Object element;
        private Node next;

        public Node(Object element) {
            this.element = element;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public MyLinkedList1() {
    }

    public int size() {
        return size;
    }

    public void add(Object o) {
        Node node = new Node(o);
    }

}
