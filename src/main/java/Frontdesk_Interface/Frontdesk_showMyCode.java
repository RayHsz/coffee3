/*
 * Created by JFormDesigner on Thu Apr 28 21:43:42 CST 2022
 */

package Frontdesk_Interface;

import javafx.scene.image.Image;
import pay.WXPay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author 1
 */
public class Frontdesk_showMyCode extends JFrame {
    public Frontdesk_showMyCode() {
        initComponents();
    }

    private void initComponents()  {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        System.out.println("当前路径="+this.getClass().getResource("/"));
        img = new ImageIcon("src\\main\\java\\img\\new.jpg");// 创建图片对象
        label2.setIcon(img);

        //======== this ========
        //setIconImage(new ImageIcon(getClass().getResource("src/main/java/img/new.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));


        //---- label2 ----
        label2.setText("text");
        contentPane.add(label2);
        label2.setBounds(235, 100, 300, 300);

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

        //---- button2 -结算完成按钮---
        button2.setText("\u7ED3\u7B97\u5B8C\u6210");
        contentPane.add(button2);
        button2.setBounds(700, 550, 100, 50);

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
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private ImageIcon img;

    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_showMyCode();
    }
}
