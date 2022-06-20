/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ConnectDB {
    public static Connection getConnect(){
        String userid = "root";
        String pass = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost/sachgiare?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(dbURL, userid, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Loi ket noi"+ ex.getMessage());
        }
        return conn;
    }
}
