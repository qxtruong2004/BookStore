<%-- Created by IntelliJ IDEA. User: quachtruong Date: 10/31/2024 Time: 9:02 PM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Q.Truong's Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>
    <style>
        .red {
            color: red;
        }
    </style>
</head>
<body>
<%
    String baoLoi = request.getAttribute("baoLoi")+"";
    baoLoi = (baoLoi.equals("null"))?"":baoLoi;

    String tenDangNhap= request.getAttribute("tenDangNhap")+"";
    tenDangNhap = (tenDangNhap.equals("null"))?"":tenDangNhap;

    String hoVaTen= request.getAttribute("hoVaTen")+"";
    hoVaTen = (hoVaTen.equals("null"))?"":hoVaTen;

    String gioiTinh= request.getAttribute("gioiTinh")+"";
    gioiTinh = (gioiTinh.equals("null"))?"":gioiTinh;

    String ngaySinh= request.getAttribute("ngaySinh")+"";
    ngaySinh = (ngaySinh.equals("null"))?"":ngaySinh;

    String diaChiKhachHang= request.getAttribute("diaChiKhachHang")+"";
    diaChiKhachHang = (diaChiKhachHang.equals("null"))?"":diaChiKhachHang;

    String diaChiMuaHang= request.getAttribute("diaChiMuaHang")+"";
    diaChiMuaHang = (diaChiMuaHang.equals("null"))?"":diaChiMuaHang;

    String diaChiNhanHang= request.getAttribute("diaChiNhanHang")+"";
    diaChiNhanHang = (diaChiNhanHang.equals("null"))?"":diaChiNhanHang;

    String soDienThoai= request.getAttribute("soDienThoai")+"";
    soDienThoai = (soDienThoai.equals("null"))?"":soDienThoai;

    String email= request.getAttribute("email")+"";
    email = (email.equals("null"))?"":email;

    String dongYNhanMail= request.getAttribute("dongYNhanMail")+"";
    dongYNhanMail = (dongYNhanMail.equals("null"))?"":dongYNhanMail;
%>
<div class="container" style="margin-top: 5px">
    <div class="title_register" style="text-align: center">
        <h1>ĐĂNG KÍ TÀI KHOẢN</h1>
    </div>
    <form action="login-web" method="post">
        <div class="row">
            <div class="col-sm-6">
                <h3>Tài khoản</h3>
                <div id="baoLoi">
                    <%=baoLoi %>
                </div>
                <div class="mb-3">
                    <label for="tenDangNhap" class="form-label">Tên đăng nhập <span class="red">*</span></label>
                    <input type="text" class="form-control" id="tenDangNhap" placeholder="Nhập tên đăng nhập"
                           name="tenDangNhap" value="<%=tenDangNhap%>" required>
                </div>

                <div class="mb-3">
                    <label for="matKhau" class="form-label">Mật khẩu <span class="red">*</span></label>
                    <input type="password" class="form-control" id="matKhau" placeholder="Nhập mật khẩu của bạn"
                           name="matKhau" required>
                </div>

                <div class="mb-3">
                    <label for="matKhauNhapLai" class="form-label">Nhập lại mật khẩu <span class="red">*</span></label>
                    <input type="password" class="form-control" id="matKhauNhapLai" placeholder="Nhập lại mật khẩu"
                           name="matKhauNhapLai" oninput="kiemTraMatKhau(); kichhoatdangki()" required> <span
                        id="e_pass" style="color: red"></span>
                </div>

                <h3>Thông tin người dùng</h3>
                <div class="mb-3">
                    <label for="hoVaTen" class="form-label">Họ và tên</label>
                    <input type="text" class="form-control" id="hoVaTen" placeholder="Nhập họ và tên của bạn"
                           name="hoVaTen" <%=hoVaTen%>>
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label">Giới Tính</label>
                    <select class="form-control" name="gioiTinh" id="gioiTinh">
                        <option></option>
                        <option value="Nam" <%=(gioiTinh.equals("Nam"))?"selected='selected'":"" %>>Nam</option>
                        <option value="Nữ" <%=(gioiTinh.equals("Nữ"))?"selected='selected'":"" %>>Nữ</option>
                        <option value="Khác" <%=(gioiTinh.equals("Khác"))?"selected='selected'":"" %>>Khác</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ngaySinh" class="form-label">Ngày Sinh</label>
                    <input type="date" class="form-control" id="ngaySinh" name="ngaySinh"<%=ngaySinh%> value="">
                </div>
            </div>
            <div class="col-sm-6">
                <h3>Địa chỉ</h3>
                <div class="mb-3">
                    <label for="diaChiKhachHang" class="form-label">Địa chỉ khách hàng</label>
                    <input type="text" class="form-control" id="diaChiKhachHang" name="diaChiKhachHang" <%=diaChiKhachHang%>>
                </div>
                <div class="mb-3">
                    <label for="diaChiMuaHang" class="form-label">Địa chỉ mua hàng</label>
                    <input type="text" class="form-control" id="diaChiMuaHang" name="diaChiMuaHang" <%=diaChiMuaHang%>>
                </div>
                <div class="mb-3">
                    <label for="diaChiNhanHang" class="form-label">Địa chỉ nhận hàng</label>
                    <input type="text" class="form-control" id="diaChiNhanHang" name="diaChiNhanHang" <%=diaChiNhanHang%>>
                </div>
                <div class="mb-3">
                    <label for="soDienThoai" class="form-label">Số điện thoại</label>
                    <input type="tel" class="form-control" id="soDienThoai" name="soDienThoai"
                           oninput="kiemTraSoDienThoai(); kichhoatdangki()" required <%=soDienThoai%>> <span id="e_tel"
                                                                                            style="color: red;"></span>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" <%=email%>>
                </div>

                <div class="mb-3">
                    <label for="dongYDieuKhoan" class="form-label">Đồng ý với <a href="#">điều khoản của công
                        ty</a><span class="red">*</span></label>
                    <input type="checkbox" class="form-check-input" id="dongYDieuKhoan" name="dongYDieuKhoan "
                           style="border-color: black"
                           onchange="kichhoatdangki()">
                </div>
                <div class="mb-3">
                    <label for="dongYNhanEmail" class="form-label">Đồng ý nhận email thông báo</label>
                    <input type="checkbox" class="form-check-input" id="dongYNhanEmail" name="dongYNhanEmail"
                           style="border-color: black">
                </div>
                <input class="btn btn-primary form-control" type="submit" value="Đăng ký" id="submit" disabled>
            </div>
        </div>
    </form>
