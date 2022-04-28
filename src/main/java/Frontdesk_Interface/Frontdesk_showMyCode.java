/*
 * Created by JFormDesigner on Thu Apr 28 21:43:42 CST 2022
 */

package Frontdesk_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author 1
 */
public class Frontdesk_showMyCode extends JFrame {
    public Frontdesk_showMyCode() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- button1 -返回按钮---
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(0, 0, 65, button1.getPreferredSize().height);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                //跳转回选择结算方式界面
                new Frontdesk_settleMent();
            }
        });

        //---- label1 -请扫码支付---
        label1.setText("\u8bf7\u626b\u7801\u652f\u4ed8");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(355, 10), label1.getPreferredSize()));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_showMyCode();
    }
}
