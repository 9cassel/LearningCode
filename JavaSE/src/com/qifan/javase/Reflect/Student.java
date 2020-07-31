package com.qifan.javase.Reflect;

public class Student {

    String name;
    int age;
    boolean gender;
    Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", teacher=" + teacher +
                '}';
    }
}
