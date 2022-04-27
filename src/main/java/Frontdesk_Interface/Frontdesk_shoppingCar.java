/*
 * Created by JFormDesigner on Wed Apr 27 20:37:06 CST 2022
 */

package Frontdesk_Interface;

import bean.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Connection.*;
import bean.ShoppingCar;
import jdk.nashorn.internal.scripts.JO;

/**
 * @author 1
 */
public class Frontdesk_shoppingCar extends JFrame {
    public Frontdesk_shoppingCar() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        button3 = new JButton();
        button5 = new JButton();

        //======== this ========
        final Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(800,600));
        final DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 45, scrollPane1.getPreferredSize().width, 275);

        //---- button1 -�Ƴ����ﳵ---
        button1.setText("\u79fb\u51fa\u8d2d\u7269\u8f66");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(55, 330), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //��ȡ��ǰѡ�е���
                int selectedRow = table1.getSelectedRow();
                //JOptionPane.showMessageDialog(contentPane,"ִ�е��˴���");
                //ͨ����ǰѡ�е��У���ȡ������Ʒ������
                Object data2[][]=queryData();
                String pname =(String)data2[selectedRow][0];

//                if ((selectedRow==-1)==true){
//                    JOptionPane.showMessageDialog(contentPane,"��ѡ���Ƴ�����Ʒ��");
//                }
//                else{
                    //ȥ���ݿ��н� ���ﳵ�и���Ʒ��ɾ����
                    try{
                        Connection conn = ConnectionHandler.getConnection();
                        String sql="delete from shoppingCar where name=?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,pname);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(contentPane,"�Ƴ��ɹ���");
                    }catch (SQLException r){
                        r.printStackTrace();
                    }
//                }
                //ˢ��
                dispose();
                new Frontdesk_shoppingCar();
            }
        });

        //---- button2 -��չ��ﳵ---
        button2.setText("\u6e05\u7a7a\u8d2d\u7269\u8f66");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(171, 330), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane,"ȷ��Ҫ��չ��ﳵ��");
                //ȷ��,���ҵ�ǰֻ��ȷ������ûʵ��ȡ��
                try{
                    Connection conn = ConnectionHandler.getConnection();
                    Statement stmt = conn.createStatement();
                    String sql = "delete from shoppingCar";
                    stmt.executeUpdate(sql);
                }catch (SQLException r){
                    r.printStackTrace();
                }
                dispose();
                new Frontdesk_shoppingCar();
            }
        });

        //---- label1 -�ܼۣ�xx ��---
        label1.setText("\u603b\u4ef7\uff1a\uffe5");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(286, 335),new Dimension(100,20)));
        try{
            float sumPrice=0;
            Connection conn = ConnectionHandler.getConnection();
            String sql = "select sum(price*num) sumPrice from shoppingCar";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                   sumPrice = rs.getFloat("sumPrice");
            }
            String key = ""+sumPrice;
            label1.setText("\u603b\u4ef7"+key+"\uffe5");

        }catch(SQLException e){
            e.printStackTrace();
        }

        //---- button3 -����---
        button3.setText("\u7ed3\u7b97");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(400, 330), button3.getPreferredSize()));

        //---- button5 -���ذ�ť---
        button5.setText("\u8FD4\u56DE");
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(0, 0), button5.getPreferredSize()));
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_GoodsMenu();
            }
        });

        pack();
        setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public Object[][] queryData() {
        Object[][] data = null;
        java.util.List<ShoppingCar> list = new ArrayList<ShoppingCar>();
        Connection conn = null;

        Statement stmt = null;//SQL������ƴSQL
        String sql = "SELECT * FROM shoppingCar";
        //System.out.println("����ִ�е�sql��" + sql);
        ResultSet rs = null;
        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //ÿѭ��һ�ξ���һ�����󣬰�����������������List��������ظ�����Set�����򲻿��ظ�����Map��key��value�ṹ��
                ShoppingCar shoppingCar = new ShoppingCar();
                shoppingCar.setName(rs.getString("name"));
                shoppingCar.setNum(rs.getInt("num"));
                shoppingCar.setPrice(rs.getFloat("price"));


                list.add(shoppingCar);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //�ͷ���Դ�����ݿ����Ӻܰ���
            try {
                rs.close();
                stmt.close();
                conn.close();//������
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        data = new Object[list.size()][head.length];
        //�Ѽ���������ݷ���Obejct�����ά����
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getName();
                data[i][1] = list.get(i).getNum();
                data[i][2] = list.get(i).getPrice();

            }
        }
        return data;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JButton button3;
    private JButton button5;

    private String head[] = {"��Ʒ����", "����", "����"};
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_shoppingCar();
    }
}
