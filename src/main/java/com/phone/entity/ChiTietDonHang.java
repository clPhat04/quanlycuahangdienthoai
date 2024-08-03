package com.phone.entity;

/**
 *
 * @author PhatLe
 */
public class ChiTietDonHang {
	private int maChiTietDH;
	private int maDH;
	private String MaSP;
	private int SoLuongSP;
	private String GiatriDH;
	private String MaNV;

	public ChiTietDonHang(int maChiTietDH, int maDH, String MaSP, int SoLuongSP, String GiatriDH, String MaNV) {
		this.maChiTietDH = maChiTietDH;
		this.maDH = maDH;
		this.MaSP = MaSP;
		this.SoLuongSP = SoLuongSP;
		this.GiatriDH = GiatriDH;
		this.MaNV = MaNV;
	}

	public ChiTietDonHang() {
	}
	

	public int getMaChiTietDH() {
		return maChiTietDH;
	}

	public void setMaChiTietDH(int maChiTietDH) {
		this.maChiTietDH = maChiTietDH;
	}

	public int getMaDH() {
		return maDH;
	}

	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}

	public String getMaSP() {
		return MaSP;
	}

	public void setMaSP(String MaSP) {
		this.MaSP = MaSP;
	}

	public int getSoLuongSP() {
		return SoLuongSP;
	}

	public void setSoLuongSP(int SoLuongSP) {
		this.SoLuongSP = SoLuongSP;
	}

	public String getGiatriDH() {
		return GiatriDH;
	}

	public void setGiatriDH(String GiatriDH) {
		this.GiatriDH = GiatriDH;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String MaNV) {
		this.MaNV = MaNV;
	}
	
	
}
