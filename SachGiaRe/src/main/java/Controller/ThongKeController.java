/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ThongKeDAO;
import Model.thongke;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ThongKeController {
    public static String DoanhThuNgay(String ThoiGian){
        String DoanhThu = ThongKeDAO.DoanhThuNgayDAO(ThoiGian);
        if(DoanhThu.length() == 8){
            DoanhThu = DoanhThu.substring(0, 1) + "." + DoanhThu.substring(1, 8);
            return DoanhThu;
        }
        if(DoanhThu.length() == 9){
            DoanhThu = DoanhThu.substring(0, 2) + "." + DoanhThu.substring(2, 9);
            return DoanhThu;
        }

        return DoanhThu;
    }
    
    public static String DoanhThuHomQua(){
        String DoanhThu = ThongKeDAO.DoanhThuHomQuaDAO();
        if(DoanhThu.length() == 8){
            DoanhThu = DoanhThu.substring(0, 1) + "." + DoanhThu.substring(1, 8);
            return DoanhThu;
        }
        if(DoanhThu.length() == 9){
            DoanhThu = DoanhThu.substring(0, 2) + "." + DoanhThu.substring(2, 9);
            return DoanhThu;
        }
        return DoanhThu;
    }
    
    public static String DoanhThuTuan(){
        String DoanhThu = ThongKeDAO.DoanhThuTuanDAO();
        if(DoanhThu.length() == 8){
            DoanhThu = DoanhThu.substring(0, 1) + "." + DoanhThu.substring(1, 8);
            return DoanhThu;
        }
        if(DoanhThu.length() == 9){
            DoanhThu = DoanhThu.substring(0, 2) + "." + DoanhThu.substring(2, 9);
            return DoanhThu;
        }
        return DoanhThu;
    }
    
    public static String SachBanNgay(String ThoiGian){
        return ThongKeDAO.SachBanNgayDAO(ThoiGian);
    }
    
    public static String SachBanHomQua(){
        return ThongKeDAO.SachBanHomQuaDAO();
    }
    
    public static String SachBanTuan(){
        return ThongKeDAO.SachBanTuanDAO();
    }
    
    public static String DoanhThuThang(){
        String DoanhThu = ThongKeDAO.DoanhThuThangDAO();
        if(DoanhThu.length() == 8){
            DoanhThu = DoanhThu.substring(0, 1) + "." + DoanhThu.substring(1, 8);
            return DoanhThu;
        }
        if(DoanhThu.length() == 9){
            DoanhThu = DoanhThu.substring(0, 2) + "." + DoanhThu.substring(2, 9);
            return DoanhThu;
        }
        return DoanhThu;
    }
    
    public static String SachBanThang(){
        return ThongKeDAO.SachBanThangDAO();
    }
    
    public static List<thongke> getALLThongTinSachBan(){
        return ThongKeDAO.getALLThongTinSachBanDAO();
    }
}
