/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.entity;

/**
 *
 * @author PhatLe
 */
public class NhanVien {

	private String maNV;
	private String matKhau;
	private String hoTen;
	private boolean VaiTro;

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien(String maNV, String matKhau, String hoTen, boolean VaiTro) {
		this.maNV = maNV;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.VaiTro = VaiTro;
	}

	public NhanVien() {
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isVaiTro() {
		return VaiTro;
	}

	public void setVaiTro(boolean VaiTro) {
		this.VaiTro = VaiTro;
	}

	

	
}
