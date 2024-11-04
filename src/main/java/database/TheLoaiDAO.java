package database;

import Model.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TheLoaiDAO implements DAOInterface<TheLoai> {

    @Override
    public ArrayList<TheLoai> selectAll() {
        ArrayList<TheLoai> ketQua = new ArrayList<TheLoai>();
        try {
            //b1. Tạo kết nối tới csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tạo ra đối tượng prepareStatement
            String sql = "select * from theloai";
            PreparedStatement ps = con.prepareStatement(sql);

            //b3. thuc thi cau lenh
            System.out.println("Đang thực thi câu lệnh" + sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTheLoai = rs.getString("matheloai");
                String tenTheLoai = rs.getString("tentheloai");

                TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
                ketQua.add(theLoai);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public TheLoai selectById(TheLoai theLoai) {
        TheLoai ketQua = new TheLoai();
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "select * from theloai where matheloai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, theLoai.getMaTheLoai());

            System.out.println("Đang thực hiện câu lệnh" + sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTheLoai = rs.getString("matheloai");
                String tenTheLoai = rs.getString("tentheloai");
                ketQua = new TheLoai(maTheLoai, tenTheLoai);
                break;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(TheLoai theLoai) {
        int dem = 0;
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "insert into theloai(matheloai, tentheloai) value(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, theLoai.getMaTheLoai());
            ps.setString(2, theLoai.getTenTheLoai());

            System.out.println("Đang thực hiện câu lệnh" + sql);
            dem = ps.executeUpdate();
            System.out.println("Có " + dem + " dòng bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }

    @Override
    public int insertAll(ArrayList<TheLoai> arr) {
        int dem = 0;
        for (TheLoai t : arr) {
            dem += this.insert(t);
        }
        return dem;
    }

    @Override
    public int delete(TheLoai theLoai) {
        int dem = 0;
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "delete from theloai where matheloai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, theLoai.getMaTheLoai());

            System.out.println("Đang thực hiện câu lệnh" + sql);
            dem = ps.executeUpdate();
            System.out.println("Có " + dem + " câu lenh bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }

    @Override
    public int deleteAll(ArrayList<TheLoai> arr) {
        int dem = 0;
        for (TheLoai tl : arr) {
            dem += this.delete(tl);
        }
        return dem;
    }

    @Override
    public int update(TheLoai theLoai) {
        int dem = 0;
        try {
            Connection con = JDBC_Util.getConnection();

            String sql = "update theloai set tentheloai = ? where matheloai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, theLoai.getTenTheLoai());
            ps.setString(2, theLoai.getMaTheLoai());

            System.out.println("Đang thực hiện câu lệnh" + sql);
            dem = ps.executeUpdate();
            System.out.println("Có " + dem + " câu lenh bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        TheLoaiDAO tldao = new TheLoaiDAO();
        TheLoai tl = new TheLoai("TL04", "Sách thủ công");
        tldao.update(tl);
    }
}
