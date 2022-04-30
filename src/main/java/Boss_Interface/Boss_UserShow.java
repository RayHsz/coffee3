/*
 * Created by JFormDesigner on Fri Apr 29 11:05:35 CST 2022
 */

package Boss_Interface;

import bean.Employee;
import Connection.ConnectionHandler1;
import bean.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class Boss_UserShow extends JFrame {
    public Boss_UserShow() {
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
        // 设置表格中的数据居中显示
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class,r);

        //======== this ========
        final JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        contentPane.setPreferredSize(new Dimension(1000,600));

        label1.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));
        label1.setText("用户信息");
        contentPane.add(label1);
        label1.setBounds(460, 0, 600, 60);

        button1.setText("删除");
        contentPane.add(button1);
        button1.setBounds(510, 355, 100, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowNo = table1.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,0)));
                String sql = "DELETE FROM emp WHERE ID = ?";
                Connection conn = ConnectionHandler1.getConnection();
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1,id);
                    pstmt.execute();
                    JOptionPane.showMessageDialog(contentPane, "已删除！");
                    DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    table1.setModel(tableModel);

                    pstmt.close();
                    ConnectionHandler1.closeConnection();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        button2.setText("新增");
        contentPane.add(button2);
        button2.setBounds(610, 355, 100, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Boss_UserUpdate(false);
            }
        });

        button3.setText("修改");
        contentPane.add(button3);
        button3.setBounds(710, 355, 100, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowNo = table1.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,0)));
                String name = (String) table1.getValueAt(rowNo,1);
                String userName = (String) table1.getValueAt(rowNo,2);
                String passWord = (String) table1.getValueAt(rowNo,3);
                String job = (String) table1.getValueAt(rowNo,4);
                int age = Integer.parseInt(String.valueOf(table1.getValueAt(rowNo,5)));
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setUserName(userName);
                user.setPassWord(passWord);
                user.setJob(job);
                user.setAge(age);

                new Boss_UserUpdate(user,true);

            }
        });

        contentPane.add(textField1);
        textField1.setBounds(270, 355, 130, 30);

        button4.setText("刷新");
        contentPane.add(button4);
        button4.setBounds(410, 355, 100, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = new DefaultTableModel(getDataFromDatabase(), head) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.setModel(tableModel);
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
        java.util.List<Employee> list = new ArrayList<Employee>();
        String sql = "SELECT * FROM emp";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setEmpname(rs.getString(2));
                employee.setEmpAccount(rs.getString(3));
                employee.setEmpPassword(rs.getString(4));
                employee.setJob(rs.getString(5));
                employee.setAge(rs.getInt(6));
                list.add(employee);
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
                data[i][1] = list.get(i).getEmpname();
                data[i][2] = list.get(i).getEmpAccount();
                data[i][3] = list.get(i).getEmpPassword();
                data[i][4] = list.get(i).getJob();
                data[i][5] = list.get(i).getAge();
            }
        }
        return data;
    }

    private JScrollPane scrollPane1;
    private JTable table1;
    private String head[] = {"员工ID", "员工姓名", "用户名", "密码", "职位","年龄"};
    private Object[][] data = null;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;
    private JLabel label1;
    private JButton button5;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
