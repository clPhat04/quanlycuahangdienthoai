
package com.phone.entity;

/**
 *
 * @author PhatLe
 */
public class SanPham {
	private String maSP;
	private String tenSP;
	private double giaTien;
	private double soluongSP;

	public SanPham(String maSP, String tenSP, double giaTien, double soluongSP) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaTien = giaTien;
		this.soluongSP = soluongSP;
	}

	public SanPham() {
	
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public double getSoluongSP() {
		return soluongSP;
	}

	public void setSoluongSP(double soluongSP) {
		this.soluongSP = soluongSP;
	}

	
	
	
}
