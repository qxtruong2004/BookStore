package Model;

import java.util.Objects;

public class TheLoai {
    private String maTheLoai;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(String maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheLoai)) return false;
        TheLoai theLoai = (TheLoai) o;
        return Objects.equals(maTheLoai, theLoai.maTheLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maTheLoai);
    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "maTheLoai='" + maTheLoai + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                '}';
    }
}
