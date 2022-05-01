/*
 * Created by JFormDesigner on Fri Apr 29 13:49:05 CST 2022
 */

package Frontdesk_Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import pay.WXPay;
import Frontdesk_Interface.Frontdesk_shoppingCar.*;

/**
 * @author 1
 */
public class Frontdesk_scanCode extends JFrame {
    public Frontdesk_scanCode() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBounds(0,0,500,500);

        contentPane.add(textField1);
        contentPane.add(textField2);
        textField1.setBounds(210, 90, 220, textField1.getPreferredSize().height);
        textField2.setBounds(210, 125, 220, textField1.getPreferredSize().height);
        textField2.setText(String.valueOf(Frontdesk_shoppingCar.sumPrice));

        //---- button1 ----
        button1.setText("\u652f\u4ed8");
        contentPane.add(button1);
        button1.setBounds(155, 160, 75, button1.getPreferredSize().height);
        button1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String num=textField1.getText();
                        try {
                            WXPay.scanCodeToPay(num);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        contentPane.add(button2);
        button2.setBounds(0, 0, 75, button1.getPreferredSize().height);
        button2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Frontdesk_settleMent();
                    }
                }
        );

        //---- label1 ----
        label1.setText("\u6761\u5f62\u7801\u6570\u5b57\u4e32\uff1a");
        contentPane.add(label1);
        label1.setBounds(75, 95, 100, 20);

        label2.setText("\u652f\u4ed8\u91d1\u989d");
        contentPane.add(label2);
        label2.setBounds(75, 125, 100, 20);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width+50, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height+100, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        this.setVisible(true);
        setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new Frontdesk_scanCode();
    }
}
