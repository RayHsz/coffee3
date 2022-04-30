/*
 * Created by JFormDesigner on Thu Apr 28 09:58:00 CST 2022
 */

package Boss_Interface;

import Connection.ConnectionHandler1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author 1
 */
public class Boss_My extends JFrame {
    public Boss_My() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        passwordField3 = new JPasswordField();
        label4 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- label1 -用户名---
        label1.setText("\u7528\u6237\u540d");
        contentPane.add(label1);
        label1.setBounds(165, 40, 45, 25);
        contentPane.add(textField1);
        textField1.setBounds(225, 40, 140, 30);

        //---- label2 -密码---
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(165, 90, 40, 25);

        //---- label3 -新密码---
        label3.setText("\u65b0\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(160, 140, 55, 25);

        contentPane.add(passwordField1);
        passwordField1.setBounds(225, 90, 140, 30);
        //---- 显示密码按钮 ----
        checkBox1.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(365, 93), checkBox1.getPreferredSize()));
        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField1.setEchoChar((char)0);
                }else{
                    passwordField1.setEchoChar('*');
                }
            }
        });

        contentPane.add(passwordField2);
        passwordField2.setBounds(225, 140, 140, 30);
        //---- 显示密码按钮 ----
        checkBox2.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox2);
        checkBox2.setBounds(new Rectangle(new Point(365, 143), checkBox2.getPreferredSize()));
        checkBox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField2.setEchoChar((char)0);
                }else{
                    passwordField2.setEchoChar('*');
                }
            }
        });

        contentPane.add(passwordField3);
        passwordField3.setBounds(225, 190, 140, 30);
        //---- 显示密码按钮 ----
        checkBox3.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox3);
        checkBox3.setBounds(new Rectangle(new Point(365, 193), checkBox3.getPreferredSize()));
        checkBox3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField3.setEchoChar((char)0);
                }else{
                    passwordField3.setEchoChar('*');
                }
            }
        });

        //---- label4 -确认密码---
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        contentPane.add(label4);
        label4.setBounds(155, 190, 70, 25);

        //---- button1    返回----
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(10, 10), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_Main();
            }
        });

        //---- button2     确认修改----
        button2.setText("\u786e\u8ba4\u4fee\u6539");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(250, 255), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textField1.getText().trim();
                String passWord = passwordField1.getText().trim();
                String newPassWord = passwordField2.getText().trim();
                String confPassWord = passwordField3.getText().trim();

                if (userName.equals("")||passWord.equals("")||newPassWord.equals("")||confPassWord.equals("")){
                    JOptionPane.showMessageDialog(contentPane, "请补全信息！");
                    return;
                }
                if (!newPassWord.equals(confPassWord)){
                    JOptionPane.showMessageDialog(contentPane, "前后两次的密码不一致！");
                    return;
                }
                Connection conn = ConnectionHandler1.getConnection();
                String sql = "SELECT * FROM boss WHERE name = ? and password = ? ";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,userName);
                    pstmt.setString(2,passWord);

                    ResultSet rs = pstmt.executeQuery();
                    //有结果集， 说明账号密码正确
                    if (rs.next()){
                        int id = rs.getInt(1);
                        String sql2 = "UPDATE boss SET password = ? WHERE ID = ?";
                        pstmt = conn.prepareStatement(sql2);
                        pstmt.setInt(2,id);
                        pstmt.setString(1,newPassWord);
                        pstmt.execute();
                        JOptionPane.showMessageDialog(contentPane, "密码修改成功！");
                        ConnectionHandler1.closeConnection();
                        pstmt.close();
                        dispose();
                        new Boss_Main();
                    }else {
                        JOptionPane.showMessageDialog(contentPane, "用户名或密码错误！");
                        return;
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });



        pack();
        setLocationRelativeTo(getOwner());

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;
    private JLabel label4;
    private JButton button1;
    private JButton button2;

    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Boss_My();
    }
}
