/*
 * Created by JFormDesigner on Tue Apr 19 13:05:59 CST 2022
 */

package Frontdesk_Interface;

import Connection.ConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 1
 */
public class Frontdesk_Mainmenu extends JFrame {
    public Frontdesk_Mainmenu() {
        initComponents();
    }

    private void initComponents() {

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));
        setTitle("主界面");

        //---- button1 点单按钮----
        button1.setText("\u70B9\u5355");
        contentPane.add(button1);
        button1.setBounds(110, 25, 175, 50);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Frontdesk_GoodsMenu();
                dispose();
            }
        });


        //---- button2 我的购物车按钮----
        button2.setText("\u6211\u7684\u8d2d\u7269\u8f66");
        contentPane.add(button2);
        button2.setBounds(110, 125, 175, 50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Frontdesk_shoppingCar();
                dispose();
            }
        });

        //---- button3 退出菜单按钮----
        button3.setText("\u9000\u51fa");
        contentPane.add(button3);
        button3.setBounds(110,330,175,50);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //---- button4 返回----
        button4.setText("\u8fd4\u56de");
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(0, 0), button2.getPreferredSize()));
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Frontdesk_Login();
                dispose();
            }
        });

        //---- button5 我的信息按钮----
        button5.setText("\u6211\u7684\u4fe1\u606f");
        contentPane.add(button5);
        button5.setBounds(110, 225, 175, 50);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Frontdesk_info();
                dispose();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_Mainmenu();
    }
}
