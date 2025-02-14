/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhatLe
 */
public class jdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost;databaseName=QUANLYSTORE12";
    private static String user ="sa";
    private static String pass ="caolephat0373119096";
    
    
    //nạp driver
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
           throw new RuntimeException(ex);
        }
    }
    
    //xây dựng PreparedStatement
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, user, pass);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }else{
            pstmt = connection.prepareStatement(sql);
        }
        for (int i =0; i< args.length; i++){
            pstmt.setObject(i+1, args[i]);
        }
        return pstmt;
    }
    
    /* phương thức update 
    thực hiệncác thao tác update insert or delte hoặc cácthủ tục lưu trữ SP.
    */
    public static int update(String sql,  Object... args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally{
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* thuẹc hiện câu lệnh SQL, truy vấn (Select) hoặc thủ tục lưu trữ  truy vấn dữ liệu
    */
  
    public static ResultSet query(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* Object */
    //trả về đối tượng bất kì (chung chung)
    public static Object value(String sql, Object... args){
        try {
            ResultSet rs = query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
  

    
}
