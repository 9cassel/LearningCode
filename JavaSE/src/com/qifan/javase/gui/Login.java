package com.qifan.javase.gui;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JLabel user = new JLabel("user:");
    private JTextField userValue = new JTextField(16);
    private JLabel password = new JLabel("password:");
    private JPasswordField passwordField = new JPasswordField(16);
    private JButton login = new JButton("login");
    private JButton cancel = new JButton("cancel");


    public Login() {
        super("login");
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout());
        this.setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(710,390);
        this.add(user);
        this.add(userValue);
        this.add(password);
        this.add(passwordField);
        this.add(login);
        this.add(cancel);
//        login.addActionListener(this);
//        cancel.addActionListener(this);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JDialog jDialog = new JDialog();
//        if (e.getSource() == login.getAction()) {
//            String user = userValue.getText();
//            String password = passwordField.getPassword().toString();
//            System.out.println(user);
//            System.out.println(password);
//            JDBCConn conn = new JDBCConn(user, password);
//            if (conn.isConn()) {
//                JOptionPane.showMessageDialog(this,"login successfully!");
//            } else {
//                JOptionPane.showMessageDialog(this,"Faild to login!");
//            }
//        }
//    }

    public static void main(String[] args) {
        new Login();
    }
}
