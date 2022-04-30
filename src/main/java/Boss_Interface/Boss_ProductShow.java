/*
 * Created by JFormDesigner on Thu Apr 28 11:35:30 CST 2022
 */

package Boss_Interface;
import Connection.ConnectionHandler1;
import bean.Product;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class Boss_ProductShow extends JFrame {
    public Boss_ProductShow() {
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
        button5 = new JButton();
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
        label1.setText("商品信息");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        button1.setText("删除");
        contentPane.add(button1);
        button1.setBounds(510, 355, 100, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowNo = table1.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,0))) ;
                String sql = "DELETE FROM product WHERE ID = ?";
                String sql2 = "DELETE FROM stock WHERE pid = ?";
                Connection conn = ConnectionHandler1.getConnection();

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1,id);
                    pstmt.execute();

                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(1,id);
                    pstmt.execute();
                    JOptionPane.showMessageDialog(contentPane, "删除成功！");
                    pstmt.close();
                    ConnectionHandler1.closeConnection();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                DefaultTableModel tableModel3 = new DefaultTableModel(getDataFromDatabase(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel3);

            }
        });

        button2.setText("新增");
        contentPane.add(button2);
        button2.setBounds(610, 355, 100, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Boss_add();
            }
        });

        button3.setText("修改");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowNo = table1.getSelectedRow();
                if (rowNo == -1){
                    JOptionPane.showMessageDialog(contentPane, "请选中需要修改的商品。");
                    return;
                }
                int id = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,0))) ;

                String name = (String) table1.getValueAt(rowNo,1);
                Float price = (Float) table1.getValueAt(rowNo,2);
                int cates = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,3)) ) ;
                int stock = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,4)) ) ;

                Product product = new Product(id,name,price,cates,stock);

                new Boss_Update(product);
            }
        });

        contentPane.add(textField1);
        textField1.setBounds(270, 355, 130, 30);

        button4.setText("刷新");
        contentPane.add(button4);
        button4.setBounds(410, 355, 100, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel2 = new DefaultTableModel(getDataFromDatabase(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel2);
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
        Connection conn = null;
        java.util.List<Product> list = new ArrayList<Product>();
        Statement stmt = null;
        String sql = "SELECT p.ID,name,price,cates,stock \n" +
                "FROM product p,stock s \n" +
                "WHERE p.ID = s.pid";
        ResultSet rs = null;
        try {
            conn = ConnectionHandler1.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setCates(rs.getInt(4));
                product.setStock(rs.getInt(5));
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                ConnectionHandler1.closeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getName();
                data[i][2] = list.get(i).getPrice();
                data[i][3] = list.get(i).getCates();
                data[i][4] = list.get(i).getStock();
            }
        }
        return data;
    }
    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"id", "商品名称", "单价", "类型", "库存"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JLabel label1;
    private JButton button5;


    public static void main(String[] args) {
        new Boss_ProductShow();
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
