package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionHandler {

    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    public static Connection getConnection(){
        String url = "jdbc:mysql://47.107.233.71:3306/coffee?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String name ="root";
        String psw ="0103Hsz..";
        Connection conn=threadLocal.get();
        if(conn==null){
            try {
                conn = DriverManager.getConnection(url, name, psw);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        Connection conn=threadLocal.get();
        if(conn!=null){
            conn.close();
            threadLocal.remove();
        }
    }
}
