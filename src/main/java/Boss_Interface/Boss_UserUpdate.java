/*
 * Created by JFormDesigner on Fri Apr 29 19:26:13 CST 2022
 */

package Boss_Interface;

import java.awt.*;
import Connection.ConnectionHandler1;
import bean.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author 1
 */
public class Boss_UserUpdate extends JFrame {
    User user;
    Boolean isUpdate;
    public Boss_UserUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
        initComponents();
    }
    public Boss_UserUpdate(User user,Boolean isUpdate) {
        this.isUpdate = isUpdate;
        this.user = user;
        initComponents();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        button2 = new JButton();
        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(500,400));

        //---- label1 ----
        label1.setText("员工ID");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);
        if (isUpdate){
            textField1.setText(String.valueOf(user.getId()));
        }


        //---- label2 ----
        label2.setText("员工姓名");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);
        if (isUpdate){
            textField2.setText(user.getName());
        }



        //---- label3 ----
        label3.setText("用户名");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);
        if (isUpdate){
            textField3.setText(user.getUserName());
        }


        //---- label4 ----
        label4.setText("密码");
        contentPane.add(label4);
        label4.setBounds(240, 80, 90, 20);
        contentPane.add(textField4);
        textField4.setBounds(300, 80, 130, 20);
        if (isUpdate){
            textField4.setText(user.getPassWord());
        }



        //---- label5 ----
        label5.setText("职位");
        contentPane.add(label5);
        label5.setBounds(20, 140, 55, 20);
        contentPane.add(textField5);
        textField5.setBounds(70, 140, 130, 20);
        if (isUpdate){
            textField5.setText(user.getJob());
        }



        //---- label6 ----
        label6.setText("年龄");
        contentPane.add(label6);
        label6.setBounds(240, 140, 90, 20);
        contentPane.add(textField6);
        textField6.setBounds(300, 140, 130, 20);
        if (isUpdate){
            textField6.setText(String.valueOf(user.getAge()));
        }


        //---- button1 ----
        button2.setText("保存");
        contentPane.add(button2);
        button2.setBounds(200, 300, 100, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(isUpdate)) {
                    int id = Integer.parseInt(textField1.getText().trim());
                    String name = textField2.getText().trim();
                    String userName = textField3.getText().trim();
                    String passWord = textField4.getText().trim();
                    String job = textField5.getText().trim();
                    int age = Integer.parseInt(textField6.getText().trim());

                    String sql = "INSERT INTO emp VALUES(?,?,?,?,?,?)";
                    Connection conn = ConnectionHandler1.getConnection();
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, userName);
                        pstmt.setString(4, passWord);
                        pstmt.setString(5, job);
                        pstmt.setInt(6, age);
                        pstmt.execute();
                        JOptionPane.showMessageDialog(contentPane, "已添加！");
                        pstmt.close();
                        ConnectionHandler1.closeConnection();
                        dispose();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else {
                    int id = Integer.parseInt(textField1.getText().trim());
                    String name = textField2.getText().trim();
                    String userName = textField3.getText().trim();
                    String passWord = textField4.getText().trim();
                    String job = textField5.getText().trim();
                    int age = Integer.parseInt(textField6.getText().trim());

                    String sql = "UPDATE emp SET empname = ?,empAccount = ?,empPassword = ?,job = ?,age = ? WHERE ID = ?";
                    Connection conn = ConnectionHandler1.getConnection();
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,name);
                        pstmt.setString(2,userName);
                        pstmt.setString(3,passWord);
                        pstmt.setString(4,job);
                        pstmt.setInt(5,age);
                        pstmt.setInt(6,id);
                        pstmt.execute();
                        JOptionPane.showMessageDialog(contentPane, "已修改！");

                        pstmt.close();
                        ConnectionHandler1.closeConnection();
                        dispose();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

            }
        });
        //---- button1  ----
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(20, 180), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    private JButton button2;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label6;
    private JTextField textField6;
    private JButton button1;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
