package database;

import Model.TacGia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TacGiaDAO implements DAOInterface<TacGia> {

    @Override
    public ArrayList<TacGia> selectAll() {
        ArrayList<TacGia> ketQua = new ArrayList<TacGia>();
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "select * from tacgia";
            PreparedStatement ps = con.prepareStatement(sql);

            //b3. thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            //b4
            while (rs.next()) {
                String maTacGia = rs.getString("matacgia");
                String hoVaTen = rs.getString("hovaten");
                Date ngaySinh = rs.getDate("ngaysinh");
                String tieuSu = rs.getString("tieusu");

                TacGia tacGia = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
                ketQua.add(tacGia);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public TacGia selectById(TacGia tacGia) {
        TacGia ketQua = null;
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "select *from tacgia where matacgia = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //thiết lập tham so cho caulenh sql
            ps.setString(1, tacGia.getMaTacGia());


            //b3. thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            //b4
            while (rs.next()) {
                String maTacGia = rs.getString("matacgia");
                String hoVaTen = rs.getString("hovaten");
                Date ngaySinh = rs.getDate("ngaysinh");
                String tieuSu = rs.getString("tieusu");

                ketQua = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
                break;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(TacGia tacGia) {
        int dem = 0;
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "insert into tacgia(matacgia, hovaten, ngaysinh, tieusu) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            //thiết lập tham so cho caulenh sql
            ps.setString(1, tacGia.getMaTacGia());
            ps.setString(2, tacGia.getHoVaTen());
            ps.setDate(3, tacGia.getNgaySinh());
            ps.setString(4, tacGia.getTieuSu());

            //b3. ket qua thuc thi cau lenh sql
            dem = ps.executeUpdate();

            //b4
            System.out.println("bạn đã thực thi câu lênh: " + sql);
            System.out.println("Có " + dem + " dòng bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }

    @Override
    public int insertAll(ArrayList<TacGia> arr) {
        int dem = 0;
        for (TacGia t : arr) {
            dem += this.insert(t);
        }
        return dem;
    }

    @Override
    public int delete(TacGia tacGia) {
        int dem = 0;
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "delete from tacgia where matacgia = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //thiết lập tham so cho caulenh sql
            ps.setString(1, tacGia.getMaTacGia());


            //b3. ket qua thuc thi cau lenh sql
            dem = ps.executeUpdate();

            //b4
            System.out.println("bạn đã thực thi câu lênh: " + sql);
            System.out.println("Có " + dem + " dòng bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }

    @Override
    public int deleteAll(ArrayList<TacGia> arr) {
        int dem = 0;
        for (TacGia t : arr) {
            dem += this.delete(t);
        }
        return dem;
    }

    @Override
    public int update(TacGia tacGia) {
        int dem = 0;
        try {
            //b1. tạo kết nối đến csdl
            Connection con = JDBC_Util.getConnection();

            //b2. tao ra đối tượng statement
            String sql = "update tacgia set hovaten= ?, ngaysinh = ?, tieusu = ? where matacgia = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            //thiết lập tham so cho caulenh sql
            ps.setString(1, tacGia.getHoVaTen());
            ps.setDate(2, tacGia.getNgaySinh());
            ps.setString(3, tacGia.getTieuSu());
            ps.setString(4, tacGia.getMaTacGia());

            //b3. ket qua thuc thi cau lenh sql
            dem = ps.executeUpdate();

            //b4
            System.out.println("bạn đã thực thi câu lênh: " + sql);
            System.out.println("Có " + dem + " dòng bị thay đổi");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }

    public static void main(String[] args) {
        TacGiaDAO dao = new TacGiaDAO();
//        ArrayList<TacGia> ketQua = dao.selectAll();
//        for(TacGia tacGia : ketQua){
//            System.out.println(tacGia.toString());
//        }
//        TacGia tg = dao.selectById(new TacGia("TG02", "", null, ""));
//        System.out.println(tg);
        TacGia u_tacGia = new TacGia("TG02", "Lê Thị Quỳnh Như", new Date(2004 + -1900, 01, 11), "Như cười xinh lắm luôn í");
        dao.update(u_tacGia);


    }

}
