/*
 * Created by JFormDesigner on Thu Apr 28 21:35:23 CST 2022
 */

package Frontdesk_Interface;

import pay.WXPay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author 1
 */
public class Frontdesk_settleMent extends JFrame {
    public Frontdesk_settleMent() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();
        button3 = new JButton();
        label3 = new JLabel();

        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));

        //---- label1 ----
        label1.setText("\u8bf7\u9009\u62e9\u7ed3\u7b97\u65b9\u5f0f");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(110, 30), label1.getPreferredSize()));

        //---- button1 -返回按钮---
        button1.setText("\u8fd4\u56de");
        contentPane.add(button1);
        button1.setBounds(0, 0, 65, button1.getPreferredSize().height);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_shoppingCar();
            }
        });

        //---- button2 -"你扫我"按钮---
        button2.setText("\u4f60\u626b\u6211");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(115, 60), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                //根据订单生成二维码后，出示商家二维码
                new Frontdesk_showMyCode();

            }
        });


        //---- label2 ----
        label2.setText("\uff08\u51fa\u793a\u5546\u5bb6\u6536\u6b3e\u7801\uff09");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(190, 65), label2.getPreferredSize()));

        //---- button3 -"我扫你"按钮---
        button3.setText("\u6211\u626b\u4f60");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(115, 100), button3.getPreferredSize()));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //此处拿起扫码枪获取客户收款码
                JOptionPane.showMessageDialog(contentPane,"扫码枪获取客户收款码！(还未实现)");
            }
        });

        //---- label3 ----
        label3.setText("\uff08\u5ba2\u6237\u51fa\u793a\u4ed8\u6b3e\u7801\uff09");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(190, 105), label3.getPreferredSize()));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JLabel label2;
    private JButton button3;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_settleMent();
    }
}
