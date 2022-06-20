/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.chamcong;
import Model.nhanvien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class QuanLyNhanVienDAO {
    public static List<nhanvien> getAllNhanVienDAO(){
        List<nhanvien> lstNV = new ArrayList<>();
        String sql = "SELECT * FROM sachgiare.nhanvien";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                nhanvien objNV = new nhanvien();
                objNV.setMaNhanVien(rs.getInt("MaNhanVien"));
                objNV.setHoTen(rs.getString("HoTen"));
                objNV.setGioiTinh(rs.getString("GioiTinh"));
                objNV.setNgaySinh(rs.getString("NgaySinh"));
                objNV.setQueQuan(rs.getString("QueQuan"));
                objNV.setSoDienThoai(rs.getString("SoDienThoai"));
                objNV.setMatKhau(rs.getString("MatKhau"));
                objNV.setAdmin(rs.getInt("Admin"));
                lstNV.add(objNV);
            }
            return lstNV;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<chamcong> getChamCongDAO(){
        List<chamcong> lstCC = new ArrayList<>();
        String sql = "SELECT * FROM sachgiare.chamcong ORDER BY MaChamCong DESC";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                chamcong objCC = new chamcong();
                objCC.setMaChamCong(rs.getInt("MaChamCong"));
                objCC.setMaNhanVien(rs.getInt("MaNhanVien"));
                objCC.setNgayChamCong(rs.getString("NgayChamCong"));
                objCC.setTrangThai(rs.getString("TrangThai"));
                lstCC.add(objCC);
            }
            return lstCC;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int insertNhanVienDAO(nhanvien objNV){
        String sql = "INSERT INTO nhanvien(HoTen,GioiTinh,NgaySinh,QueQuan,SoDienThoai,MatKhau,Admin) VALUES (?,?,?,?,?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objNV.getHoTen());
            sttm.setString(2, objNV.getGioiTinh());
            sttm.setString(3, objNV.getNgaySinh());
            sttm.setString(4, objNV.getQueQuan());
            sttm.setString(5, objNV.getSoDienThoai());
            sttm.setString(6, objNV.getMatKhau());
            sttm.setInt(7, objNV.getAdmin());
            result = sttm.executeUpdate();
            if(result == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBanHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static int updateNhanVienDAO(nhanvien objNV){
        String sql = "UPDATE nhanvien SET HoTen=?,GioiTinh=?,NgaySinh=?,QueQuan=?,SoDienThoai=?,MatKhau=?, Admin = ? WHERE MaNhanVien = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objNV.getHoTen());
            sttm.setString(2, objNV.getGioiTinh());
            sttm.setString(3, objNV.getNgaySinh());
            sttm.setString(4, objNV.getQueQuan());
            sttm.setString(5, objNV.getSoDienThoai());
            sttm.setString(6, objNV.getMatKhau());
            sttm.setInt(7, objNV.getAdmin());
            sttm.setInt(8, objNV.getMaNhanVien());
            result = sttm.executeUpdate();
            if(result == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBanHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static int deleteNhanVienDAO(int MaNhanVien){
        String sql = "DELETE FROM sachgiare.nhanvien WHERE MaNhanVien = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setInt(1, MaNhanVien);
            result = sttm.executeUpdate();
            if(result == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBanHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
