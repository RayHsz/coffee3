/*
 * Created by JFormDesigner on Fri Apr 29 13:17:54 CST 2022
 */

package Boss_Interface;

import Connection.ConnectionHandler1;
import bean.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class Boss_OrderShow extends JFrame {
    public Boss_OrderShow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        button5 = new JButton();
        label4 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table1.setModel(tableModel);
        //======== this ========
        final JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(1000,600));

        label1.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));
        label1.setText("今日订单");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);


        label2.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));
        label2.setText("今日总销售额:"+allPrice+"元");
        contentPane.add(label2);
        label2.setBounds(550,400,600,30);

        label3.setText("请输入查询日期:");
        contentPane.add(label3);
        label3.setBounds(160,355,100,30);

        label4.setFont(new
                Font("STHeiti Light", Font.BOLD,
                25));
        Connection conn = ConnectionHandler1.getConnection();
        String sql  = "SELECT NAME,MAX(allorder)\n" +
                "FROM (SELECT p.NAME,SUM(amount) AS allorder\n" +
                "FROM orders o , product p\n" +
                "WHERE o.item_id = p.ID \n" +
                "GROUP BY item_id) o\n" +
                "GROUP BY NAME";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                mostProduct=rs.getString(1);
            }
            rs.close();
            pstmt.close();
            ConnectionHandler1.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        label4.setText("销售量最高的商品为:"+mostProduct);
        contentPane.add(label4);
        label4.setBounds(600,450,600,30);

        button1.setText("刷新");
        contentPane.add(button1);
        button1.setBounds(510, 355, 100, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel);
                label2.setText("今日总销售额:"+allPrice+"元");
            }
        });

        button2.setText("新增");
        contentPane.add(button2);
        button2.setBounds(610, 355, 100, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        button3.setText("修改");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        contentPane.add(textField1);
        textField1.setBounds(270, 355, 130, 30);

        button4.setText("查询");
        contentPane.add(button4);
        button4.setBounds(410, 355, 100, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = textField1.getText().trim();
                DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase2(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel);
                label2.setText(date+"日的销售额:"+allPrice+"元");

            }
        });
        button5.setText("返回");
        contentPane.add(button5);
        button5.setBounds(20,355,100,30);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Boss_Main();
            }
        });

        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 50, 1000, 300);


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public Object[][] getDataFromDatabase() {
        Connection conn = ConnectionHandler1.getConnection();
        java.util.List<Order> list = new ArrayList<Order>();
        String sql = "SELECT o.ID,p.NAME,o.item_price,o.amount,o.order_time,o.order_price\n" +
                "FROM product p , orders o \n" +
                "WHERE order_time>=CURRENT_DATE AND o.item_id = p.ID";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            allPrice = 0;
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setProduct_name(rs.getString(2));
                order.setItem_price(rs.getFloat(3));
                order.setAmount(rs.getInt(4));
                order.setOrder_time(rs.getTimestamp(5));
                order.setOrder_price(rs.getFloat(6));
                allPrice = allPrice+rs.getFloat(6);
                list.add(order);
            }

            rs.close();
            pstmt.close();
            ConnectionHandler1.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getProduct_name();
                data[i][2] = list.get(i).getItem_price();
                data[i][3] = list.get(i).getAmount();
                data[i][4] = list.get(i).getOrder_time();
                data[i][5] = list.get(i).getOrder_price();
            }
        }
        return data;
    }

    public Object[][] getDataFromDatabase2() {
        String date = textField1.getText().trim();
        String date1 = date + " 00:00:00";
        String date2 = date + " 23:59:59";
        Connection conn = ConnectionHandler1.getConnection();
        java.util.List<Order> list = new ArrayList<Order>();
        String sql = "SELECT o.ID,p.NAME,o.item_price,o.amount,o.order_time,o.order_price\n" +
                "FROM product p , orders o \n" +
                "WHERE order_time BETWEEN ? and ? AND p.id = o.item_id";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,date1);
            pstmt.setString(2,date2);
            ResultSet rs = pstmt.executeQuery();
            allPrice = 0;
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setProduct_name(rs.getString(2));
                order.setItem_price(rs.getFloat(3));
                order.setAmount(rs.getInt(4));
                order.setOrder_time(rs.getTimestamp(5));
                order.setOrder_price(rs.getFloat(6));
                allPrice = allPrice+rs.getFloat(6);
                list.add(order);
            }

            rs.close();
            pstmt.close();
            ConnectionHandler1.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getProduct_name();
                data[i][2] = list.get(i).getItem_price();
                data[i][3] = list.get(i).getAmount();
                data[i][4] = list.get(i).getOrder_time();
                data[i][5] = list.get(i).getOrder_price();
            }
        }
        return data;
    }


    private String getAll(){
        return null;
    }


    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"订单ID", "商品名称", "单价", "数量", "下单时间","订单金额"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JLabel label1;
    private JButton button5;
    private JLabel label2;
    private JLabel label3;
    private float allPrice;
    private JLabel label4;
    private String mostProduct;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
