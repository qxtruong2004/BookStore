package Controller;

import Model.KhachHang;
import database.KhachHangDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Random;

@WebServlet(urlPatterns = {"/login-web"})
public class TrangChuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");
        String matKhauNhapNhapLai = request.getParameter("matKhauNhapLai");
        String hoVaTen = request.getParameter("hoVaTen");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChiKhachHang = request.getParameter("diaChiKhachHang");
        String diaChiMuaHang = request.getParameter("diaChiMuaHang");
        String diaChiNhanHang = request.getParameter("diaChiNhanHang");
        String soDienThoai = request.getParameter("soDienThoai");
        String email = request.getParameter("email");
        String dongYNhanEmail = request.getParameter("dongYNhanEmail");

        request.setAttribute("tenDangNhap", tenDangNhap);
        request.setAttribute("matKhau", matKhau);
        request.setAttribute("hoVaTen", hoVaTen);
        request.setAttribute("gioiTinh", gioiTinh);
        request.setAttribute("ngaySinh", ngaySinh);
        request.setAttribute("diaChiKhachHang", diaChiKhachHang);
        request.setAttribute("diaChiMuaHang", diaChiMuaHang);
        request.setAttribute("diaChiNhanHang", diaChiNhanHang);
        request.setAttribute("soDienThoai", soDienThoai);
        request.setAttribute("email", email);
        request.setAttribute("dongYNhanMail", dongYNhanEmail);

        String baoLoi = "";
        String url = "";

        KhachHangDAO khachHangDAO = new KhachHangDAO();
        //kiểm tra ten tài khoản đã có trong csdl chưa
        if (khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
            baoLoi += "Tên đăng nhập đã tồn tại";
        }
        request.setAttribute("baoLoi", baoLoi);

        //tạo đươg dẫn url
        if (baoLoi.length() > 0) {
            url = "/register.jsp";
        } else {
            Random rd = new Random();
            String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + ""; //tạo mã kh tự động
            KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), soDienThoai, email, dongYNhanEmail != null);
            khachHangDAO.insert(kh);
            url = "/TrangChu.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);

        // Hiển thị thông báo và chuyển hướng về TrangChu.jsp
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().write("<script>alert('Đăng kí tài khoản thành công!'); window.location.href = 'TrangChu.jsp';</script>");
    }

}
