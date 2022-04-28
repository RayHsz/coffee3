/*
 * Created by JFormDesigner on Thu Apr 28 20:53:45 CST 2022
 */

package Frontdesk_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import Connection.*;


/**
 * @author 1
 */
public class Frontdesk_alterPsw2 extends JFrame {
    public Frontdesk_alterPsw2() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        passwordField1 = new JPasswordField();
        checkBox1 = new JCheckBox();

        label3 = new JLabel();

        passwordField2 = new JPasswordField();
        checkBox2 = new JCheckBox();

        button2 = new JButton();

        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- button1 -返回按钮---
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(0, 0, 65, button1.getPreferredSize().height);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_alterPsw1();
            }
        });

        //---- label1 -请输入新密码---
        label1.setText("\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(115, 50), label1.getPreferredSize()));

        //---- label2 -新密码---
        label2.setText("\u65b0\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(70, 85), label2.getPreferredSize()));
        contentPane.add(passwordField1);
        passwordField1.setBounds(120, 80, 95, passwordField1.getPreferredSize().height);

        //---- checkBox1 -显示密码1---
        checkBox1.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(220, 75), checkBox1.getPreferredSize()));
        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField1.setEchoChar((char)0);
                }else{
                    passwordField1.setEchoChar('*');
                }
            }
        });

        //---- label3 -确认密码---
        label3.setText("\u786e\u8ba4\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(60, 125), label3.getPreferredSize()));
        contentPane.add(passwordField2);
        passwordField2.setBounds(120, 120, 95, passwordField2.getPreferredSize().height);

        //---- checkBox2 -显示密码2---
        checkBox2.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox2);
        checkBox2.setBounds(new Rectangle(new Point(220, 115), checkBox2.getPreferredSize()));
        checkBox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField2.setEchoChar((char)0);
                }else{
                    passwordField2.setEchoChar('*');
                }
            }
        });

        //---- button2 -提交按钮---
        button2.setText("\u63d0\u4ea4");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(120, 165), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPsw = new String(passwordField1.getPassword());
                String surePsw = new String(passwordField2.getPassword());

                if (newPsw.equals("") || surePsw.equals("")){
                    JOptionPane.showMessageDialog(contentPane,"密码不能为空！");
                }
                else{
                    if (!newPsw.equals(surePsw)){
                        JOptionPane.showMessageDialog(contentPane,"两次输入的密码不一致！");
                    }
                    else{
                        Connection conn=null;
                        try{
                            conn = ConnectionHandler.getConnection();
                            String sql = "update emp set empPassword = ? where empAccount = ?";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1,newPsw);
                            pstmt.setString(2,Frontdesk_Login.username);
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(contentPane,"密码修改成功！请重新登录！");
                            dispose();
                            new Frontdesk_Login();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JCheckBox checkBox1;
    private JLabel label3;
    private JPasswordField passwordField2;
    private JCheckBox checkBox2;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_alterPsw2();
    }
}
