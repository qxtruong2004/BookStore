package database;

import Model.ChiTietDonHang;
import Model.DonHang;
import Model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {
    private ArrayList<ChiTietDonHang> data = new ArrayList<ChiTietDonHang>();

    @Override
    public ArrayList<ChiTietDonHang> selectAll() {
        ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM chitietdonhang";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String maDonHang = rs.getString("madonhang");
                String maSanPham = rs.getString("masanpham");
                int soLuong = rs.getInt("soluong");
                double giaBia = rs.getDouble("giabia");
                double giamGia = rs.getDouble("giamgia");
                double giaBan = rs.getDouble("giaban");
                double VAT = rs.getDouble("vat");
                double tongThanhTien = rs.getDouble("tongthanhtien");

                DonHang dh = new DonHangDAO().selectById(new DonHang(maDonHang, null, "", "", "", "", "", 0, 0, null, null));
                SanPham sp = new SanPhamDAO().selectById(new SanPham(maSanPham, "", null, 0, 0, 0, 0, 0, null, "", ""));

                ChiTietDonHang ctdh = new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, giaBia, giamGia, giaBan, VAT, tongThanhTien);
                ketQua.add(ctdh);
            }

            // Bước 5:
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ChiTietDonHang selectById(ChiTietDonHang t) {
        ChiTietDonHang ketQua = null;
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "SELECT * FROM chitietdonhang WHERE machitietdonhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String maDonHang = rs.getString("madonhang");
                String maSanPham = rs.getString("masanpham");
                int soLuong = rs.getInt("soluong");
                double giaBia = rs.getDouble("giabia");
                double giamGia = rs.getDouble("giamgia");
                double giaBan = rs.getDouble("giaban");
                double VAT = rs.getDouble("vat");
                double tongThanhTien = rs.getDouble("tongthanhtien");

                DonHang dh = new DonHangDAO().selectById(new DonHang(maDonHang, null, "", "", "", "", "", 0, 0, null, null));
                SanPham sp = new SanPhamDAO().selectById(new SanPham(maSanPham, "", null, 0, 0, 0, 0, 0, null, "", ""));

                ketQua = new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, giaBia, giamGia, giaBan, VAT, tongThanhTien);
                break;
            }
            // Bước 5:
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO chitietdonhang (machitietdonhang, madonhang, masanpham, soluong, giaban,giamgia,giabia,vat,tongthanhtien) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());
            st.setString(2, t.getDonHang().getMaDonHang());
            st.setString(3, t.getSanPham().getMaSanPham());
            st.setInt(4, t.getSoLuong());
            st.setDouble(5, t.getGiaBan());
            st.setDouble(7, t.getGiamGia());
            st.setDouble(6, t.getGiaBia());
            st.setDouble(8, t.getVAT());
            st.setDouble(9, t.getTongThanhTien());
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
    public int insertAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.insert(ChiTietDonHang);
        }
        return dem;
    }

    @Override
    public int delete(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from chitietdonhang " + " WHERE machitietdonhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

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
    public int deleteAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.delete(ChiTietDonHang);
        }
        return dem;
    }

    public int deleteAll(DonHang dh) {
        int dem = 0;
        for (ChiTietDonHang chiTietChiTietDonHang : data) {
            if (chiTietChiTietDonHang.getDonHang().equals(dh)) {
                this.delete(chiTietChiTietDonHang);
            }
        }
        return dem;
    }

    @Override
    public int update(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBC_Util.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE chitietdonhang SET madonhang=?, masanpham=?, soluong=?, giaban=?, giamgia=?, giabia=?, vat=?, tongthanhtien=?"
                    + " WHERE machitietdonhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDonHang().getMaDonHang());
            st.setString(2, t.getSanPham().getMaSanPham());
            st.setInt(4, t.getSoLuong());
            st.setDouble(5, t.getGiaBan());
            st.setDouble(7, t.getGiamGia());
            st.setDouble(6, t.getGiaBia());
            st.setDouble(8, t.getVAT());
            st.setDouble(9, t.getTongThanhTien());
            st.setString(9, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL

            System.out.println(sql);
            ketQua = st.executeUpdate();

            JDBC_Util.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }
}
