/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
public class SachDAO {
    public static List<sach> getAllSachDAO(){
        List<sach> lstSach = new ArrayList<>();
        String sql = "SELECT * FROM sachgiare.sach";
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
    
    public static int updateSachDAO(sach objSach){
        String sql = "UPDATE sach SET TenSach=?,MaChuyenMuc=?,MaNhaCungCap=?,TacGia=?,GiaBan=?,SoLuong=? WHERE MaSach = ?";
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
            sttm.setInt(7, objSach.getMaSach());
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
    
    public static int deleteSachDAO(int MaSach){
        String sql = "DELETE FROM sachgiare.sach WHERE MaSach = ?";
        Connection conn = ConnectDB.getConnect();
        PreparedStatement sttm;
        int result;
        try {
            sttm = conn.prepareStatement(sql);
            sttm.setInt(1, MaSach);
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
    
    public void main(String args[]){
        deleteSachDAO(10);
    }
}
