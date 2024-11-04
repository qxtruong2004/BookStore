package database;

import Model.KhachHang;
import Model.TacGia;

import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO implements DAOInterface<KhachHang> {
    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "select * from khachhang";
            PreparedStatement ps = con.prepareStatement(sql);

            //b3. thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            //b4
            while (rs.next()) {
                String maKhachHang = rs.getString("makhachhang");
                String tenDangNhap = rs.getString("tendangnhap");
                String matKhau = rs.getString("matkhau");
                String hoVaTen = rs.getString("hovaten");
                String gioiTinh = rs.getString("gioitinh");
                String diaChi = rs.getString("diachi");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String diaChiMuaHang = rs.getString("diachimuahang");
                Date ngaySinh = rs.getDate("ngaysinh");
                String soDienThoai = rs.getString("sodienthoai");
                String email = rs.getString("email");
                boolean nhanThongTin = rs.getBoolean("nhanthongtin");

                KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, hoVaTen, matKhau, gioiTinh, diaChi, diaChiMuaHang, diaChiNhanHang, ngaySinh, soDienThoai, email, nhanThongTin);
                ketQua.add(kh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public KhachHang selectById(KhachHang t) {
        KhachHang ketQua = null;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM khachhang WHERE makhachhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhachHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String maKhacHang = rs.getString("makhachhang");
                String tenDangNhap = rs.getString("tendangnhap");
                String matKhau = rs.getString("matkhau");
                String hoVaTen = rs.getString("hovaten");
                String gioiTinh = rs.getString("gioitinh");
                String diaChi = rs.getString("diachi");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String diaChiMuaHang = rs.getString("diachimuahang");
                Date ngaySinh = rs.getDate("ngaysinh");
                String soDienThoai = rs.getString("sodienthoai");
                String email = rs.getString("email");
                boolean nhanThongTin = rs.getBoolean("nhanthongtin");

                ketQua = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang,
                        diaChiMuaHang, ngaySinh, soDienThoai, email, nhanThongTin);
            }

            // Bước 5:

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    public boolean kiemTraTenDangNhap(String tenDangNhap) {
        boolean ketQua = false;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM khachhang WHERE tenDangNhap=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tenDangNhap);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                ketQua = true;
            }

            // Bước 5:
            JDBC_Util.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO khachhang (makhachhang, tendangnhap, matkhau, hovaten, gioitinh, diachi, diachinhanhang, diachimuahang, ngaysinh, sodienthoai, email, nhanthongtin) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhachHang());
            st.setString(2, t.getTenDangNhap());
            st.setString(3, t.getMatKhau());
            st.setString(4, t.getHoVaTen());
            st.setString(5, t.getGioiTinh());
            st.setString(6, t.getDiaChi());
            st.setString(7, t.getDiaChiNhanHang());
            st.setString(8, t.getDiaChiMuaHang());
            st.setDate(9, t.getNgaySinh());
            st.setString(10, t.getSoDienThoai());
            st.setString(11, t.getEmail());
            st.setBoolean(12, t.getNhanThongTin());

            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insertAll(ArrayList<KhachHang> arr) {
        int dem = 0;
        for (KhachHang KhachHang : arr) {
            dem += this.insert(KhachHang);
        }
        return dem;
    }

    @Override
    public int delete(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from khachhang " + " WHERE makhachhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhachHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            JDBC_Util.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int deleteAll(ArrayList<KhachHang> arr) {
        int dem = 0;
        for (KhachHang KhachHang : arr) {
            dem += this.delete(KhachHang);
        }
        return dem;
    }

    @Override
    public int update(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE khachhang " + " SET " + " tendangnhap=?" + ", matkhau=?" + ", hovaten=?" + ", gioitinh=?"
                    + ", diachi=?" + ", diachinhanhang=?" + ", diachimuahang=?" + ", ngaysinh=?" + ", sodienthoai=?"
                    + ", email=?" + ", nhanthongtin=?" + " WHERE makhachhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTenDangNhap());
            st.setString(2, t.getMatKhau());
            st.setString(3, t.getHoVaTen());
            st.setString(4, t.getGioiTinh());
            st.setString(5, t.getDiaChi());
            st.setString(6, t.getDiaChiNhanHang());
            st.setString(7, t.getDiaChiMuaHang());
            st.setDate(8, t.getNgaySinh());
            st.setString(9, t.getSoDienThoai());
            st.setString(10, t.getEmail());
            st.setBoolean(11, t.getNhanThongTin());
            st.setString(12, t.getMaKhachHang());
            // Bước 3: thực thi câu lệnh SQL

            System.out.println(sql);
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    public static void main(String[] args) {
        KhachHangDAO dao = new KhachHangDAO();
        //selectAll
////        ArrayList<KhachHang> arr = dao.selectAll();
////        for (KhachHang khachHang : arr) {
////            System.out.println(khachHang);
//        }

        //selectbyID
//        KhachHang kh1 = new KhachHang("KH02", "" , "", "", "", "", "", "", null, "", "", false);
//        KhachHang f_kh = dao.selectById(kh1);
//        System.out.println("Khách hàng cần in: " +f_kh);

        //insert
        KhachHang kh0 = new KhachHang("KH04", "khachhang4", "khachhang4", "Tran Dương D", "Nam", "Hà Nam", "Hà Nam", "Hà Nội", new Date(2002 - 1900, 05, 11), "1597534682", "khachhang4@gmail.com", false);
        dao.insert(kh0);

        //insertAll
//        KhachHang kh0 = new KhachHang("KH04", "khachhang4" , "khachhang4", "Tran Dương D", "Nam", "Hà Nam", "Hà Nam", "Hà Nội", new Date(2002-1900, 05, 11), "1597534682", "khachhang4@gmail.com", false);
//        KhachHang kh1 = new KhachHang("KH05", "khachhang5" , "khachhang5", "Quách Xuân Trường", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2004-1900, 10, 19), "0382770508", "quachtruong2k4@gmail.com", true);
//        ArrayList<KhachHang> day = new ArrayList<KhachHang>();
//        day.add(kh0);
//        day.add(kh1);
//        dao.insertAll(day);

        //delete
//        dao.delete(new KhachHang("KH04","" , "", "", "", "", "", "", null, "", "", false));

        //update
        KhachHang kh1 = new KhachHang("KH05", "khachhang5", "khachhang5", "Quách Xuân Trường", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2004 - 1900, 10, 19), "0382770508", "anh369255@gmail.com", false);
        dao.update(kh1);
    }
}
