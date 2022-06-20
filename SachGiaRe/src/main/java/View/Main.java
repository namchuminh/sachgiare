/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Controller.CaNhanController;
import Controller.DangNhapController;
import Controller.QuanLyBanHangController;
import Controller.QuanLyKhoController;
import Controller.QuanLyNhanVienController;
import Controller.SachController;
import Controller.ThongKeController;
import Model.chamcong;
import Model.chuyenmuc;
import Model.hoadon;
import Model.nhacungcap;
import Model.nhanvien;
import Model.sach;
import Model.thongke;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate; 
import static java.time.LocalDate.now;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private int xPress;
    private int yPress;
    private int xDrag;
    private int yDrag;
    private nhanvien objNV = new nhanvien();
    DefaultTableModel tbmodelQLBH = new DefaultTableModel();
    DefaultTableModel tbmodelQLBHHoaDon = new DefaultTableModel();
    DefaultTableModel tbmodelQLKNhaCungCap = new DefaultTableModel();
    DefaultTableModel tbmodelQLKChuyenMuc = new DefaultTableModel();
    DefaultTableModel tbmodelQLChuyenMuc = new DefaultTableModel();
    DefaultTableModel tbmodelQLSach = new DefaultTableModel();
    DefaultTableModel tbmodelChamCong = new DefaultTableModel();
    DefaultTableModel tbmodelQLNVNhanVien = new DefaultTableModel();
    DefaultTableModel tbmodelQLNVChamCong = new DefaultTableModel();
    DefaultTableModel tbmodelThongKeSachBan = new DefaultTableModel();
    public Main(String SoDienThoai) {
        initComponents();
        objNV = DangNhapController.getNhanVien(SoDienThoai);
        getTimeNow();
        startMenu();
        moveWindow();
        isAdmin();
        startQLBH();
        startQLK();
        startQLS();
        startTTCN();
        startQLNV();
        startThongKe();
    }
    public Main(){
    
    }
    
    public void clearThongKe(){
        while(tbmodelThongKeSachBan.getRowCount() > 0){
            tbmodelThongKeSachBan.removeRow(0);
        }
    }
    
    private void startThongKe(){
        //Doanh thu
        String ThoiGian = String.valueOf(now());
        String DoanhThu = ThongKeController.DoanhThuNgay(ThoiGian);
        txtThongKeDoanhThu.setText(DoanhThu+"đ");
        txtThongKeDoanhThuNgay.setText("Doanh thu được tính từ đầu ngày tới giờ!");
        
        // Sach ban
        String SachBan = ThongKeController.SachBanNgay(ThoiGian);
        txtThongKeSachBan.setText(SachBan+" cuốn");
        txtThongKeSachBanNgay.setText("Tổng số cuốn sách được bán từ đầu ngày tới giờ!");
        
        //Doanh thu thang
        String DoanhThuThang = ThongKeController.DoanhThuThang();
        txtThongKeDoanhThuThang.setText(DoanhThuThang+"đ");
        
        //Sach ban thang
        String SachBanThang = ThongKeController.SachBanThang();
        txtThongKeSachBanThang.setText(SachBanThang+" cuốn");
        
        //Table sach thong ke
        clearThongKe();
        List<thongke> lstTK = ThongKeController.getALLThongTinSachBan();
        String[] nameColTK = {"Mã Sách", "Tên Sách","Thời Gian Bán", "Nhân Viên", "Số Lượng", "Thành Tiền", "Trạng Thái"};
        tbmodelThongKeSachBan.setColumnIdentifiers(nameColTK);
        for(int i = 0; i < lstTK.size(); i++){
            String[] row = {
                String.valueOf(lstTK.get(i).getMaSach()),
                lstTK.get(i).getTenSach(),
                lstTK.get(i).getThoiGian(),
                lstTK.get(i).getHoTen(),
                String.valueOf(lstTK.get(i).getSoLuong()) + " cuốn",
                lstTK.get(i).getDonGia() + "đ",
                lstTK.get(i).getTrangThai(),
            };
            tbmodelThongKeSachBan.addRow(row);
        }
        tbThongKeSachBan.setModel(tbmodelThongKeSachBan);
    }
    public void clearNhanVien(){
        while(tbmodelQLNVNhanVien.getRowCount() > 0){
            tbmodelQLNVNhanVien.removeRow(0);
        }
        
        while(tbmodelQLNVChamCong.getRowCount() > 0){
            tbmodelQLNVChamCong.removeRow(0);
        }
    }
    private void startQLNV(){
        btnQLNVSua.setVisible(false);
        btnQLNVXoa.setVisible(false);
        btnQLNVLamMoi.setVisible(false);
        btnQLNVLamMoi.setEnabled(false);
        btnQLNVThem.setVisible(true);
        btnQLNVThem.setEnabled(true);
        
        //Hien thi nhan vien
        clearNhanVien();
        List<nhanvien> lstNV = QuanLyNhanVienController.getAllNhanVien();
        String[] nameColQLNV = {"Mã Nhân Viên", "Họ Tên","Giới Tính", "Ngày Sinh", "Quê Quán", "Số Điện Thoại", "Mật Khẩu", "Admin"};
        tbmodelQLNVNhanVien.setColumnIdentifiers(nameColQLNV);
        for(int i = 0; i < lstNV.size(); i++){
            String[] row = {
                String.valueOf(lstNV.get(i).getMaNhanVien()),
                lstNV.get(i).getHoTen(),
                lstNV.get(i).getGioiTinh(),
                lstNV.get(i).getNgaySinh(),
                lstNV.get(i).getQueQuan(),
                lstNV.get(i).getSoDienThoai(),
                lstNV.get(i).getMatKhau(),
                String.valueOf(lstNV.get(i).getAdmin()),
            };
            tbmodelQLNVNhanVien.addRow(row);
        }
        tbQLNVgetALL.setModel(tbmodelQLNVNhanVien);
        
        // Hien thi cham cong
        List<chamcong> lstCC = QuanLyNhanVienController.getChamCong();
        String[] nameColCC = {"Mã Chấm Công","Mã Nhân Viên", "Ngày Chấm Công","Trạng Thái"};
        tbmodelQLNVChamCong.setColumnIdentifiers(nameColCC);
        for(int i = 0; i < lstCC.size(); i++){
            String[] row = {
                String.valueOf(lstCC.get(i).getMaChamCong()),
                String.valueOf(lstCC.get(i).getMaNhanVien()),
                lstCC.get(i).getNgayChamCong(),
                lstCC.get(i).getTrangThai(),
            };
            tbmodelQLNVChamCong.addRow(row);
        }
        tbQLNVChamCong.setModel(tbmodelQLNVChamCong);
        
    }
    
    void clearChamCong(){
        while(tbmodelChamCong.getRowCount() > 0){
            tbmodelChamCong.removeRow(0);
        }
    }
    
    private void startTTCN(){
        //thong tin nhan vien
        int MaNhanVien = objNV.getMaNhanVien();
        nhanvien objNVCaNhan = CaNhanController.getThongTinCaNhan(MaNhanVien);
        txtTTCNHoTen.setText(objNVCaNhan.getHoTen());
        txtTTCNGioiTinh.setText(objNVCaNhan.getGioiTinh());
        txtTTCNNgaySinh.setText(objNVCaNhan.getNgaySinh());
        txtTTCNQueQuan.setText(objNVCaNhan.getQueQuan());
        txtTTCNSoDienThoai.setText(objNVCaNhan.getSoDienThoai());
        txtTTCNMatKhau.setText(objNVCaNhan.getMatKhau());
        
        //cham cong
        clearChamCong();
        List<chamcong> lstCC = CaNhanController.getChamCong(MaNhanVien);
        String[] nameColCC = {"Mã Chấm Công", "Ngày Chấm Công","Trạng Thái"};
        tbmodelChamCong.setColumnIdentifiers(nameColCC);
        for(int i = 0; i < lstCC.size(); i++){
            String[] row = {
                String.valueOf(lstCC.get(i).getMaChamCong()),
                lstCC.get(i).getNgayChamCong(),
                lstCC.get(i).getTrangThai(),
            };
            tbmodelChamCong.addRow(row);
        }
        tbTTCNChamCong1.setModel(tbmodelChamCong);
    }
    private void clearQLS(){
        while(tbmodelQLSach.getRowCount() > 0){
            tbmodelQLSach.removeRow(0);
        }
        
    }
    private void startQLS(){
        clearQLS();
        //Table thong tin sach
        String[] nameCol = {"Mã Sách", "Tên Sách", "Mã Chuyên Mục","Mã Nhà Cung Cấp", "Tác Giả", "Giá Bán", "Số Lượng"};
        tbmodelQLSach.setColumnIdentifiers(nameCol);
        List<sach> lstSach = SachController.getAllSach();
        for(int i = 0; i < lstSach.size(); i++){
            String[] row = {
                String.valueOf(lstSach.get(i).getMaSach()),
                lstSach.get(i).getTenSach(),
                String.valueOf(lstSach.get(i).getMaChuyenMuc()),
                String.valueOf(lstSach.get(i).getMaNhaCungCap()),
                lstSach.get(i).getTacGia(),
                String.valueOf(lstSach.get(i).getGiaBan()),
                String.valueOf(lstSach.get(i).getSoLuong()),
            };
            tbmodelQLSach.addRow(row);
        }
        tbQLSGetAll.setModel(tbmodelQLSach);
    }
    
    private void clearDataQLK(){
        while(tbmodelQLKNhaCungCap.getRowCount() > 0){
            tbmodelQLKNhaCungCap.removeRow(0);
        }
        
        while(tbmodelQLKChuyenMuc.getRowCount() > 0){
            tbmodelQLKChuyenMuc.removeRow(0);
        }
    }
    
    private void startQLK(){
        clearDataQLK();
        //Table nha cung cap
        String[] nameColNCC = {"Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Trạng Thái"};
        tbmodelQLKNhaCungCap.setColumnIdentifiers(nameColNCC);
        List<nhacungcap> lstNhaCungCap = QuanLyKhoController.getAllNhaCungCap();
        for(int i = 0; i < lstNhaCungCap.size(); i++){
            String[] row = {
                String.valueOf(lstNhaCungCap.get(i).getMaNhaCungCap()),
                lstNhaCungCap.get(i).getTenNhaCungCap(),
                lstNhaCungCap.get(i).getTrangThai(),
            };
            tbmodelQLKNhaCungCap.addRow(row);
        }
        tbQLKNhaCungCap1.setModel(tbmodelQLKNhaCungCap);
        
        //Table chuyen muc
        String[] nameColCM = {"Mã Chuyên Mục", "Tên Chuyên Mục"};
        tbmodelQLKChuyenMuc.setColumnIdentifiers(nameColCM);
        List<chuyenmuc> lstChuyenMuc = QuanLyKhoController.getAllChuyenMuc();
        for(int i = 0; i < lstChuyenMuc.size(); i++){
            String[] row = {
                String.valueOf(lstChuyenMuc.get(i).getMaChuyenMuc()),
                lstChuyenMuc.get(i).getTenChuyenMuc(),
            };
            tbmodelQLKChuyenMuc.addRow(row);
        }
        tbQLKChuyenMuc.setModel(tbmodelQLKChuyenMuc);
    }
    
    private void clearData(){
        while(tbmodelQLBH.getRowCount() > 0){
            tbmodelQLBH.removeRow(0);
        }
        
        while(tbmodelQLBHHoaDon.getRowCount() > 0){
            tbmodelQLBHHoaDon.removeRow(0);
        }
    }
    private void startQLBH(){
        clearData();
        //Table thong tin sach
        String[] nameCol = {"Mã Sách", "Tên Sách", "Mã Chuyên Mục","Mã Nhà Cung Cấp", "Tác Giả", "Giá Bán", "Số Lượng"};
        tbmodelQLBH.setColumnIdentifiers(nameCol);
        List<sach> lstSach = SachController.getAllSach();
        for(int i = 0; i < lstSach.size(); i++){
            String[] row = {
                String.valueOf(lstSach.get(i).getMaSach()),
                lstSach.get(i).getTenSach(),
                String.valueOf(lstSach.get(i).getMaChuyenMuc()),
                String.valueOf(lstSach.get(i).getMaNhaCungCap()),
                lstSach.get(i).getTacGia(),
                String.valueOf(lstSach.get(i).getGiaBan()),
                String.valueOf(lstSach.get(i).getSoLuong()),
            };
            tbmodelQLBH.addRow(row);
        }
        tbQLBHgetALLSach.setModel(tbmodelQLBH);
        
        //Table thanh toan - QLBH
        String[] nameColThanhToan = {"Mã Hóa Đơn", "Mã Sách","Thời Gian","Đơn Giá", "Số Lượng", "Trạng Thái"};
        tbmodelQLBHHoaDon.setColumnIdentifiers(nameColThanhToan);
        List<hoadon> lstHoaDon = QuanLyBanHangController.getAllHoaDon();
        for(int i = 0; i < lstHoaDon.size(); i++){
            String[] row = {
                String.valueOf(lstHoaDon.get(i).getMaHoaDon()),
                String.valueOf(lstHoaDon.get(i).getMaSach()),
                lstHoaDon.get(i).getThoiGian(),
                String.valueOf(lstHoaDon.get(i).getDonGia()),
                String.valueOf(lstHoaDon.get(i).getSoLuong()),
                lstHoaDon.get(i).getTrangThai(),
            };
            tbmodelQLBHHoaDon.addRow(row);
        }
        tbQLBHHoaDon.setModel(tbmodelQLBHHoaDon);
    }
    private void isAdmin(){
        txtTrangChuTenNV.setText("Xin chào, " + objNV.getHoTen() + "!");
        if(objNV.getAdmin() != 1){
            jpnMenu_QLNV.setVisible(false);
            jpnMenu_ThongKe.setVisible(false);
        }
    }
    private void moveWindow(){
        this.addMouseMotionListener(new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            xDrag = e.getX();
            yDrag = e.getY();

            JFrame sFrame = (JFrame) e.getSource();
            sFrame.setLocation(sFrame.getLocation().x+xDrag-xPress, 
            sFrame.getLocation().y+yDrag-yPress);
         }

         @Override
         public void mouseMoved(MouseEvent e) {
            xPress = e.getX();
            yPress = e.getY();
          }
        });
    }
    
    private void startMenu(){
        menu_selection.setSelectedIndex(0);
        jpnMenu_TrangChu.setBackground(Color.white);
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }
    private void getTimeNow(){
        LocalDate myObj = LocalDate.now();  // Create a date object
        int day = myObj.getDayOfMonth();
        int month = myObj.getMonthValue();
        int year = myObj.getYear();
        String time = "Hôm nay, ngày " + day + ", tháng " + month + ", năm " + year;
        jLabel26.setText(time);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jpnMenu_QLNV = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jpnMenu_QLBH = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jpnMenu_QLK = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jpnMenu_QLS = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jpnMenu_ThongKe = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jpnMenu_TrangChu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jpnMenu_TTCN = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtTrangChuTenNV = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel68 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        menu_selection = new javax.swing.JTabbedPane();
        jpnTrangChu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtHello = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jpnlQLNV = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        txtQLNVHoTen = new javax.swing.JTextField();
        txtQLNVMaNhanVien = new javax.swing.JTextField();
        txtQLNVSoDienThoai = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtQLNVMatKhau = new javax.swing.JTextField();
        txtQLNVQueQuan = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        txtQLNVNgaySinh = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txtQLNVGioiTinh = new javax.swing.JTextField();
        txtQLNVAdmin = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnQLNVThem = new javax.swing.JButton();
        btnQLNVSua = new javax.swing.JButton();
        btnQLNVXoa = new javax.swing.JButton();
        btnQLNVLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQLNVChamCong = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbQLNVgetALL = new javax.swing.JTable();
        jpnlQLBH = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbQLBHgetALLSach = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtQLBHTenSach = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtQLBHMaSach = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtQLBHDonGia = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbQLBHHoaDon = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        txtQLBHSoLuong = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        cbQLBHTrangThai = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        jpnlQLK = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel16 = new javax.swing.JPanel();
        txtQLKTenSach = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtQLKMaChuyenMuc = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtQLKTacGia = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtQLKMaNhaCungCap = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtQLKSoLuong = new javax.swing.JTextField();
        txtQLKGiaBan = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        txtQLKTenNhaCungCap = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        cbQLKTrangThai = new javax.swing.JComboBox<>();
        txtQLKTenNhaCungCap1 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbQLKNhaCungCap1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txtQLKTenChuyenMuc = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbQLKChuyenMuc = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jpnlQLS = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtQLSSoLuong = new javax.swing.JTextField();
        txtQLSTenSach = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        btnQLSXoaSach = new javax.swing.JButton();
        txtQLSGiaBan = new javax.swing.JTextField();
        txtQLSTacGia = new javax.swing.JTextField();
        txtQLSMaChuyenMuc = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        btnQLSSuaSach = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        txtQLSMaNhaCungCap = new javax.swing.JTextField();
        txtQLSMaSach = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbQLSGetAll = new javax.swing.JTable();
        jpnlThongKe = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        txtThongKeSachBanNgay = new javax.swing.JLabel();
        txtThongKeSachBan = new javax.swing.JLabel();
        cbSachBanNgay = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        cbDoanhThuNgay = new javax.swing.JComboBox<>();
        jPanel29 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        txtThongKeDoanhThuNgay = new javax.swing.JLabel();
        txtThongKeDoanhThu = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        txtThongKeDoanhThuThang = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        txtThongKeSachBanThang = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbThongKeSachBan = new javax.swing.JTable();
        jpnlTTCN = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtTTCNMatKhau = new javax.swing.JTextField();
        txtTTCNHoTen = new javax.swing.JTextField();
        txtTTCNGioiTinh = new javax.swing.JTextField();
        txtTTCNNgaySinh = new javax.swing.JTextField();
        txtTTCNQueQuan = new javax.swing.JTextField();
        txtTTCNSoDienThoai = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbTTCNChamCong1 = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 196, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnMenu_QLNV.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_QLNV.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_QLNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(242, 240, 235));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(74, 67, 72));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quản Lý Nhân Viên");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jpnMenu_QLNV.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 140, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/management.png"))); // NOI18N
        jpnMenu_QLNV.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_QLNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 220, 50));

        jpnMenu_QLBH.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_QLBH.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_QLBH.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(74, 67, 72));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản Lý Bán Hàng");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jpnMenu_QLBH.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 140, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sell.png"))); // NOI18N
        jpnMenu_QLBH.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_QLBH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 50));

        jpnMenu_QLK.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_QLK.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_QLK.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(74, 67, 72));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("   Quản Lý Kho");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jpnMenu_QLK.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 150, 50));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/box.png"))); // NOI18N
        jpnMenu_QLK.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_QLK, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 50));

        jpnMenu_QLS.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_QLS.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_QLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMenu_QLSMouseClicked(evt);
            }
        });
        jpnMenu_QLS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(74, 67, 72));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("   Quản Lý Sách");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jpnMenu_QLS.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book.png"))); // NOI18N
        jpnMenu_QLS.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_QLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, 50));

        jpnMenu_ThongKe.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_ThongKe.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_ThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(74, 67, 72));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("   Thống Kê");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jpnMenu_ThongKe.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 140, 50));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/statistical.png"))); // NOI18N
        jpnMenu_ThongKe.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_ThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 220, 50));

        jpnMenu_TrangChu.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_TrangChu.setForeground(new java.awt.Color(74, 67, 72));
        jpnMenu_TrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Home.png"))); // NOI18N
        jpnMenu_TrangChu.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 30, 50));

        jLabel19.setBackground(new java.awt.Color(255, 247, 246));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(74, 67, 72));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("   Trang Chủ");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jpnMenu_TrangChu.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 140, 50));

        jPanel2.add(jpnMenu_TrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 220, 50));

        jPanel11.setBackground(new java.awt.Color(153, 196, 200));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jPanel11.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 70));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SGR3.png"))); // NOI18N
        jPanel11.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 70));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 70));

        jLabel7.setBackground(new java.awt.Color(102, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(74, 67, 72));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Đăng Xuất");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 70, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\SachGiaRe\\SachGiaRe\\src\\main\\resources\\img\\logout.png")); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel17)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 220, 50));

        jpnMenu_TTCN.setBackground(new java.awt.Color(153, 196, 200));
        jpnMenu_TTCN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(74, 67, 72));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Thông Tin Cá Nhân");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jpnMenu_TTCN.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 140, 50));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/172628_user_male_icon.png"))); // NOI18N
        jpnMenu_TTCN.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 50));

        jPanel2.add(jpnMenu_TTCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 220, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        jPanel4.setBackground(new java.awt.Color(89, 156, 173));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(89, 156, 173));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 30, 50));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x.png"))); // NOI18N
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        jPanel15.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, -10, -1, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus2.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel15.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, -10, -1, 50));

        txtTimKiem.setBackground(new java.awt.Color(89, 156, 173));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setText("Nhập tên sách cần tìm...");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 156, 173), 3));
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        jPanel15.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 310, 40));

        txtTrangChuTenNV.setBackground(new java.awt.Color(255, 255, 255));
        txtTrangChuTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangChuTenNV.setForeground(new java.awt.Color(255, 255, 255));
        txtTrangChuTenNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTrangChuTenNV.setText("Xin chào, Chu Minh Nam!");
        txtTrangChuTenNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(txtTrangChuTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, 60));
        jPanel15.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 310, 10));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users.png"))); // NOI18N
        jLabel68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 60));

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 60));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 310, -1));

        menu_selection.setBackground(new java.awt.Color(255, 255, 255));
        menu_selection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        menu_selection.setForeground(new java.awt.Color(255, 255, 255));
        menu_selection.setEnabled(false);
        menu_selection.setFocusable(false);
        menu_selection.setRequestFocusEnabled(false);
        menu_selection.setVerifyInputWhenFocusTarget(false);

        jpnTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        jpnTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Hôm nay, ngày 07, tháng 02, nam 2022");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        txtHello.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtHello.setForeground(new java.awt.Color(255, 255, 255));
        txtHello.setText("Chúc bạn ngày mới tốt lành_______________");
        jPanel3.add(txtHello, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 700, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Form-Background-2203989.jpg"))); // NOI18N
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 190));

        jpnTrangChu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 190));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(89, 156, 173));
        jLabel28.setText("Thông báo");
        jpnTrangChu.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, 30));
        jpnTrangChu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 280, 10));
        jpnTrangChu.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 310, 10));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Capture.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
        );

        jpnTrangChu.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 200, 110));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Capture1.PNG"))); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnTrangChu.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 200, 110));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Capture2.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnTrangChu.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 200, 110));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CAPTURE4.png"))); // NOI18N
        jpnTrangChu.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, -1, 110));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Capture5.png"))); // NOI18N
        jpnTrangChu.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 110));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CAPTURE3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnTrangChu.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 200, 110));

        menu_selection.addTab("", jpnTrangChu);

        jpnlQLNV.setBackground(new java.awt.Color(255, 255, 255));
        jpnlQLNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(66, 149, 166));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(66, 149, 166));
        jLabel29.setText("Thông Tin Nhân Viên");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));
        jPanel10.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 580, 10));

        jPanel7.setBackground(new java.awt.Color(224, 236, 237));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQLNVHoTen.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVHoTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        txtQLNVHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLNVHoTenActionPerformed(evt);
            }
        });
        jPanel7.add(txtQLNVHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 290, 20));

        txtQLNVMaNhanVien.setBackground(new java.awt.Color(224, 236, 237));
        txtQLNVMaNhanVien.setForeground(new java.awt.Color(224, 236, 237));
        txtQLNVMaNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 236, 237), 10));
        txtQLNVMaNhanVien.setCaretColor(new java.awt.Color(224, 236, 237));
        jPanel7.add(txtQLNVMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 20));

        txtQLNVSoDienThoai.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 130, 20));

        jLabel31.setBackground(new java.awt.Color(51, 51, 51));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Họ tên:");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 50, 20));

        jLabel32.setBackground(new java.awt.Color(51, 51, 51));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Giới Tính:");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 130, 20));

        jLabel40.setBackground(new java.awt.Color(51, 51, 51));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Số điện thoại:");
        jPanel7.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 90, 20));

        jLabel42.setBackground(new java.awt.Color(51, 51, 51));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Ngày sinh:");
        jPanel7.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 90, 20));

        jLabel69.setBackground(new java.awt.Color(51, 51, 51));
        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setText("Admin:");
        jPanel7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 90, 20));

        txtQLNVMatKhau.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 150, 20));

        txtQLNVQueQuan.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVQueQuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVQueQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, 20));

        jLabel79.setBackground(new java.awt.Color(51, 51, 51));
        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setText("Quê quán:");
        jPanel7.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 90, 20));

        txtQLNVNgaySinh.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVNgaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 150, 20));

        jLabel80.setBackground(new java.awt.Color(51, 51, 51));
        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText("Mật khẩu:");
        jPanel7.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 150, 20));

        txtQLNVGioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVGioiTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 110, 20));

        txtQLNVAdmin.setForeground(new java.awt.Color(102, 102, 102));
        txtQLNVAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.add(txtQLNVAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 130, 20));

        jPanel10.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 400, 190));

        jPanel12.setBackground(new java.awt.Color(224, 236, 237));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(66, 149, 166));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CHẤM CÔNG");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel12.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, 30));

        btnQLNVThem.setBackground(new java.awt.Color(66, 149, 166));
        btnQLNVThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQLNVThem.setForeground(new java.awt.Color(255, 255, 255));
        btnQLNVThem.setText("THÊM ");
        btnQLNVThem.setBorder(null);
        btnQLNVThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLNVThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLNVThemMouseClicked(evt);
            }
        });
        jPanel12.add(btnQLNVThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 110, 30));

        btnQLNVSua.setBackground(new java.awt.Color(66, 149, 166));
        btnQLNVSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQLNVSua.setForeground(new java.awt.Color(255, 255, 255));
        btnQLNVSua.setText("SỬA ");
        btnQLNVSua.setBorder(null);
        btnQLNVSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLNVSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLNVSuaMouseClicked(evt);
            }
        });
        jPanel12.add(btnQLNVSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, 30));

        btnQLNVXoa.setBackground(new java.awt.Color(66, 149, 166));
        btnQLNVXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQLNVXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnQLNVXoa.setText("XÓA ");
        btnQLNVXoa.setBorder(null);
        btnQLNVXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLNVXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLNVXoaMouseClicked(evt);
            }
        });
        jPanel12.add(btnQLNVXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 110, 30));

        btnQLNVLamMoi.setBackground(new java.awt.Color(66, 149, 166));
        btnQLNVLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQLNVLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnQLNVLamMoi.setText("LÀM MỚI");
        btnQLNVLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLNVLamMoiMouseClicked(evt);
            }
        });
        jPanel12.add(btnQLNVLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 110, 30));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 330, 190));

        tbQLNVChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chấm Công", "Mã Nhân Viên", "Ngày Chấm Công", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLNVChamCong.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbQLNVChamCong.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLNVChamCong.setName(""); // NOI18N
        tbQLNVChamCong.setRowHeight(25);
        tbQLNVChamCong.setShowGrid(true);
        jScrollPane1.setViewportView(tbQLNVChamCong);
        if (tbQLNVChamCong.getColumnModel().getColumnCount() > 0) {
            tbQLNVChamCong.getColumnModel().getColumn(0).setResizable(false);
            tbQLNVChamCong.getColumnModel().getColumn(1).setResizable(false);
            tbQLNVChamCong.getColumnModel().getColumn(2).setResizable(false);
            tbQLNVChamCong.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 740, 110));

        jLabel43.setBackground(new java.awt.Color(66, 149, 166));
        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(66, 149, 166));
        jLabel43.setText("Thông Tin Nhân Viên");
        jPanel10.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 200, -1));
        jPanel10.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 580, 10));

        jLabel66.setBackground(new java.awt.Color(66, 149, 166));
        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(66, 149, 166));
        jLabel66.setText("Thông Tin Chấm Công");
        jPanel10.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 200, -1));
        jPanel10.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 570, 10));

        tbQLNVgetALL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Họ Tên", "Giới Tính", "Ngày Sinh", "Quê Quán", "Số điện thọai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLNVgetALL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbQLNVgetALL.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLNVgetALL.setName(""); // NOI18N
        tbQLNVgetALL.setRowHeight(25);
        tbQLNVgetALL.setShowGrid(true);
        tbQLNVgetALL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNVgetALLMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbQLNVgetALL);
        if (tbQLNVgetALL.getColumnModel().getColumnCount() > 0) {
            tbQLNVgetALL.getColumnModel().getColumn(0).setResizable(false);
            tbQLNVgetALL.getColumnModel().getColumn(1).setResizable(false);
            tbQLNVgetALL.getColumnModel().getColumn(2).setResizable(false);
            tbQLNVgetALL.getColumnModel().getColumn(3).setResizable(false);
            tbQLNVgetALL.getColumnModel().getColumn(4).setResizable(false);
            tbQLNVgetALL.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel10.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 740, 140));

        jpnlQLNV.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 550));

        menu_selection.addTab("", jpnlQLNV);

        jpnlQLBH.setBackground(new java.awt.Color(255, 255, 255));
        jpnlQLBH.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setBackground(new java.awt.Color(66, 149, 166));
        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(66, 149, 166));
        jLabel44.setText("Xử Lý Đơn Hàng");
        jpnlQLBH.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 30));
        jpnlQLBH.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 610, 10));

        tbQLBHgetALLSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Mã Chuyên Mục", "Mã Nhà Cung Cấp", "Tác Giả", "Giá Bán", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbQLBHgetALLSach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbQLBHgetALLSach.setDragEnabled(true);
        tbQLBHgetALLSach.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLBHgetALLSach.setRowHeight(25);
        tbQLBHgetALLSach.setShowGrid(true);
        tbQLBHgetALLSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLBHgetALLSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbQLBHgetALLSach);
        if (tbQLBHgetALLSach.getColumnModel().getColumnCount() > 0) {
            tbQLBHgetALLSach.getColumnModel().getColumn(0).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(1).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(2).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(3).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(4).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(5).setResizable(false);
            tbQLBHgetALLSach.getColumnModel().getColumn(6).setResizable(false);
        }

        jpnlQLBH.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 720, 160));

        jPanel13.setBackground(new java.awt.Color(224, 236, 237));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Nhập tên sách:");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 480, 30));

        txtQLBHTenSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLBHTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLBHTenSachActionPerformed(evt);
            }
        });
        jPanel13.add(txtQLBHTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 490, 30));

        jButton6.setBackground(new java.awt.Color(89, 156, 173));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Kiểm Tra");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jPanel13.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 210, 30));

        jpnlQLBH.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 750, 250));

        jLabel46.setBackground(new java.awt.Color(66, 149, 166));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(66, 149, 166));
        jLabel46.setText("Hóa Đơn Thanh Toán");
        jpnlQLBH.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 240, -1));
        jpnlQLBH.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 580, 10));

        jPanel14.setBackground(new java.awt.Color(224, 236, 237));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(89, 156, 173));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thanh Toán");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel14.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 210, 30));

        txtQLBHMaSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLBHMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLBHMaSachActionPerformed(evt);
            }
        });
        jPanel14.add(txtQLBHMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 60, 30));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Ðơn giá:");
        jPanel14.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 120, 30));

        txtQLBHDonGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLBHDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLBHDonGiaActionPerformed(evt);
            }
        });
        jPanel14.add(txtQLBHDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 100, 30));

        tbQLBHHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã Sách", "Thời Gian", "Đơn Giá", "Số Lượng", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLBHHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbQLBHHoaDon.setDragEnabled(true);
        tbQLBHHoaDon.setFillsViewportHeight(true);
        tbQLBHHoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLBHHoaDon.setRowHeight(25);
        jScrollPane4.setViewportView(tbQLBHHoaDon);
        if (tbQLBHHoaDon.getColumnModel().getColumnCount() > 0) {
            tbQLBHHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tbQLBHHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tbQLBHHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tbQLBHHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tbQLBHHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tbQLBHHoaDon.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel14.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 720, 130));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setText("Mã sách:");
        jPanel14.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 170, 30));

        txtQLBHSoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLBHSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLBHSoLuongActionPerformed(evt);
            }
        });
        jPanel14.add(txtQLBHSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 100, 30));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("Trạng thái:");
        jPanel14.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 100, 30));

        cbQLBHTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán" }));
        jPanel14.add(cbQLBHTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 130, 30));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setText("Nhập số lượng:");
        jPanel14.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 100, 30));

        jpnlQLBH.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 750, 210));

        menu_selection.addTab("tab4", jpnlQLBH);

        jpnlQLK.setBackground(new java.awt.Color(255, 255, 255));
        jpnlQLK.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setBackground(new java.awt.Color(89, 156, 173));
        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(89, 156, 173));
        jLabel49.setText("Nhập Sách Mới");
        jpnlQLK.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));
        jpnlQLK.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 630, 10));

        jPanel16.setBackground(new java.awt.Color(224, 236, 237));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQLKTenSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKTenSachActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 260, 20));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("Nhập tên sách:");
        jPanel16.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 130, 20));

        jButton7.setBackground(new java.awt.Color(89, 156, 173));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Thêm Sách");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel16.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 580, 20));

        txtQLKMaChuyenMuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKMaChuyenMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKMaChuyenMucActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKMaChuyenMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 260, 20));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Nhập mã chuyên mục:");
        jPanel16.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 190, 20));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Nhập tác giả:");
        jPanel16.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, -1));

        txtQLKTacGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKTacGiaActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 260, 20));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText("Nhập mã nhà cung cấp:");
        jPanel16.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, 20));

        txtQLKMaNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKMaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKMaNhaCungCapActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 260, 20));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("Nhập số lượng:");
        jPanel16.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 130, 20));

        txtQLKSoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKSoLuongActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 260, -1));

        txtQLKGiaBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKGiaBanActionPerformed(evt);
            }
        });
        jPanel16.add(txtQLKGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 260, 20));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setText("Nhập giá bán:");
        jPanel16.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 260, 20));

        jpnlQLK.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 740, 160));

        jPanel17.setBackground(new java.awt.Color(224, 236, 237));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQLKTenNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKTenNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKTenNhaCungCapActionPerformed(evt);
            }
        });
        jPanel17.add(txtQLKTenNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 20));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("Trạng Thái:");
        jPanel17.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 130, 20));

        cbQLKTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang cung cấp", "Chưa cung cấp" }));
        jPanel17.add(cbQLKTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 130, 20));

        txtQLKTenNhaCungCap1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKTenNhaCungCap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKTenNhaCungCap1ActionPerformed(evt);
            }
        });
        jPanel17.add(txtQLKTenNhaCungCap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 20));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel76.setText("Nhập tên nhà cung cấp:");
        jPanel17.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, 20));

        tbQLKNhaCungCap1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLKNhaCungCap1.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLKNhaCungCap1.setRowHeight(25);
        tbQLKNhaCungCap1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLKNhaCungCap1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbQLKNhaCungCap1);
        if (tbQLKNhaCungCap1.getColumnModel().getColumnCount() > 0) {
            tbQLKNhaCungCap1.getColumnModel().getColumn(0).setResizable(false);
            tbQLKNhaCungCap1.getColumnModel().getColumn(1).setResizable(false);
            tbQLKNhaCungCap1.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel17.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 710, 100));

        jButton8.setBackground(new java.awt.Color(89, 156, 173));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Thêm Nhà Cung Cấp");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jPanel17.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 250, 20));

        jpnlQLK.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 740, 160));

        jLabel58.setBackground(new java.awt.Color(89, 156, 173));
        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(89, 156, 173));
        jLabel58.setText("Nhà Cung Cấp");
        jpnlQLK.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 150, -1));
        jpnlQLK.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 640, 10));
        jpnlQLK.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 650, 10));

        jLabel50.setBackground(new java.awt.Color(89, 156, 173));
        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(89, 156, 173));
        jLabel50.setText("Chuyên Mục");
        jpnlQLK.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 20));

        jPanel21.setBackground(new java.awt.Color(224, 236, 237));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQLKTenChuyenMuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLKTenChuyenMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLKTenChuyenMucActionPerformed(evt);
            }
        });
        jPanel21.add(txtQLKTenChuyenMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 20));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Nhập tên chuyên mục:");
        jPanel21.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, -1));

        tbQLKChuyenMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Chuyên Mục", "Tên Chuyên Mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLKChuyenMuc.setGridColor(new java.awt.Color(255, 255, 255));
        tbQLKChuyenMuc.setRowHeight(25);
        tbQLKChuyenMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLKChuyenMucMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbQLKChuyenMuc);
        if (tbQLKChuyenMuc.getColumnModel().getColumnCount() > 0) {
            tbQLKChuyenMuc.getColumnModel().getColumn(0).setResizable(false);
            tbQLKChuyenMuc.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel21.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 710, 90));

        jButton11.setBackground(new java.awt.Color(89, 156, 173));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Thêm Chuyên Mục");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jPanel21.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 250, -1));

        jpnlQLK.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 740, 150));

        menu_selection.addTab("tab5", jpnlQLK);

        jpnlQLS.setBackground(new java.awt.Color(255, 255, 255));
        jpnlQLS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(224, 236, 237));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Tên Sách:");
        jPanel18.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 30));

        txtQLSSoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSSoLuongActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 260, 30));

        txtQLSTenSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSTenSachActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 260, 30));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setText("Mã Chuyên Mục:");
        jPanel18.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 130, 30));

        btnQLSXoaSach.setBackground(new java.awt.Color(89, 156, 173));
        btnQLSXoaSach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLSXoaSach.setForeground(new java.awt.Color(255, 255, 255));
        btnQLSXoaSach.setText("Xóa Sách");
        btnQLSXoaSach.setBorder(null);
        btnQLSXoaSach.setBorderPainted(false);
        btnQLSXoaSach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLSXoaSach.setEnabled(false);
        btnQLSXoaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLSXoaSachMouseClicked(evt);
            }
        });
        jPanel18.add(btnQLSXoaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 260, 30));

        txtQLSGiaBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSGiaBanActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 260, 30));

        txtQLSTacGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSTacGiaActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 260, 30));

        txtQLSMaChuyenMuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSMaChuyenMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSMaChuyenMucActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSMaChuyenMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 260, 30));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("Tác Giả:");
        jPanel18.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 130, 30));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setText("Giá Bán:");
        jPanel18.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 70, 30));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setText("Số Lượng:");
        jPanel18.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 130, 30));

        btnQLSSuaSach.setBackground(new java.awt.Color(89, 156, 173));
        btnQLSSuaSach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLSSuaSach.setForeground(new java.awt.Color(255, 255, 255));
        btnQLSSuaSach.setText("Sửa Sách");
        btnQLSSuaSach.setBorder(null);
        btnQLSSuaSach.setBorderPainted(false);
        btnQLSSuaSach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLSSuaSach.setEnabled(false);
        btnQLSSuaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLSSuaSachMouseClicked(evt);
            }
        });
        jPanel18.add(btnQLSSuaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 260, 30));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setText("Mã Nhà Cung Cấp:");
        jPanel18.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 130, 30));

        txtQLSMaNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        txtQLSMaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQLSMaNhaCungCapActionPerformed(evt);
            }
        });
        jPanel18.add(txtQLSMaNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 260, 30));

        txtQLSMaSach.setBackground(new java.awt.Color(224, 236, 237));
        txtQLSMaSach.setForeground(new java.awt.Color(224, 236, 237));
        txtQLSMaSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 236, 237), 10));
        txtQLSMaSach.setEnabled(false);
        jPanel18.add(txtQLSMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 10, 30));

        jpnlQLS.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 750, 250));

        jLabel59.setBackground(new java.awt.Color(66, 149, 166));
        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(66, 149, 166));
        jLabel59.setText("Thông Tin Sách");
        jpnlQLS.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 150, -1));
        jpnlQLS.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 620, 10));

        jLabel61.setBackground(new java.awt.Color(66, 149, 166));
        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(66, 149, 166));
        jLabel61.setText("Cập Nhật Sách");
        jpnlQLS.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 150, 20));
        jpnlQLS.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 630, 10));

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbQLSGetAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Mã Chuyên Mục", "Mã Nhà Cung Cấp", "Tác Giả", "Giá Bán", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLSGetAll.setRowHeight(25);
        tbQLSGetAll.setShowGrid(true);
        tbQLSGetAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLSGetAllMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbQLSGetAll);
        if (tbQLSGetAll.getColumnModel().getColumnCount() > 0) {
            tbQLSGetAll.getColumnModel().getColumn(0).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(1).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(2).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(3).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(4).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(5).setResizable(false);
            tbQLSGetAll.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel19.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 210));

        jpnlQLS.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 750, 210));

        menu_selection.addTab("tab6", jpnlQLS);

        jpnlThongKe.setBackground(new java.awt.Color(255, 255, 255));
        jpnlThongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel30.setBackground(new java.awt.Color(153, 196, 200));
        jPanel30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        jPanel32.setBackground(new java.awt.Color(204, 204, 204));
        jPanel32.setForeground(new java.awt.Color(255, 255, 255));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtThongKeSachBanNgay.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtThongKeSachBanNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtThongKeSachBanNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeSachBanNgay.setText("Tăng 15 cuốn so với ngày hôm qua!");
        jPanel32.add(txtThongKeSachBanNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel28.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 370, -1));

        txtThongKeSachBan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtThongKeSachBan.setForeground(new java.awt.Color(0, 153, 153));
        txtThongKeSachBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeSachBan.setText("365 cuốn");
        jPanel28.add(txtThongKeSachBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, 50));

        cbSachBanNgay.setBackground(new java.awt.Color(153, 196, 200));
        cbSachBanNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbSachBanNgay.setForeground(new java.awt.Color(255, 255, 255));
        cbSachBanNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng Sách Bán Hôm Nay", "Tổng Sách Bán Hôm Qua", "Tổng Sách Bán 7 Ngày Trước" }));
        cbSachBanNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSachBanNgayItemStateChanged(evt);
            }
        });
        jPanel28.add(cbSachBanNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jpnlThongKe.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(153, 196, 200));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbDoanhThuNgay.setBackground(new java.awt.Color(153, 196, 200));
        cbDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        cbDoanhThuNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng Doanh Thu Hôm Nay", "Tổng Doanh Thu Hôm Qua", "Tổng Doanh Thu 7 Ngày Trước" }));
        cbDoanhThuNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDoanhThuNgayItemStateChanged(evt);
            }
        });
        cbDoanhThuNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbDoanhThuNgayMouseClicked(evt);
            }
        });
        jPanel26.add(cbDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel27.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel29.setBackground(new java.awt.Color(204, 204, 204));
        jPanel29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setBackground(new java.awt.Color(153, 196, 200));
        jPanel31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel29.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 360, 30));

        txtThongKeDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        txtThongKeDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtThongKeDoanhThuNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeDoanhThuNgay.setText("Tăng 500.000đ so với ngày hôm qua!");
        jPanel29.add(txtThongKeDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel27.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 370, 30));

        txtThongKeDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtThongKeDoanhThu.setForeground(new java.awt.Color(0, 153, 153));
        txtThongKeDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeDoanhThu.setText("10.506.536đ ");
        jPanel27.add(txtThongKeDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, 50));

        jpnlThongKe.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 370, -1));

        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(153, 196, 200));
        jPanel35.setForeground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel33.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        jPanel40.setBackground(new java.awt.Color(204, 204, 204));
        jPanel40.setForeground(new java.awt.Color(255, 255, 255));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel91.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("Doanh thu được tính từ đầu tháng đến giờ!");
        jPanel40.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel33.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        jPanel39.setBackground(new java.awt.Color(153, 196, 200));
        jPanel39.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Tổng Doanh Thu Trong Tháng");
        jLabel90.setToolTipText("");
        jPanel39.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel33.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txtThongKeDoanhThuThang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtThongKeDoanhThuThang.setForeground(new java.awt.Color(0, 153, 153));
        txtThongKeDoanhThuThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeDoanhThuThang.setText("20.568.153");
        jPanel33.add(txtThongKeDoanhThuThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, 50));

        jpnlThongKe.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 370, -1));

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel36.setBackground(new java.awt.Color(153, 196, 200));
        jPanel36.setForeground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel34.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        jPanel38.setBackground(new java.awt.Color(204, 204, 204));
        jPanel38.setForeground(new java.awt.Color(255, 255, 255));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Tổng số cuốn sách được bán từ đầu tháng đến giờ!");
        jPanel38.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel34.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        jPanel37.setBackground(new java.awt.Color(153, 196, 200));
        jPanel37.setForeground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Tổng Sách Bán Trong Tháng");
        jLabel87.setToolTipText("");
        jPanel37.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 30));

        jPanel34.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txtThongKeSachBanThang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtThongKeSachBanThang.setForeground(new java.awt.Color(0, 153, 153));
        txtThongKeSachBanThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtThongKeSachBanThang.setText("365 cuốn");
        jPanel34.add(txtThongKeSachBanThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 370, 50));

        jpnlThongKe.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jLabel81.setBackground(new java.awt.Color(66, 149, 166));
        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(66, 149, 166));
        jLabel81.setText("Thông tin thống kê");
        jpnlThongKe.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 190, 40));
        jpnlThongKe.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 600, 10));

        jLabel84.setBackground(new java.awt.Color(66, 149, 166));
        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(66, 149, 166));
        jLabel84.setText("Thông tin sách bán trong tháng");
        jpnlThongKe.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 260, 30));
        jpnlThongKe.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 293, 500, 10));

        tbThongKeSachBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Thời Gian", "Nhân Viên", "Số Lượng", "Thành Tiền"
            }
        ));
        tbThongKeSachBan.setRowHeight(25);
        tbThongKeSachBan.setShowGrid(true);
        jScrollPane9.setViewportView(tbThongKeSachBan);

        jpnlThongKe.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 750, 240));

        menu_selection.addTab("tab6", jpnlThongKe);

        jpnlTTCN.setBackground(new java.awt.Color(255, 255, 255));
        jpnlTTCN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setBackground(new java.awt.Color(66, 149, 166));
        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(66, 149, 166));
        jLabel75.setText("Thông Tin Cá Nhân");
        jpnlTTCN.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 200, -1));
        jpnlTTCN.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 570, 10));

        jPanel22.setBackground(new java.awt.Color(224, 236, 237));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Mật Khẩu:");
        jPanel23.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 120, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Họ Tên:");
        jPanel23.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText("Giới Tính:");
        jPanel23.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, 30));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(153, 153, 153));
        jLabel45.setText("Ngày Sinh:");
        jPanel23.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, 30));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(153, 153, 153));
        jLabel47.setText("Quê Quán:");
        jPanel23.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 120, 30));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(153, 153, 153));
        jLabel56.setText("Số Điện Thoại:");
        jPanel23.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 30));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 250));

        txtTTCNMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 360, 30));

        txtTTCNHoTen.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 360, 30));

        txtTTCNGioiTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 360, 30));

        txtTTCNNgaySinh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 360, 30));

        txtTTCNQueQuan.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNQueQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 360, 30));

        txtTTCNSoDienThoai.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel22.add(txtTTCNSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 360, 30));

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setBackground(new java.awt.Color(89, 156, 173));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("CHẤM CÔNG");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jPanel24.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 70));

        jPanel22.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 210, 110));

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(89, 156, 173));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("CẬP NHẬT THÔNG TIN");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jPanel25.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 70));

        jPanel22.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 210, 110));

        jpnlTTCN.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 750, 250));

        jLabel78.setBackground(new java.awt.Color(66, 149, 166));
        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(66, 149, 166));
        jLabel78.setText("Thông Tin Chấm Công");
        jpnlTTCN.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 180, -1));
        jpnlTTCN.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 570, 0));
        jpnlTTCN.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 600, 10));

        tbTTCNChamCong1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Chấm Công", "Ngày Chấm Công", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTTCNChamCong1.setRowHeight(25);
        tbTTCNChamCong1.setShowGrid(true);
        jScrollPane8.setViewportView(tbTTCNChamCong1);
        if (tbTTCNChamCong1.getColumnModel().getColumnCount() > 0) {
            tbTTCNChamCong1.getColumnModel().getColumn(0).setResizable(false);
            tbTTCNChamCong1.getColumnModel().getColumn(1).setResizable(false);
            tbTTCNChamCong1.getColumnModel().getColumn(2).setResizable(false);
        }

        jpnlTTCN.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 750, 220));

        menu_selection.addTab("tab7", jpnlTTCN);

        jPanel4.add(menu_selection, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 780, 580));
        jPanel4.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 80));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 780, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        menu_selection.setSelectedIndex(0);
        jpnMenu_TrangChu.setBackground(Color.white);
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        menu_selection.setSelectedIndex(1);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(Color.white);
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        menu_selection.setSelectedIndex(2);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(Color.white);
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        menu_selection.setSelectedIndex(3);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(Color.white);
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        menu_selection.setSelectedIndex(6);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(Color.white);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        menu_selection.setSelectedIndex(5);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(new Color(153,196,200));
        jpnMenu_ThongKe.setBackground(Color.white);
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void txtQLNVHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLNVHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLNVHoTenActionPerformed

    private void txtQLBHTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLBHTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLBHTenSachActionPerformed

    private void txtQLBHMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLBHMaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLBHMaSachActionPerformed

    private void txtQLBHDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLBHDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLBHDonGiaActionPerformed

    private void txtQLKTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKTenSachActionPerformed

    private void txtQLKMaChuyenMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKMaChuyenMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKMaChuyenMucActionPerformed

    private void txtQLKTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKTacGiaActionPerformed

    private void txtQLKMaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKMaNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKMaNhaCungCapActionPerformed

    private void txtQLKSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKSoLuongActionPerformed

    private void txtQLKTenNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKTenNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKTenNhaCungCapActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.setState(Main.ICONIFIED);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txtQLSSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSSoLuongActionPerformed

    private void txtQLSTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSTenSachActionPerformed

    private void txtQLSGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSGiaBanActionPerformed

    private void txtQLSTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSTacGiaActionPerformed

    private void txtQLSMaChuyenMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSMaChuyenMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSMaChuyenMucActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/log/NhoMatKhau.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write("false");
            bw.newLine();
            bw.write("null");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtQLSMaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLSMaNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLSMaNhaCungCapActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        menu_selection.setSelectedIndex(4);
        jpnMenu_TrangChu.setBackground(new Color(153,196,200));
        jpnMenu_QLBH.setBackground(new Color(153,196,200));
        jpnMenu_QLK.setBackground(new Color(153,196,200));
        jpnMenu_QLNV.setBackground(new Color(153,196,200));
        jpnMenu_QLS.setBackground(Color.white);
        jpnMenu_ThongKe.setBackground(new Color(153,196,200));
        jpnMenu_TTCN.setBackground(new Color(153,196,200));
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jpnMenu_QLSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMenu_QLSMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jpnMenu_QLSMouseClicked

    private void txtQLBHSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLBHSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLBHSoLuongActionPerformed

    private void tbQLBHgetALLSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLBHgetALLSachMouseClicked
        int row = tbQLBHgetALLSach.getSelectedRow();
        if(row >= 0){
            txtQLBHMaSach.setText(tbQLBHgetALLSach.getValueAt(row, 0).toString());
            txtQLBHDonGia.setText(tbQLBHgetALLSach.getValueAt(row, 5).toString());
        }
    }//GEN-LAST:event_tbQLBHgetALLSachMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int MaSach = Integer.parseInt(txtQLBHMaSach.getText());
        float GiaBan = Float.parseFloat(txtQLBHDonGia.getText());
        int SoLuong = Integer.parseInt(txtQLBHSoLuong.getText());
        float DonGia = GiaBan * SoLuong;
        String TrangThai = cbQLBHTrangThai.getSelectedItem().toString();
        int result = QuanLyBanHangController.insertHoaDon(MaSach, objNV.getMaNhanVien(), DonGia, SoLuong, TrangThai);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công!");
            startQLBH();
            startThongKe();
        }else{
            JOptionPane.showMessageDialog(this, "Thanh Toán Không Thành Công! Vui Lòng Kiểm Tra Lại!");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        String TenSach = txtQLBHTenSach.getText();
        if(TenSach.equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sách!");
        }else{
            while(tbmodelQLBH.getRowCount() > 0){
                tbmodelQLBH.removeRow(0);
            }
            String[] nameCol = {"Mã Sách", "Tên Sách", "Mã Chuyên Mục","Mã Nhà Cung Cấp", "Tác Giả", "Giá Bán", "Số Lượng"};
            tbmodelQLBH.setColumnIdentifiers(nameCol);
            List<sach> lstSach = QuanLyBanHangController.getSachByTen(TenSach);
            for(int i = 0; i < lstSach.size(); i++){
                String[] row = {
                    String.valueOf(lstSach.get(i).getMaSach()),
                    lstSach.get(i).getTenSach(),
                    String.valueOf(lstSach.get(i).getMaChuyenMuc()),
                    String.valueOf(lstSach.get(i).getMaNhaCungCap()),
                    lstSach.get(i).getTacGia(),
                    String.valueOf(lstSach.get(i).getGiaBan()),
                    String.valueOf(lstSach.get(i).getSoLuong()),
                };
                tbmodelQLBH.addRow(row);
            }
            tbQLBHgetALLSach.setModel(tbmodelQLBH);
        }
    }//GEN-LAST:event_jButton6MouseClicked

    private void txtQLKGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKGiaBanActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        nhacungcap objNhaCungCap = new nhacungcap();
        objNhaCungCap.setTenNhaCungCap(txtQLKTenNhaCungCap.getText());
        String TrangThaiNCC = cbQLKTrangThai.getSelectedItem().toString();
        objNhaCungCap.setTrangThai(TrangThaiNCC);
        int result = QuanLyKhoController.insertNhaCungCap(objNhaCungCap);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Thêm Nhà Cung Cấp Thành Công!");
            startQLK();
            txtQLKTenNhaCungCap.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "Thêm Nhà Cung Cấp Thành Không Công! Vui Lòng Kiểm Tra Lại!");
        }
    }//GEN-LAST:event_jButton8MouseClicked
    
    public void resetInsertSach(){
        txtQLKMaNhaCungCap.setText("");
        txtQLKMaChuyenMuc.setText("");
        txtQLKGiaBan.setText("");
        txtQLKSoLuong.setText("");
        txtQLKTenSach.setText("");
        txtQLKTacGia.setText("");
    }
    
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        sach objSach = new sach();
        int MaNhaCungCap = Integer.parseInt(txtQLKMaNhaCungCap.getText());
        int MaChuyenMuc = Integer.parseInt(txtQLKMaChuyenMuc.getText());
        float GiaBan = Float.parseFloat(txtQLKGiaBan.getText());
        int SoLuong = Integer.parseInt(txtQLKSoLuong.getText());
        objSach.setTenSach(txtQLKTenSach.getText());
        objSach.setMaChuyenMuc(MaChuyenMuc);
        objSach.setMaNhaCungCap(MaNhaCungCap);
        objSach.setTacGia(txtQLKTacGia.getText());
        objSach.setGiaBan(GiaBan);
        objSach.setSoLuong(SoLuong);
        int result = QuanLyKhoController.insertSach(objSach);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Thêm Sách Thành Công!");
            startQLBH();
            startQLS();
            resetInsertSach();
        }else{
            JOptionPane.showMessageDialog(this, "Thêm Sách Không Thành Công!");
        }
    }//GEN-LAST:event_jButton7MouseClicked

    private void txtQLKTenChuyenMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKTenChuyenMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKTenChuyenMucActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        chuyenmuc objChuyenMuc = new chuyenmuc();
        objChuyenMuc.setTenChuyenMuc(txtQLKTenChuyenMuc.getText());
        int result = QuanLyKhoController.insertChuyenMuc(objChuyenMuc);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Thêm Chuyên Mục Thành Công!");
            startQLK();
            txtQLKTenChuyenMuc.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "Thêm Chuyên Mục Không Thành Công! Vui Lòng Kiểm Tra Lại!");
        }
    }//GEN-LAST:event_jButton11MouseClicked

    private void txtQLKTenNhaCungCap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQLKTenNhaCungCap1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQLKTenNhaCungCap1ActionPerformed

    private void tbQLKChuyenMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLKChuyenMucMouseClicked
        int row = tbQLKChuyenMuc.getSelectedRow();
        if(row >= 0){
            txtQLKMaChuyenMuc.setText(tbQLKChuyenMuc.getValueAt(row, 0).toString());
        }
    }//GEN-LAST:event_tbQLKChuyenMucMouseClicked

    private void tbQLKNhaCungCap1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLKNhaCungCap1MouseClicked
        int row = tbQLKNhaCungCap1.getSelectedRow();
        if(row >= 0){
            txtQLKMaNhaCungCap.setText(tbQLKNhaCungCap1.getValueAt(row, 0).toString());
        }
    }//GEN-LAST:event_tbQLKNhaCungCap1MouseClicked

    public void setSach(String MaSach){
        
    }
    private void tbQLSGetAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLSGetAllMouseClicked
        int row = tbQLSGetAll.getSelectedRow();
        if(row >= 0){
            txtQLSTenSach.setText(tbQLSGetAll.getValueAt(row, 1).toString());
            txtQLSMaChuyenMuc.setText(tbQLSGetAll.getValueAt(row, 2).toString());
            txtQLSMaNhaCungCap.setText(tbQLSGetAll.getValueAt(row, 3).toString());
            txtQLSTacGia.setText(tbQLSGetAll.getValueAt(row, 4).toString());
            txtQLSGiaBan.setText(tbQLSGetAll.getValueAt(row, 5).toString());
            txtQLSSoLuong.setText(tbQLSGetAll.getValueAt(row, 6).toString());
            txtQLSMaSach.setText(tbQLSGetAll.getValueAt(row, 0).toString());
            btnQLSSuaSach.setEnabled(true);
            btnQLSXoaSach.setEnabled(true);
        }
    }//GEN-LAST:event_tbQLSGetAllMouseClicked

    public void resetSach(){
        txtQLSTenSach.setText("");
        txtQLSTacGia.setText("");
        txtQLSMaChuyenMuc.setText("");
        txtQLSMaNhaCungCap.setText("");
        txtQLSGiaBan.setText("");
        txtQLSSoLuong.setText("");
    }
    private void btnQLSSuaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLSSuaSachMouseClicked
        sach objSach = new sach();
        objSach.setTenSach(txtQLSTenSach.getText());
        objSach.setTacGia(txtQLSTacGia.getText());
        objSach.setMaChuyenMuc(Integer.parseInt(txtQLSMaChuyenMuc.getText()));      
        objSach.setMaNhaCungCap(Integer.parseInt(txtQLSMaNhaCungCap.getText()));
        objSach.setGiaBan(Float.parseFloat(txtQLSGiaBan.getText()));
        objSach.setSoLuong(Integer.parseInt(txtQLSSoLuong.getText()));
        objSach.setMaSach(Integer.parseInt(txtQLSMaSach.getText()));
        
        int result = SachController.updateSach(objSach);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!");
            startQLS();
            startQLBH();
            resetSach();
        }else{
            JOptionPane.showMessageDialog(this, "Cập nhật sách không thành công! Vui lòng kiểm tra lại!");
        }
    }//GEN-LAST:event_btnQLSSuaSachMouseClicked

    private void btnQLSXoaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLSXoaSachMouseClicked
        int MaSach = Integer.parseInt(txtQLSMaSach.getText());
        int result = SachController.deleteSach(MaSach);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
            startQLBH();
            startQLS();
            resetSach();
        }else{
            JOptionPane.showMessageDialog(this, "Xóa sách không thành công! Vui lòng kiểm tra lại!");
        }

    }//GEN-LAST:event_btnQLSXoaSachMouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        int MaNhanVien = objNV.getMaNhanVien();
        chamcong objCC = CaNhanController.getLastChamCong(MaNhanVien);
        String Now = String.valueOf(now());
        if(Now.equals(objCC.getNgayChamCong()) == false){
            int result = CaNhanController.insertChamCong(MaNhanVien);
            if(result == 1){
                JOptionPane.showMessageDialog(this, "Chấm công thành công!");
                startTTCN();
            }else{
                JOptionPane.showMessageDialog(this, "Chấm công không thành công! Vui lòng thao tác lại!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Bạn đã thực hiện chấm công cho hôm nay rồi!");
        }
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        nhanvien objNVUpdate = new nhanvien();
        objNVUpdate.setMaNhanVien(objNV.getMaNhanVien());
        objNVUpdate.setHoTen(txtTTCNHoTen.getText());
        objNVUpdate.setGioiTinh(txtTTCNGioiTinh.getText());
        objNVUpdate.setNgaySinh(txtTTCNNgaySinh.getText());
        objNVUpdate.setQueQuan(txtTTCNQueQuan.getText());
        objNVUpdate.setSoDienThoai(txtTTCNSoDienThoai.getText());
        objNVUpdate.setMatKhau(txtTTCNMatKhau.getText());
        int result = CaNhanController.updateThongTinCaNhan(objNVUpdate);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
            startTTCN();
        }else{
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin không thành công! Vui lòng kiểm tra lại!");
        }
        
    }//GEN-LAST:event_jButton10MouseClicked

    private void tbQLNVgetALLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNVgetALLMouseClicked
        btnQLNVLamMoi.setVisible(true);
        btnQLNVLamMoi.setEnabled(true);
        btnQLNVThem.setVisible(false);
        btnQLNVThem.setEnabled(false);
        btnQLNVSua.setVisible(true);
        btnQLNVXoa.setVisible(true);
        int row = tbQLNVgetALL.getSelectedRow();
        if(row >= 0){
            txtQLNVMaNhanVien.setText(tbQLNVgetALL.getValueAt(row, 0).toString());
            txtQLNVHoTen.setText(tbQLNVgetALL.getValueAt(row, 1).toString());
            txtQLNVGioiTinh.setText(tbQLNVgetALL.getValueAt(row, 2).toString());
            txtQLNVNgaySinh.setText(tbQLNVgetALL.getValueAt(row, 3).toString());
            txtQLNVQueQuan.setText(tbQLNVgetALL.getValueAt(row, 4).toString());
            txtQLNVSoDienThoai.setText(tbQLNVgetALL.getValueAt(row, 5).toString());
            txtQLNVMatKhau.setText(tbQLNVgetALL.getValueAt(row, 6).toString());
            txtQLNVAdmin.setText(tbQLNVgetALL.getValueAt(row, 7).toString());
            btnQLNVThem.setEnabled(false);
            btnQLNVSua.setEnabled(true);
            btnQLNVXoa.setEnabled(true);
        }
    }//GEN-LAST:event_tbQLNVgetALLMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(txtQLNVMaNhanVien.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần chấm công!");
        }else{
            int MaNhanVien = Integer.parseInt(txtQLNVMaNhanVien.getText());
            chamcong objCC = CaNhanController.getLastChamCong(MaNhanVien);
            String Now = String.valueOf(now());
            if(Now.equals(objCC.getNgayChamCong()) == false){
                int result = CaNhanController.insertChamCong(MaNhanVien);
                if(result == 1){
                    JOptionPane.showMessageDialog(this, "Chấm công thành công!");
                    startTTCN();
                    startQLNV();
                }else{
                    JOptionPane.showMessageDialog(this, "Chấm công không thành công! Vui lòng thao tác lại!");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Bạn đã thực hiện chấm công cho hôm nay rồi!");
            }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void btnQLNVLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNVLamMoiMouseClicked
        btnQLNVLamMoi.setVisible(false);
        btnQLNVLamMoi.setEnabled(false);
        btnQLNVThem.setVisible(true);
        btnQLNVThem.setEnabled(true);
        btnQLNVXoa.setVisible(false);
        btnQLNVSua.setVisible(false);
        
        txtQLNVMaNhanVien.setText("");
        txtQLNVHoTen.setText("");
        txtQLNVGioiTinh.setText("");
        txtQLNVQueQuan.setText("");
        txtQLNVSoDienThoai.setText("");
        txtQLNVAdmin.setText("");
        txtQLNVMatKhau.setText("");
        txtQLNVNgaySinh.setText("");
    }//GEN-LAST:event_btnQLNVLamMoiMouseClicked

    void resetQLNV(){
        txtQLNVMaNhanVien.setText("");
        txtQLNVHoTen.setText("");
        txtQLNVGioiTinh.setText("");
        txtQLNVQueQuan.setText("");
        txtQLNVSoDienThoai.setText("");
        txtQLNVAdmin.setText("");
        txtQLNVMatKhau.setText("");
        txtQLNVNgaySinh.setText("");
    }
    private void btnQLNVThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNVThemMouseClicked
        if(txtQLNVHoTen.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVGioiTinh.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if(txtQLNVGioiTinh.getText().equals("Nam") == true){
                String GioiTinh = "Nam";
            }else{
                if(txtQLNVGioiTinh.getText().equals("Nữ") == true){
                    String GioiTinh = "Nữ";
                }else{
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại giới tính! Nam hoặc Nữ!");
                    return;
                }
            }
        }
        if(txtQLNVQueQuan.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVSoDienThoai.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
            if(txtQLNVSoDienThoai.getText().matches(reg) == false){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số điện thoại!");
                return;
            }
        }
        if(txtQLNVAdmin.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if(txtQLNVAdmin.getText().equals("0") == true){
                int Admin = 0;
            }else{
                if(txtQLNVAdmin.getText().equals("1") == true){
                    int Admin = 1;
                }else{
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại phân quyền Admin! Giá trị 1 là Admin, giá trị 0 là Nhân Viên!!");
                    return;
                }
            }
        }
        if(txtQLNVMatKhau.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVNgaySinh.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if (txtQLNVNgaySinh.getText().matches("\\d{4}-\\d{2}-\\d{2}") == false) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh theo định dạng năm-tháng-ngày! Ví dụ: 2002-01-31");
                return;
            }
        }

        nhanvien objNV = new nhanvien();
        objNV.setHoTen(txtQLNVHoTen.getText());
        objNV.setGioiTinh(txtQLNVGioiTinh.getText());
        objNV.setSoDienThoai(txtQLNVSoDienThoai.getText());
        objNV.setQueQuan(txtQLNVQueQuan.getText());
        objNV.setNgaySinh(txtQLNVNgaySinh.getText());
        objNV.setMatKhau(txtQLNVMatKhau.getText());
        objNV.setAdmin(Integer.parseInt(txtQLNVAdmin.getText()));
        
        int result = QuanLyNhanVienController.insertNhanVien(objNV);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            startQLNV();
            resetQLNV();
        }else{
            JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công! Vui lòng kiểm tra lại!");
        }
    }//GEN-LAST:event_btnQLNVThemMouseClicked

    private void btnQLNVSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNVSuaMouseClicked
        if(txtQLNVHoTen.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVGioiTinh.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if(txtQLNVGioiTinh.getText().equals("Nam") == true){
                String GioiTinh = "Nam";
            }else{
                if(txtQLNVGioiTinh.getText().equals("Nữ") == true){
                    String GioiTinh = "Nữ";
                }else{
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại giới tính! Nam hoặc Nữ!");
                    return;
                }
            }
        }
        if(txtQLNVQueQuan.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVSoDienThoai.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
            if(txtQLNVSoDienThoai.getText().matches(reg) == false){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số điện thoại!");
                return;
            }
        }
        if(txtQLNVAdmin.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if(txtQLNVAdmin.getText().equals("0") == true){
                int Admin = 0;
            }else{
                if(txtQLNVAdmin.getText().equals("1") == true){
                    int Admin = 1;
                }else{
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại phân quyền Admin! Giá trị 1 là Admin, giá trị 0 là Nhân Viên!!");
                    return;
                }
            }
        }
        if(txtQLNVMatKhau.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if(txtQLNVNgaySinh.getText().equals("") == true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }else{
            if (txtQLNVNgaySinh.getText().matches("\\d{4}-\\d{2}-\\d{2}") == false) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh theo định dạng năm-tháng-ngày! Ví dụ: 2002-01-31");
                return;
            }
        }
        
        if(txtQLNVMatKhau.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng đầy nhập đủ thông tin!");
            return;
        }
        
        nhanvien objNV = new nhanvien();
        objNV.setMaNhanVien(Integer.parseInt(txtQLNVMaNhanVien.getText()));
        objNV.setHoTen(txtQLNVHoTen.getText());
        objNV.setGioiTinh(txtQLNVGioiTinh.getText());
        objNV.setSoDienThoai(txtQLNVSoDienThoai.getText());
        objNV.setQueQuan(txtQLNVQueQuan.getText());
        objNV.setNgaySinh(txtQLNVNgaySinh.getText());
        objNV.setMatKhau(txtQLNVMatKhau.getText());
        objNV.setAdmin(Integer.parseInt(txtQLNVAdmin.getText()));
        
        int result = QuanLyNhanVienController.updateNhanVien(objNV);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
            startQLNV();
            startTTCN();
        }else{
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin không thành công! Vui lòng kiểm tra lại!");
        }
        
    }//GEN-LAST:event_btnQLNVSuaMouseClicked

    private void btnQLNVXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNVXoaMouseClicked
        int MaNhanVien = Integer.parseInt(txtQLNVMaNhanVien.getText());
        int result = QuanLyNhanVienController.deleteNhanVien(MaNhanVien);
        if(result == 1){
            JOptionPane.showMessageDialog(this, "Xóa thông tin nhân viên thành công!");
            startQLNV();
            resetQLNV();
        }else{
            JOptionPane.showMessageDialog(this, "Xóa thông tin nhân viên không thành công! Vui lòng kiểm tra lại!");
        }
    }//GEN-LAST:event_btnQLNVXoaMouseClicked

    private void cbDoanhThuNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDoanhThuNgayMouseClicked
        
    }//GEN-LAST:event_cbDoanhThuNgayMouseClicked

    private void cbDoanhThuNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDoanhThuNgayItemStateChanged
        if(cbDoanhThuNgay.getSelectedIndex() == 0){
            String ThoiGian = String.valueOf(now());
            String DoanhThu = ThongKeController.DoanhThuNgay(ThoiGian);
            txtThongKeDoanhThu.setText(DoanhThu+"đ");
            txtThongKeDoanhThuNgay.setText("Doanh thu được tính từ đầu ngày tới giờ!");
        }
        if(cbDoanhThuNgay.getSelectedIndex() == 1){
            String DoanhThu = ThongKeController.DoanhThuHomQua();
            txtThongKeDoanhThu.setText(DoanhThu+"đ");
            txtThongKeDoanhThuNgay.setText("Doanh thu được tính trong ngày hôm qua!");
        }
        if(cbDoanhThuNgay.getSelectedIndex() == 2){
            String DoanhThu = ThongKeController.DoanhThuTuan();
            txtThongKeDoanhThu.setText(DoanhThu+"đ");
            txtThongKeDoanhThuNgay.setText("Doanh thu được tính từ 7 ngày trước!");
        }
    }//GEN-LAST:event_cbDoanhThuNgayItemStateChanged

    private void cbSachBanNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSachBanNgayItemStateChanged
        if(cbSachBanNgay.getSelectedIndex() == 0){
            String ThoiGian = String.valueOf(now());
            String SachBan = ThongKeController.SachBanNgay(ThoiGian);
            txtThongKeSachBan.setText(SachBan+" cuốn");
            txtThongKeSachBanNgay.setText("Tổng số cuốn sách được bán từ đầu ngày tới giờ!");
        }
        if(cbSachBanNgay.getSelectedIndex() == 1){
            String SachBan = ThongKeController.SachBanHomQua();
            txtThongKeSachBan.setText(SachBan+" cuốn");
            txtThongKeSachBanNgay.setText("Tổng số cuốn sách được bán trong ngày hôm qua!");
        }
        if(cbSachBanNgay.getSelectedIndex() == 2){
            String SachBan = ThongKeController.SachBanTuan();
            txtThongKeSachBan.setText(SachBan+" cuốn");
            txtThongKeSachBanNgay.setText("Tổng số cuốn sách được bán trong 7 ngày trước!");
        }
    }//GEN-LAST:event_cbSachBanNgayItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQLNVLamMoi;
    private javax.swing.JButton btnQLNVSua;
    private javax.swing.JButton btnQLNVThem;
    private javax.swing.JButton btnQLNVXoa;
    private javax.swing.JButton btnQLSSuaSach;
    private javax.swing.JButton btnQLSXoaSach;
    private javax.swing.JComboBox<String> cbDoanhThuNgay;
    private javax.swing.JComboBox<String> cbQLBHTrangThai;
    private javax.swing.JComboBox<String> cbQLKTrangThai;
    private javax.swing.JComboBox<String> cbSachBanNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel jpnMenu_QLBH;
    private javax.swing.JPanel jpnMenu_QLK;
    private javax.swing.JPanel jpnMenu_QLNV;
    private javax.swing.JPanel jpnMenu_QLS;
    private javax.swing.JPanel jpnMenu_TTCN;
    private javax.swing.JPanel jpnMenu_ThongKe;
    private javax.swing.JPanel jpnMenu_TrangChu;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnlQLBH;
    private javax.swing.JPanel jpnlQLK;
    private javax.swing.JPanel jpnlQLNV;
    private javax.swing.JPanel jpnlQLS;
    private javax.swing.JPanel jpnlTTCN;
    private javax.swing.JPanel jpnlThongKe;
    private javax.swing.JTabbedPane menu_selection;
    private javax.swing.JTable tbQLBHHoaDon;
    private javax.swing.JTable tbQLBHgetALLSach;
    private javax.swing.JTable tbQLKChuyenMuc;
    private javax.swing.JTable tbQLKNhaCungCap1;
    private javax.swing.JTable tbQLNVChamCong;
    private javax.swing.JTable tbQLNVgetALL;
    private javax.swing.JTable tbQLSGetAll;
    private javax.swing.JTable tbTTCNChamCong1;
    private javax.swing.JTable tbThongKeSachBan;
    private javax.swing.JLabel txtHello;
    private javax.swing.JTextField txtQLBHDonGia;
    private javax.swing.JTextField txtQLBHMaSach;
    private javax.swing.JTextField txtQLBHSoLuong;
    private javax.swing.JTextField txtQLBHTenSach;
    private javax.swing.JTextField txtQLKGiaBan;
    private javax.swing.JTextField txtQLKMaChuyenMuc;
    private javax.swing.JTextField txtQLKMaNhaCungCap;
    private javax.swing.JTextField txtQLKSoLuong;
    private javax.swing.JTextField txtQLKTacGia;
    private javax.swing.JTextField txtQLKTenChuyenMuc;
    private javax.swing.JTextField txtQLKTenNhaCungCap;
    private javax.swing.JTextField txtQLKTenNhaCungCap1;
    private javax.swing.JTextField txtQLKTenSach;
    private javax.swing.JTextField txtQLNVAdmin;
    private javax.swing.JTextField txtQLNVGioiTinh;
    private javax.swing.JTextField txtQLNVHoTen;
    private javax.swing.JTextField txtQLNVMaNhanVien;
    private javax.swing.JTextField txtQLNVMatKhau;
    private javax.swing.JTextField txtQLNVNgaySinh;
    private javax.swing.JTextField txtQLNVQueQuan;
    private javax.swing.JTextField txtQLNVSoDienThoai;
    private javax.swing.JTextField txtQLSGiaBan;
    private javax.swing.JTextField txtQLSMaChuyenMuc;
    private javax.swing.JTextField txtQLSMaNhaCungCap;
    private javax.swing.JTextField txtQLSMaSach;
    private javax.swing.JTextField txtQLSSoLuong;
    private javax.swing.JTextField txtQLSTacGia;
    private javax.swing.JTextField txtQLSTenSach;
    private javax.swing.JTextField txtTTCNGioiTinh;
    private javax.swing.JTextField txtTTCNHoTen;
    private javax.swing.JTextField txtTTCNMatKhau;
    private javax.swing.JTextField txtTTCNNgaySinh;
    private javax.swing.JTextField txtTTCNQueQuan;
    private javax.swing.JTextField txtTTCNSoDienThoai;
    private javax.swing.JLabel txtThongKeDoanhThu;
    private javax.swing.JLabel txtThongKeDoanhThuNgay;
    private javax.swing.JLabel txtThongKeDoanhThuThang;
    private javax.swing.JLabel txtThongKeSachBan;
    private javax.swing.JLabel txtThongKeSachBanNgay;
    private javax.swing.JLabel txtThongKeSachBanThang;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtTrangChuTenNV;
    // End of variables declaration//GEN-END:variables
}
