/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.entity;

/**
 *
 * @author PhatLe
 */
public class KhachHang {
	private String  maKH;
	private String hoTen;
	private String sanpham;
	private String SoDT;
	private String diachi;

	public KhachHang() {

	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSanpham() {
		return sanpham;
	}

	public void setSanpham(String sanpham) {
		this.sanpham = sanpham;
	}

	public String getSoDT() {
		return SoDT;
	}

	public void setSoDT(String SoDT) {
		this.SoDT = SoDT;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public KhachHang(String maKH, String hoTen, String sanpham, String SoDT, String diachi) {
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sanpham = sanpham;
		this.SoDT = SoDT;
		this.diachi = diachi;
	}

	

}
	