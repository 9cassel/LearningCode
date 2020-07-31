package com.qifan.javase.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyDataBase {

    File database;
    private int count = 0;

    private List<Integer> stuID =  new ArrayList<>();
    private List<String> stuName = new ArrayList<>();
    private List<Integer> stuAge = new ArrayList<>();
    private List<Boolean> stuGender = new ArrayList<>();

    DataInputStream dis;
    DataOutputStream dos;

    public MyDataBase(String path) throws IOException {
        database = new File(path);
        if (database.exists()) {
            readDateBase();
        } else {
            database.createNewFile();
        }
    }

    private void readDateBase(){
        try {
            dis = new DataInputStream(new FileInputStream(database));
            while (true) {
                stuID.add(dis.readInt());
                stuName.add(dis.readUTF());
                stuAge.add(dis.readInt());
                stuGender.add(dis.readBoolean());
                count++;
            }
        } catch (EOFException e) {
        } finally {
                try {
                    dis.close();
                } catch (IOException e) {
                }
                return;
        }
    }

    public boolean insert(Student student) throws IOException {

        if (student == null) {
            return false;
        }
        int id = student.getId();
        String name = student.getName();
        int age = student.getAge();
        boolean gender = student.isGender();

        stuID.add(id);
        stuName.add(name);
        stuAge.add(age);
        stuGender.add(gender);

        count++;
        return true;
    }

    public boolean delete(Student student) {
        int index = indexOf(student);
        if (index != -1) {
            stuName.remove(index);
            stuAge.remove(index);
            stuGender.remove(index);
            count--;
            return true;
        }
        return false;
    }

    public boolean update(Student oldStu, Student newStu) {

        int index = indexOf(oldStu);
        if (index != -1) {
            stuID.set(index, newStu.getId());
            stuName.set(index,newStu.getName());
            stuAge.set(index, newStu.getAge());
            stuGender.set(index,newStu.isGender());
            return true;
        }
        return false;
    }

    public boolean submit() throws IOException {
        dos = new DataOutputStream(new FileOutputStream(database));
        for (int i = 0; i < count; i++) {
            dos.writeInt(stuID.get(i));
            dos.writeUTF(stuName.get(i));
            dos.writeInt(stuAge.get(i));
            dos.writeBoolean(stuGender.get(i));
        }
        dos.close();
        return true;
    }

    public void select() {
        for (int i = 0; i < count; i++) {
            System.out.println("[" + stuID.get(i) + "," + stuName.get(i) + "," + stuAge.get(i) + "," + stuGender.get(i) + "]");;
        }
    }

    private int indexOf(Student student) {
        int id = student.getId();
        int index;
        index = stuID.indexOf(id);
        if (stuName.get(index).equals(student.getName())
                &&stuAge.get(index) == student.getAge()
                && stuGender.get(index) == student.isGender()){
            return index;
        }
        return -1;

    }
    
    public int size() {
        return count;
    }

}
