/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.nhanvien;
import Model.thongke;
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
public class ThongKeDAO {
    public static String DoanhThuNgayDAO(String ThoiGian){
        String DoanhThu = null;
        String sql = "SELECT SUM(DonGia) AS DoanhThuNgay FROM sachgiare.hoadon WHERE ThoiGian LIKE '%" + ThoiGian + "%'";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                DoanhThu = String.valueOf(rs.getBigDecimal("DoanhThuNgay"));
            }
            return DoanhThu;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String DoanhThuHomQuaDAO(){
        String DoanhThu = null;
        String sql = "SELECT SUM(DonGia) AS DoanhThuHomQua FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-1, DATE) AND ThoiGian < CURRENT_DATE";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                DoanhThu = String.valueOf(rs.getBigDecimal("DoanhThuHomQua"));
            }
            return DoanhThu;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String DoanhThuTuanDAO(){
        String DoanhThu = null;
        String sql = "SELECT SUM(DonGia) AS DoanhThuTuan FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-7, DATE);";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                DoanhThu = String.valueOf(rs.getBigDecimal("DoanhThuTuan"));
            }
            return DoanhThu;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String SachBanNgayDAO(String ThoiGian){
        String SachBan = null;
        String sql = "SELECT SUM(SoLuong) AS SachBanNgay FROM sachgiare.hoadon WHERE ThoiGian LIKE '%" + ThoiGian + "%'";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                SachBan = String.valueOf(rs.getInt("SachBanNgay"));
            }
            return SachBan;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String SachBanHomQuaDAO(){
        String SachBan = null;
        String sql = "SELECT SUM(SoLuong) AS SachHomQua FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-1, DATE) AND ThoiGian < CURRENT_DATE";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                SachBan = String.valueOf(rs.getInt("SachHomQua"));
            }
            return SachBan;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String SachBanTuanDAO(){
        String SachBan = null;
        String sql = "SELECT SUM(SoLuong) AS SachBanTuan FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-7, DATE);";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                SachBan = String.valueOf(rs.getInt("SachBanTuan"));
            }
            return SachBan;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String DoanhThuThangDAO(){
        String DoanhThu = null;
        String sql = "SELECT SUM(DonGia) AS DoanhThuThang FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-(DAY(CURRENT_DATE) - 1), DATE)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                DoanhThu = String.valueOf(rs.getBigDecimal("DoanhThuThang"));
            }
            return DoanhThu;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String SachBanThangDAO(){
        String SachBan = null;
        String sql = "SELECT SUM(SoLuong) AS SachBanThang FROM hoadon WHERE ThoiGian >= CONVERT(CURRENT_DATE-(DAY(CURRENT_DATE) - 1), DATE)";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()){
                SachBan = String.valueOf(rs.getInt("SachBanThang"));
            }
            return SachBan;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<thongke> getALLThongTinSachBanDAO(){
        List<thongke> lstTK = new ArrayList<>();
        String sql = "SELECT sach.MaSach, sach.TenSach, hoadon.ThoiGian, nhanvien.HoTen, hoadon.SoLuong, hoadon.DonGia, hoadon.TrangThai FROM hoadon, nhanvien, sach WHERE nhanvien.MaNhanVien = hoadon.MaNhanVien AND hoadon.MaSach = sach.MaSach AND hoadon.ThoiGian >= CONVERT(CURRENT_DATE-(DAY(CURRENT_DATE) - 1), DATE) ORDER BY hoadon.MaHoaDon DESC;";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                thongke objTK = new thongke();
                objTK.setMaSach(rs.getInt("MaSach"));
                objTK.setTenSach(rs.getString("TenSach"));
                objTK.setThoiGian(rs.getString("ThoiGian"));
                objTK.setHoTen(rs.getString("HoTen"));
                objTK.setSoLuong(rs.getInt("SoLuong"));
                String DonGia = String.valueOf(rs.getString("DonGia"));
                if(DonGia.length() == 8){
                    DonGia = DonGia.substring(0, 1) + "." + DonGia.substring(1, 8);
                    objTK.setDonGia(DonGia);
                }else{
                    objTK.setDonGia(rs.getString("DonGia"));
                }
                objTK.setTrangThai(rs.getString("TrangThai"));
                lstTK.add(objTK);
            }
            return lstTK;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
