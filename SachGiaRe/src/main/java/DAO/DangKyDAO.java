/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.nhanvien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DangKyDAO {
    public static int checkDangKy(nhanvien objNV){
        String sql = "SELECT * FROM sachgiare.nhanvien Where SoDienThoai = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        ResultSet rs;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objNV.getSoDienThoai());
            rs = sttm.executeQuery();
            if(rs.next()){
                return 0;
            }else{
                sql = "INSERT INTO nhanvien (HoTen, GioiTinh, NgaySinh, QueQuan, SoDienThoai, MatKhau, Admin) VALUES (?, ?, ?, ?, ?, ?, 0)";
                sttm = conn.prepareStatement(sql);
                sttm.setString(1, objNV.getHoTen());
                sttm.setString(2, objNV.getGioiTinh());
                sttm.setString(3, objNV.getNgaySinh());
                sttm.setString(4, objNV.getQueQuan());
                sttm.setString(5, objNV.getSoDienThoai());
                sttm.setString(6, objNV.getMatKhau());
                result = sttm.executeUpdate();
                if(result == 1){
                    return 1;
                }else{
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
