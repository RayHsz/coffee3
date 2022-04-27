package Main_Interface;

import Frontdesk_Interface.Frontdesk_Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Created by JFormDesigner on Tue Apr 19 12:38:23 CST 2022
 */


/**
 * @author 1
 */
public class Main_Interface extends JFrame {
    public Main_Interface() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));


        //---- button2--前台登录 ----
        button2.setText("\u524d\u53f0\u767b\u5f55");
        contentPane.add(button2);
        button2.setBounds(110, 90, 175, 50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_Login();
            }
        });

        //---- button3 --商家登录----
        button3.setText("\u5546\u5bb6\u767b\u5f55");
        contentPane.add(button3);
        button3.setBounds(110, 155, 175, 50);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                dispose();
//                new Main_Interface();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Main_Interface();
    }
}