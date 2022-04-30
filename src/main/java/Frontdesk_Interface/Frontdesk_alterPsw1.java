/*
 * Created by JFormDesigner on Thu Apr 28 20:19:16 CST 2022
 */

package Frontdesk_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;
import Connection.*;

/**
 * @author 1
 */
public class Frontdesk_alterPsw1 extends JFrame {
    public Frontdesk_alterPsw1() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        checkBox1 = new JCheckBox();

        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u5f53\u524d\u5bc6\u7801");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(70, 50), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(70, 75), label2.getPreferredSize()));
        contentPane.add(passwordField1);
        passwordField1.setBounds(100, 70, 80, passwordField1.getPreferredSize().height);

        checkBox1.setText("\u663e\u793a\u5bc6\u7801");
        contentPane.add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(180, 67), checkBox1.getPreferredSize()));
        checkBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordField1.setEchoChar((char)0);
                }else{
                    passwordField1.setEchoChar('*');
                }
            }
        });

        //---- button1 ----
        button1.setText("\u4e0b\u4e00\u6b65");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(85, 105), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password1 = passwordField1.getText().trim();
                if(password1.equals("")){
                    JOptionPane.showMessageDialog(contentPane, "输入的密码不能为空！");
                }
                else{
                    if (password1.equals(Frontdesk_Login.password)){
                        dispose();
                        new Frontdesk_alterPsw2();
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane,"密码错误！");
                    }
                }
            }
        });

        //---- button2 -返回---
        button2.setText("\u8FD4\u56DE");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(0, 0), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_info();
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
    private JLabel label2;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;

    private JCheckBox checkBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new Frontdesk_alterPsw1();
    }
}
