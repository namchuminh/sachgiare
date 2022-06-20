/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.hoadon;
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
public class QuanLyBanHangDAO {
    public static int insertHoaDonDAO(int MaSach, int MaNhanVien, float DonGia, int SoLuong, String TrangThai){
        String sql = "INSERT INTO `hoadon` (MaSach, MaNhanVien,DonGia, SoLuong, TrangThai) VALUES (?,?,?,?,?);";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setInt(1, MaSach);
            sttm.setInt(2, MaNhanVien);
            sttm.setFloat(3,DonGia);
            sttm.setInt(4,SoLuong);
            sttm.setString(5,TrangThai);
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
    
    public static List<hoadon> getAllHoaDonDAO(){
        List<hoadon> lstHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM sachgiare.hoadon ORDER BY MaHoaDon DESC";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                hoadon objHoaDon = new hoadon();
                objHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                objHoaDon.setMaSach(rs.getInt("MaSach"));
                objHoaDon.setThoiGian(rs.getString("ThoiGian"));
                objHoaDon.setDonGia(rs.getFloat("DonGia"));
                objHoaDon.setSoLuong(rs.getInt("SoLuong"));
                objHoaDon.setTrangThai(rs.getString("TrangThai"));
                lstHoaDon.add(objHoaDon);
            }
            return lstHoaDon;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<sach> getSachByTenDAO(String TenSach){
        List<sach> lstSach = new ArrayList<>();
        String sql = "SELECT * FROM sachgiare.sach WHERE TenSach LIKE '%"+TenSach+"%'";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        try {
            sttm = conn.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                sach objSach = new sach();
                objSach.setMaSach(rs.getInt("MaSach"));
                objSach.setTenSach(rs.getString("TenSach"));
                objSach.setMaChuyenMuc(rs.getInt("MaChuyenMuc"));
                objSach.setMaNhaCungCap(rs.getInt("MaNhaCungCap"));
                objSach.setTacGia(rs.getString("TacGia"));
                objSach.setGiaBan(rs.getFloat("GiaBan"));
                objSach.setSoLuong(rs.getInt("SoLuong"));
                lstSach.add(objSach);
            }
            return lstSach;
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
