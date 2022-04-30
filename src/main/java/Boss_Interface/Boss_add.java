/*
 * Created by JFormDesigner on Fri Apr 29 10:06:47 CST 2022
 */

package Boss_Interface;

import java.awt.*;
import Connection.ConnectionHandler1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author 1
 */
public class Boss_add extends JFrame {
    public Boss_add() {
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
        label1.setText("\u5546\u54c1ID\uff1a");
        contentPane.add(label1);
        label1.setBounds(20, 20, 55, 20);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 130, 20);


        //---- label2 ----
        label2.setText("\u5546\u54c1\u540d\u79f0\uff1a");
        contentPane.add(label2);
        label2.setBounds(240, 20, 90, 20);
        contentPane.add(textField2);
        textField2.setBounds(300, 20, 130, 20);



        //---- label3 ----
        label3.setText("\u5355\u4ef7\uff1a");
        contentPane.add(label3);
        label3.setBounds(20, 80, 55, 20);
        contentPane.add(textField3);
        textField3.setBounds(70, 80, 130, 20);



        //---- label4 ----
        label4.setText("类型");
        contentPane.add(label4);
        label4.setBounds(240, 80, 90, 20);
        contentPane.add(textField4);
        textField4.setBounds(300, 80, 130, 20);



        //---- label5 ----
        label5.setText("库存");
        contentPane.add(label5);
        label5.setBounds(20, 140, 55, 20);
        contentPane.add(textField5);
        textField5.setBounds(70, 140, 130, 20);



        //---- label6 ----
        label6.setText("\u5546\u54c1\u56fe\u7247\uff1a");
        contentPane.add(label6);
        label6.setBounds(240, 140, 90, 20);
        contentPane.add(textField6);
        textField6.setBounds(300, 140, 130, 20);


        //---- button1 ----
        button2.setText("保存");
        contentPane.add(button2);
        button2.setBounds(200, 300, 100, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int  id = Integer.parseInt(textField1.getText().trim());
                String name = textField2.getText().trim();
                Float price = Float.parseFloat(textField3.getText().trim());
                int cates =  Integer.parseInt(textField4.getText().trim());
                int stok = Integer.parseInt(textField5.getText().trim());

                String sql = "INSERT INTO product VALUES(?,?,?,?)";
                String sql2 = "INSERT INTO stock VALUES(?,?,?)";
                Connection conn = ConnectionHandler1.getConnection();
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1,id);
                    pstmt.setString(2,name);
                    pstmt.setFloat(3,price);
                    pstmt.setInt(4,cates);
                    pstmt.execute();


                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(1,id);
                    pstmt.setInt(2,stok);
                    pstmt.setInt(3,id);
                    pstmt.execute();

                    pstmt.close();
                    ConnectionHandler1.closeConnection();
                    JOptionPane.showMessageDialog(contentPane, "新增成功！");
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
