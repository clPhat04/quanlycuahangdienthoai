
package com.phone.entity;

import java.awt.event.WindowAdapter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author PhatLe
 */
public class DonHangg {
	private int maDH;
	private String MaKH;
	private Date ngayDH;
	private String diachiGH;
	private String TrangThai;

	public DonHangg(int maDH, String MaKH, Date ngayDH, String diachiGH, String TrangThai) {
		this.maDH = maDH;
		this.MaKH = MaKH;
		this.ngayDH = ngayDH;
		this.diachiGH = diachiGH;
		this.TrangThai = TrangThai;
	}

	

	public DonHangg() {
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String TrangThai) {
		this.TrangThai = TrangThai;
	}



	
	public int getMaDH() {
		return maDH;
	}

	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String MaKH) {
		this.MaKH = MaKH;
	}

	public Date getNgayDH() {
		return ngayDH;
	}

	public void setNgayDH(Date ngayDH) {
		this.ngayDH = ngayDH;
	}

	public String getDiachiGH() {
		return diachiGH;
	}

	public void setDiachiGH(String diachiGH) {
		this.diachiGH = diachiGH;
	}
	
	
}
