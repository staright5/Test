package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private static final String Driver ="com.mysql.cj.jdbc.Driver";
    private static final String Url ="jdbc:mysql://localhost:3306/quanlynhansutest";
    private static final String Username ="root";
    private static final String Password ="Phuong295246";

    public static Connection getConnection() {
        Connection conn = null;
        try{
            Class.forName(Driver);
            conn = DriverManager.getConnection(Url,Username,Password);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) {
        try{
            if (callSt != null) callSt.close();
            if(conn != null) conn.close();
        } catch(Exception e){
                e.printStackTrace();
            }
        }
    }



