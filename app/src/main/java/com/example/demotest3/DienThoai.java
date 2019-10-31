package com.example.demotest3;

public class DienThoai {
    private String nhanHieu;
    private float gia;
    private int soLuong;

    public DienThoai(String nhanHieu, float gia, int soLuong) {
        this.nhanHieu = nhanHieu;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getNhanHieu() {
        return nhanHieu;
    }

    public void setNhanHieu(String nhanHieu) {
        this.nhanHieu = nhanHieu;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
