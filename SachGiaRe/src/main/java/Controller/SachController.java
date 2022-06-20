/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SachDAO;
import Model.sach;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class SachController {
    public static List<sach> getAllSach(){
        return SachDAO.getAllSachDAO();
    }
    
    public static int updateSach(sach objSach){
        if(objSach.getTenSach().equals("") == true){
            System.out.println("Vui long nhap ten sach!");
            return 0;
        }
        
        if(objSach.getTacGia().equals("") == true){
            System.out.println("Vui long nhap tac gia!");
            return 0;
        }
        
        int result = SachDAO.updateSachDAO(objSach);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static int deleteSach(int MaSach){
        int result = SachDAO.deleteSachDAO(MaSach);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
}
