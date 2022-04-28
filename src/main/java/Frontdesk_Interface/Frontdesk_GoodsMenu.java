/*
 * Created by JFormDesigner on Wed Apr 27 15:05:55 CST 2022
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
import jdk.nashorn.internal.scripts.JO;

/**
 * @author 1
 */
public class Frontdesk_GoodsMenu extends JFrame {
    public Frontdesk_GoodsMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

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
        scrollPane1.setBounds(30, 40, 515, 295);

        //---- button1 -加入购物车按钮---
        button1.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(55, 350), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取当前选中的行
               int selectedRow = table1.getSelectedRow();

               Connection conn = ConnectionHandler.getConnection();
               try{
                   //通过当前选中的行，获取该行商品的名称
                   Object data2[][]=queryData();
                   String pname =(String)data2[selectedRow][1];

                   /* 然后JDBC查询当前 shoppingCar 购物车表中是否存在该商品
                    * 1.已经存在：则 num=num+1
                    * 2.还未存在，则查询 product 商品表，将对应的数据插入 购物车表
                    */

                   String sql = "select name from shoppingCar where name=?";
                   PreparedStatement pstmt = conn.prepareStatement(sql);

                   pstmt.setString(1,pname);

                   ResultSet rs = pstmt.executeQuery();

                   if(rs.next()){ //若购物车中已经存在该商品,则 num=num+1
                       String sql2 = "update shoppingCar set num=num+1 where name=?";
                       PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                       pstmt2.setString(1,pname);
                       pstmt2.executeUpdate();
                       JOptionPane.showMessageDialog(contentPane,"加入购物车成功,数量加一！");
                   }
                   //若购物车未存在该商品
                   else{
                       float price=0;
                      String sql3 = "select * from product where name=?";
                      String sql4 = "insert into shoppingCar(name,num,price) " +
                                    "values(?,?,?)";
                      PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                      pstmt3.setString(1,pname);
                      ResultSet rs1 = pstmt3.executeQuery();
                      while (rs1.next()){
                           price=rs1.getFloat("price");
                      }

                      PreparedStatement pstmt4 = conn.prepareStatement(sql4);
                      pstmt4.setString(1,pname);
                      pstmt4.setInt(2,1);
                      pstmt4.setFloat(3,price);
                      pstmt4.executeUpdate();
                      JOptionPane.showMessageDialog(contentPane,"加入购物车成功！");
                   }
               }catch (SQLException r){
                   r.printStackTrace();
               }catch (ArrayIndexOutOfBoundsException m){
                   JOptionPane.showMessageDialog(contentPane,"请选择需要加入购物车的商品！");
                   m.printStackTrace();
               }

            }
        });

        //---- button2 -购物车按钮---
        button2.setText("\u8d2d\u7269\u8f66");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(460, 355), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_shoppingCar();
            }
        });

        //---- button3 -返回按钮---
        button3.setText("\u8FD4\u56DE");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(0, 0), button2.getPreferredSize()));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontdesk_Mainmenu();
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
        java.util.List<Product> list = new ArrayList<Product>();
        Connection conn = null;

        Statement stmt = null;//SQL语句对象，拼SQL
        String sql = "SELECT * FROM product";
        //System.out.println("即将执行的sql：" + sql);
        ResultSet rs = null;
        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //每循环一次就是一个对象，把这个对象放入容器（List（有序可重复）、Set（无序不可重复）、Map（key、value结构）
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setCates(rs.getInt(4));

                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源：数据库连接很昂贵
            try {
                rs.close();
                stmt.close();
                conn.close();//关连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        data = new Object[list.size()][head.length];
        //把集合里的数据放入Obejct这个二维数组
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getName();
                data[i][2] = list.get(i).getPrice();
                data[i][3] = list.get(i).getCates();
            }
        }
        return data;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private String head[] = {"id", "商品名称", "单价", "商品类别"};
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_GoodsMenu();
    }
}
