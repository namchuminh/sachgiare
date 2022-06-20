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
public class DangNhapDAO {
    
    public static int checkDangNhap(String SoDienThoai, String MatKhau){
        String sql = "SELECT * FROM sachgiare.nhanvien Where SoDienThoai = ? AND MatKhau = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, SoDienThoai);
            sttm.setString(2, MatKhau);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static nhanvien getNhanVienDAO(String SoDienThoai){
        nhanvien objNV = new nhanvien();
        String sql = "SELECT * FROM sachgiare.nhanvien Where SoDienThoai = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, SoDienThoai);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                objNV.setMaNhanVien(rs.getInt("MaNhanVien"));
                objNV.setHoTen(rs.getString("HoTen"));
                objNV.setGioiTinh(rs.getString("GioiTinh"));
                objNV.setNgaySinh(rs.getString("NgaySinh"));
                objNV.setQueQuan(rs.getString("QueQuan"));
                objNV.setSoDienThoai(rs.getString("SoDienThoai"));
                objNV.setMatKhau(rs.getString("MatKhau"));
                objNV.setAdmin(rs.getInt("Admin"));
                return objNV;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
