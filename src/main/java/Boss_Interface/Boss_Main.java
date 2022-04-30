/*
 * Created by JFormDesigner on Wed Apr 27 19:54:21 CST 2022
 */

package Boss_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author 1
 */
public class Boss_Main extends JFrame {
    public Boss_Main() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- button1 商品管理----
        button1.setText("\u5546\u54c1\u7ba1\u7406");
        contentPane.add(button1);
        button1.setBounds(235, 50, 120, 50);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_ProductShow();
            }
        });

        //---- button2 订单管理----
        button2.setText("\u8ba2\u5355\u7ba1\u7406");
        contentPane.add(button2);
        button2.setBounds(235, 110, 120, 50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_OrderShow();
            }
        });

        //---- button3 用户管理----
        button3.setText("\u7528\u6237\u7ba1\u7406");
        contentPane.add(button3);
        button3.setBounds(235, 170, 120, 50);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_UserShow();
            }
        });

        //---- button4 我的----
        button4.setText("\u6211\u7684");
        contentPane.add(button4);
        button4.setBounds(235, 230, 120, 50);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_My();
            }
        });

        //---- button5 登出----
        button5.setText("\u767b\u51fa");
        contentPane.add(button5);
        button5.setBounds(235, 290, 120, 50);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_Login();
            }
        });

        pack();
        setLocationRelativeTo(getOwner());

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Boss_Main();
    }
}