</div>
</body>
<%--Script--%>
<script>
    function kiemTraSoDienThoai() {
        let soDienThoai = document.getElementById("soDienThoai").value.trim();
        let regex = /^[0-9]+$/;

        if (!regex.test(soDienThoai)) {
            document.getElementById("e_tel").innerHTML = "Số điện thoại chỉ được phép chứa số"
            document.getElementById("soDienThoai").style.borderColor = "red";
            return false;
        } else if (soDienThoai.length != 10) {
            document.getElementById("e_tel").innerHTML = "Số điện thoại có độ dài phải = 10"
            document.getElementById("soDienThoai").style.borderColor = "red";
            return false;
        } else {
            document.getElementById("e_tel").innerHTML = ""; // Xóa thông báo lỗi nếu kiểm tra thành công
            document.getElementById("soDienThoai").style.borderColor = "";
            return true;
        }
    }

    function kiemTraMatKhau() {
        let matKhau = document.getElementById("matKhau").value;
        let matKhauNhapLai = document.getElementById("matKhauNhapLai").value;

        if (matKhau !== matKhauNhapLai) {
            document.getElementById("matKhauNhapLai").style.borderColor = "red";
            document.getElementById("e_pass").innerHTML = "Mật khẩu không trùng khớp";
            return false;
        } else {
            document.getElementById("matKhauNhapLai").style.borderColor = "";
            document.getElementById("e_pass").innerHTML = "";
            return true;
        }
    }

    function kichhoatdangki() {
        const checkbox1 = document.getElementById('dongYDieuKhoan').checked;
        if (kiemTraSoDienThoai() && kiemTraMatKhau() && checkbox1) {
            document.getElementById('submit').disabled = false;
        } else {
            document.getElementById('submit').disabled = true;
        }
    }

    // function trangchu() {
    //     if(kiemTraMatKhau() && kiemTraSoDienThoai() ){
    //         alert("Đăng kí tài khoản thành công");
    //         setTimeout(function () {
    //             window.location.href = "TrangChu.jsp";
    //         }, 1000);
    //         return true
    //     }
    //     return false
    // }

</script>
</html>

