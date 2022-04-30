package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {
    public static Connection getConnection(){

        String url = "jdbc:mysql://47.107.233.71:3306/coffee?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String name ="root";
        String psw ="0103Hsz..";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url, name, psw);
        }catch (Exception r){
            r.printStackTrace();
        }
        return conn;
    }
}