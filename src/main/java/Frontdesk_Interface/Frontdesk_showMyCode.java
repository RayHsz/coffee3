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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import Connection.*;

/**
 * @author 1
 */
public class Frontdesk_showMyCode extends JFrame {
    public static int resultNum1=0;
    public static int resultNum2=0;
    public static boolean flag=true;
    public Frontdesk_showMyCode() {
        initComponents();

        //初始化完成界面之后，先记录一次当前订单表中的数据
        resultNum1 = new CountOrders().getOrdersNum();
        //开启一个线程开始监听 orders订单表中的数据变化
        Thread t = new Thread(new Mythread());
        t.start();

    }

    private void initComponents()  {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        // System.out.println("当前路径="+this.getClass().getResource("/"));
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
                flag=false;//修改标志位，关闭当前的进程
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
        label1.setBounds(new Rectangle(new Point(300, 10),new Dimension(300,50)));
        label1.setFont(new
                Font("STHeiti Light", Font.BOLD,
                30));

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

    //内部类 : 线程，每三秒查询一次订单表数据，查看订单总数是否多了一单
    class Mythread implements Runnable{

        @Override
        public void run() {
            //三秒查询一次数据库订单表，以此查看用户是否支付成功
            while (flag){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                resultNum2 = new CountOrders().getOrdersNum();

                if (resultNum2==(resultNum1+1)){
                    //进入此分支，说明订单表新插入了一条订单，用户支付成功
                    //设置flag退出while循环，完成线程任务
                    flag=false;
                    new Frontdesk_successPay();
                    dispose();
                }
                 // System.out.println("resultNum1="+Frontdesk_showMyCode.resultNum1);
                 // System.out.println("resultNum2="+Frontdesk_showMyCode.resultNum2);
            }
        }
    }

}

 //该类中有一个方法，getTotal_Orders 去获当前数据库订单表中的数据
 class CountOrders{
    Connection conn=null;
    int resultNum = 0;
     int getOrdersNum(){
         try{
             conn = ConnectionHandler.getConnection();
             String sql = "select count(*) resultNum from orders";
             PreparedStatement pstmt =conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()){
                 resultNum = rs.getInt("resultNum");
             }

             conn.close();
             pstmt.close();

         }catch (SQLException e){
             e.printStackTrace();
         }

         //System.out.println("resultNum="+resultNum);
         return resultNum;
     }
 }


