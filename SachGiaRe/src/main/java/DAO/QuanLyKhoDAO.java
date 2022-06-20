/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.chuyenmuc;
import Model.hoadon;
import Model.nhacungcap;
import Model.sach;
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
public class QuanLyKhoDAO {
    public static int insertNhaCungCapDAO(nhacungcap objNhaCungCap){
        String sql = "INSERT INTO sachgiare.nhacungcap (TenNhaCungCap, TrangThai) VALUES (?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objNhaCungCap.getTenNhaCungCap());
            sttm.setString(2, objNhaCungCap.getTrangThai());
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
    
    public static List<nhacungcap> getAllNhaCungCapDAO(){
        List<nhacungcap> lstNhaCungCap = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                nhacungcap objNhaCungCap = new nhacungcap();
                objNhaCungCap.setMaNhaCungCap(rs.getInt("MaNhaCungCap"));
                objNhaCungCap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                objNhaCungCap.setTrangThai(rs.getString("TrangThai"));
                lstNhaCungCap.add(objNhaCungCap);
            }
            return lstNhaCungCap;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int insertSachDAO(sach objSach){
        String sql = "INSERT INTO sachgiare.sach(TenSach, MaChuyenMuc, MaNhaCungCap, TacGia, GiaBan, SoLuong) VALUES (?,?,?,?,?,?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objSach.getTenSach());
            sttm.setInt(2, objSach.getMaChuyenMuc());
            sttm.setInt(3, objSach.getMaNhaCungCap());
            sttm.setString(4, objSach.getTacGia());
            sttm.setFloat(5, objSach.getGiaBan());
            sttm.setInt(6, objSach.getSoLuong());
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
    
    public static int insertChuyenMucDAO(chuyenmuc objChuyenMuc){
        String sql = "INSERT INTO sachgiare.chuyenmuc (TenChuyenMuc) VALUES (?)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setString(1, objChuyenMuc.getTenChuyenMuc());
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
    
    public static List<chuyenmuc> getAllChuyenMucDAO(){
        List<chuyenmuc> lstChuyenMuc = new ArrayList<>();
        String sql = "SELECT * FROM chuyenmuc";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                chuyenmuc objChuyenMuc = new chuyenmuc();
                objChuyenMuc.setMaChuyenMuc(rs.getInt("MaChuyenMuc"));
                objChuyenMuc.setTenChuyenMuc(rs.getString("TenChuyenMuc"));
                lstChuyenMuc.add(objChuyenMuc);
            }
            return lstChuyenMuc;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
