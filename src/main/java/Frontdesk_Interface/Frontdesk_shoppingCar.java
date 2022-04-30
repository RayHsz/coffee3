/*
 * Created by JFormDesigner on Wed Apr 27 20:37:06 CST 2022
 */

package Frontdesk_Interface;

import bean.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Connection.*;
import bean.ShoppingCar;
import jdk.nashorn.internal.scripts.JO;
import pay.WXPay;

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
        setTitle("购物车");
        final DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);

        // 设置表格中的数据居中显示
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class,r);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 45,760,300);

        //---- button1 -移出购物车---
        button1.setText("\u79fb\u51fa\u8d2d\u7269\u8f66");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(55, 355), button1.getPreferredSize()));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取当前选中的行
                int selectedRow = table1.getSelectedRow();


                    try{
                        //通过当前选中的行，获取该行商品的名称
                        Object data2[][]=queryData();
                        String pname =(String)data2[selectedRow][0];

                        Connection conn = ConnectionHandler.getConnection();
                        String sql="delete from shoppingCar where name=?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,pname);
                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(contentPane,"移除成功！");

                        //刷新
                        final DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        table1.setModel(tableModel);

                    }catch (SQLException r){
                        r.printStackTrace();
                    } catch (ArrayIndexOutOfBoundsException r){
                        r.printStackTrace();
                        JOptionPane.showMessageDialog(contentPane,"请选择需要移除的商品");
                    }

                final DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel);

                try{

                    Connection conn = ConnectionHandler.getConnection();
                    String sql = "select cast(sum(price*num) as decimal(10,2)) sumPrice from shoppingCar";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs=pstmt.executeQuery();
                    while(rs.next()){
                        sumPrice = rs.getFloat("sumPrice");
                    }
                    String key = ""+sumPrice;
                    label1.setText("\u603b\u4ef7"+sumPrice+"\uffe5");

                }catch(SQLException r){
                    r.printStackTrace();
                }
                label1.setText("\u603b\u4ef7"+sumPrice+"\uffe5");
//                }
            }
        });

        //---- button2 -清空购物车---
        button2.setText("\u6e05\u7a7a\u8d2d\u7269\u8f66");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(241, 355), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane,"清空完毕！");
                //确定,并且当前只能确定，还没实现取消
                try{
                    Connection conn = ConnectionHandler.getConnection();
                    Statement stmt = conn.createStatement();
                    String sql = "delete from shoppingCar";
                    stmt.executeUpdate(sql);
                }catch (SQLException r){
                    r.printStackTrace();
                }

                //清空购物车之后重置总价 ￥
                final DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel);

                try{

                    Connection conn = ConnectionHandler.getConnection();
                    String sql = "select cast(sum(price*num) as decimal(10,2)) sumPrice from shoppingCar";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs=pstmt.executeQuery();
                    while(rs.next()){
                        sumPrice = rs.getFloat("sumPrice");
                    }
                    String key = ""+sumPrice;
                    label1.setText("\u603b\u4ef7"+sumPrice+"\uffe5");

                }catch(SQLException r){
                    r.printStackTrace();
                }
                label1.setText("\u603b\u4ef7"+sumPrice+"\uffe5");


            }
        });

        //---- label1 -总价：xx ￥---
        label1.setText("\u603b\u4ef7\uff1a\uffe5");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(406, 355),new Dimension(100,20)));
        try{

            Connection conn = ConnectionHandler.getConnection();
            String sql = "select cast(sum(price*num) as decimal(10,2)) sumPrice from shoppingCar";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                   sumPrice = rs.getFloat("sumPrice");
            }
            String key = ""+sumPrice;
            label1.setText("\u603b\u4ef7"+sumPrice+"\uffe5");

        }catch(SQLException e){
            e.printStackTrace();
        }

        //---- button3 -结算---
        button3.setText("\u7ed3\u7b97");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(570, 355), button3.getPreferredSize()));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * 首先查看当前购物车中有没有商品
                 * 1.有则跳转到结算界面
                 * 2.没有则提示当前购物车没有需要结算的商品
                 */
                Connection conn=null;
                try{
                    conn = ConnectionHandler.getConnection();
                    Statement stmt = conn.createStatement();
                    String sql = "select * from shoppingCar";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()){
                        dispose();
                        //跳到结算界面
                        new Frontdesk_settleMent();
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane,"当前购物车没有需要结算的商品！");
                    }
                    conn.close();
                    stmt.close();

                }catch(SQLException r){
                    r.printStackTrace();
                }



            }
        });

        //---- button5 -返回按钮---
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

        Statement stmt = null;//SQL语句对象，拼SQL
        String sql = "SELECT * FROM shoppingCar";
        //System.out.println("即将执行的sql：" + sql);
        ResultSet rs = null;
        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //每循环一次就是一个对象，把这个对象放入容器（List（有序可重复）、Set（无序不可重复）、Map（key、value结构）
                ShoppingCar shoppingCar = new ShoppingCar();
                shoppingCar.setName(rs.getString("name"));
                shoppingCar.setNum(rs.getInt("num"));
                shoppingCar.setPrice(rs.getFloat("price"));


                list.add(shoppingCar);
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

    public static float sumPrice=0;

    private String head[] = {"商品名称", "数量", "单价"};
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Frontdesk_shoppingCar();
    }
}
