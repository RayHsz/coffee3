/*
 * Created by JFormDesigner on Thu Apr 28 17:59:15 CST 2022
 */

package Frontdesk_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import Connection.*;

/**
 * @author 1
 */
public class Frontdesk_info extends JFrame {
    public Frontdesk_info() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- button1 -返回按钮---
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(0, 0, 60, button1.getPreferredSize().height);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_Mainmenu();
            }
        });

        //---- label1 -姓名---
        label1.setText("\u59D3\u540D\uFF1A");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(65, 60), label1.getPreferredSize()));


        //---- label2 ----
        label2.setText("****");
        contentPane.add(label2);
        label2.setBounds(110, 60, 100, label2.getPreferredSize().height);
        Connection conn = null;
        String userName=null;
        String userAccount=null;
        String password=null;
        try{
            conn = ConnectionHandler.getConnection();
            String sql = "select * from emp where empAccount=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Frontdesk_Login.username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                userName = rs.getString("empname");
                userAccount = rs.getString("empAccount");
                password = rs.getString("empPassword");
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        label2.setText(userName);



        //---- label3 -登陆账户名---
        label3.setText("\u767b\u9646\u8D26\u53F7\uFF1A");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(40, 90), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("********");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(110, 90), label4.getPreferredSize()));
        label4.setText(userAccount);

        //---- button2 -修改密码---
        button2.setText("\u4fee\u6539\u5bc6\u7801");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(70, 145), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_alterPsw1();
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
    private JLabel label3;
    private JLabel label4;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_info();
    }
}
