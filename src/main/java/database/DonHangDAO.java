package database;

import Model.DonHang;
import Model.KhachHang;
import database.KhachHangDAO;

import java.sql.*;
import java.util.ArrayList;

public class DonHangDAO implements DAOInterface<DonHang> {
    private ArrayList<DonHang> data = new ArrayList<DonHang>();

    @Override
    public ArrayList<DonHang> selectAll() {
        ArrayList<DonHang> ketqua = new ArrayList<DonHang>();
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "select * from donhang";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDonHang = rs.getString("madonhang");
                String maKhachHang = rs.getString("makhachhang");
                String diaChiNguoiMua = rs.getString("diachinguoimua");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String trangThai = rs.getString("trangthai");
                String hinhThucThanhToan = rs.getString("hinhthucthanhtoan");
                String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
                Double soTienDaThanhToan = rs.getDouble("sotiendathanhtoan");
                Double soTienConThieu = rs.getDouble("sotienconthieu");
                Date ngayDatHang = rs.getDate("ngaydathang");
                Date ngayGiaoHang = rs.getDate("ngaygiaohang");

                KhachHang kh1 = new KhachHang();
                kh1.setMaKhachHang(maKhachHang);

                KhachHang khachHang = new KhachHangDAO().selectById(kh1);
                DonHang dh = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

                ketqua.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public DonHang selectById(DonHang t) {
        DonHang ketQua = null;
        try {
            Connection con = JDBC_Util.getConnection();
            String sql = "SELECT * FROM donhang WHERE madonhang = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDonHang = rs.getString("madonhang");
                String maKhachHang = rs.getString("makhachhang");
                String diaChiNguoiMua = rs.getString("diachinguoimua");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String trangThai = rs.getString("trangthai");
                String hinhThucThanhToan = rs.getString("hinhthucthanhtoan");
                String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
                Double soTienDaThanhToan = rs.getDouble("sotiendathanhtoan");
                Double soTienConThieu = rs.getDouble("sotienconthieu");
                Date ngayDatHang = rs.getDate("ngaydathang");
                Date ngayGiaoHang = rs.getDate("ngaygiaohang");

                KhachHang khachHang = new KhachHangDAO()
                        .selectById(new KhachHang(maKhachHang, "", "", "", "", "", "", "", null, "", "", false));
                ketQua = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

                break;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(DonHang t) {
        int kq = 0;
        Connection con = JDBC_Util.getConnection();
        String sql = "INSERT INTO donhang(madonhang, makhachhang, diachinguoimua, diachinhanhang, trangthai, hinhthucthanhtoan, trangthaithanhtoan, sotiendathanhtoan, sotienconthieu, ngaydathang, ngaygiaohang)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            st.setString(2, t.getKhachHang().getMaKhachHang());
            st.setString(3, t.getDiaChiNguoiMua());
            st.setString(4, t.getDiaChiNhanHang());
            st.setString(5, t.getTrangThai());
            st.setString(6, t.getHinhThucThanhToan());
            st.setString(7, t.getTrangThaiThanhToan());
            st.setDouble(8, t.getSoTienDaThanhToan());
            st.setDouble(9, t.getSoTienConThieu());
            st.setDate(10, t.getNgayDatHang());
            st.setDate(11, t.getNgayGiaoHang());

            kq = st.executeUpdate();
            System.out.println("Có" + kq + " câu lệnh bị thay đổi");
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int insertAll(ArrayList<DonHang> arr) {
        int kq = 0;
        for (DonHang donHang : arr) {
            kq += this.insert(donHang);
        }
        return kq;
    }

    @Override
    public int delete(DonHang t) {
        int kq = 0;
        Connection con = JDBC_Util.getConnection();
        String sql = "DELETE FROM donhang WHERE madonhang = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            kq = st.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + kq + " câu lệnh bị thay đổi");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int deleteAll(ArrayList<DonHang> arr) {
        int kq = 0;
        for (DonHang t : arr) {
            kq += this.delete(t);
        }
        return kq;
    }

    @Override
    public int update(DonHang t) {
        int kq = 0;
        Connection con = JDBC_Util.getConnection();

        String sql = "UPDATE donhang" + " SET " + "makhachhang=?" + ", diachinguoimua=?" + ",diachinhanhang=?"
                + ",trangthai=?" + ",hinhthucthanhtoan=?" + ",trangthaithanhtoan = ?" + ",sotiendathanhtoan=?" + ",sotienconthieu=?" + ",ngaydathang=?"
                + ",ngaygiaohang=?" + " WHERE madonhang=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getKhachHang().getMaKhachHang());
            st.setString(2, t.getDiaChiNguoiMua());
            st.setString(3, t.getDiaChiNhanHang());
            st.setString(4, t.getTrangThai());
            st.setString(5, t.getHinhThucThanhToan());
            st.setString(6, t.getTrangThaiThanhToan());
            st.setDouble(7, t.getSoTienDaThanhToan());
            st.setDouble(8, t.getSoTienConThieu());
            st.setDate(9, t.getNgayDatHang());
            st.setDate(10, t.getNgayGiaoHang());
            ;
            st.setString(11, t.getMaDonHang());

            kq = st.executeUpdate();
            System.out.println("Đã thực hiện câu lệnh " + sql);
            System.out.println("Có " + kq + " câu lệnh bị thay đổi");

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static void main(String[] args) {
        DonHangDAO dao = new DonHangDAO();
        //selectAll
//        ArrayList<DonHang> arr = dao.selectAll();
//        for (DonHang donHang : arr) {
//            System.out.println(donHang);
//        }

        //selectbyID
        KhachHang kh1 = new KhachHang("KH05", "khachhang5", "khachhang5", "Quách Xuân Trường", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2004 - 1900, 10, 19), "0382770508", "quachtruong2k4@gmail.com", true);
        DonHang dh1 = new DonHang("DH05", kh1, "", "", "", "", "", 0, 0, null, null);
        DonHang f_dh = dao.selectById(dh1);
        System.out.println("Đơn hàng cần in: " + f_dh);

        //insert
//        KhachHang kh1 = new KhachHang("KH03", "khachhang3" , "khachhang3", "Lê Thị Quỳnh Như", "Nữ", "Thái Bình", "Hà Nội", "Hà Nội", new Date(2004-1900, 03, 18), "123456789", "nheinhei182@gmail.com", true);
//        DonHang dh1 = new DonHang("DHO4", kh1 , "Vĩnh Phúc", "Hà Nội", "Đang vận chuyển", "Chuyen Khoản", "Đã thanh toán", 119.191, 0, new Date(2024-1900, 9, 27), new Date(2024-1900, 9, 30));
//        dao.insert(dh1);

        //insertAll
        //     KhachHang kh0 = new KhachHang("KH04", "khachhang4" , "khachhang4", "Tran Dương D", "Nam", "Hà Nam", "Hà Nam", "Hà Nội", new Date(2002-1900, 05, 11), "1597534682", "khachhang4@gmail.com", false);
        //KhachHang kh1 = new KhachHang("KH05", "khachhang5" , "khachhang5", "Quách Xuân Trường", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2004-1900, 10, 19), "0382770508", "quachtruong2k4@gmail.com", true);

        //        ArrayList<DonHang> day = new ArrayList<DonHang>();
//        DonHang dh1 = new DonHang("DHO5", kh1 , "Hà Nội", "Hà Nội", "Đang vận chuyển", "COD", "Chưa thanh toán", 0, 300000, new Date(2024-1900, 9, 26), new Date(2024-1900, 9, 28));
//        DonHang dh2 = new DonHang("DHO6", kh0 , "TP Hồ Chí Minh", "Hà Nội", "Chờ lấy hàng", "Chuyển khoản", "Đã thanh toán", 600000, 0, new Date(2024-1900, 9, 28), new Date(2024-1900, 9, 30));
//        day.add(dh1);
//        day.add(dh2);
//        dao.insertAll(day);

        //delete
        //dao.delete(new DonHang("DHO6", kh0 , "TP Hồ Chí Minh", "Hà Nội", "Chờ lấy hàng", "Chuyển khoản", "Đã thanh toán", 600000, 0, new Date(2024-1900, 9, 28), new Date(2024-1900, 9, 30)));

        //update
//        DonHang dh1 = new DonHang("DHO5", kh1 , "Hà Nội", "Thái Bình", "Đang vận chuyển", "COD", "Chưa thanh toán", 0, 300000, new Date(2024-1900, 9, 26), new Date(2024-1900, 9, 28));
////
//        dao.update(dh1);
    }
}
