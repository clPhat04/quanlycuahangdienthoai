/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.dao;

import com.phone.entity.KhachHang;
import com.phone.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
public class KhachHangDAO extends QUANLYCUAHANGDAO<KhachHang, String> {

//	final String INSERT_SQL = "INSERT INTO KhachHang (maKH, hotenKH, sanpham, soDT, diaChi) VALUES (?, ?, ?, ?, ?)";
//	final String UPDATE_SQL = "UPDATE KhachHang SET hotenKH = ?, sanpham = ?, soDT = ?, diaChi = ? WHERE maKH = ?";
//	final String DELETE_SQL = "DELETE FROM KhachHang WHERE maKH = ?";
//	final String SELECT_ALL = "SELECT * FROM KhachHang";
//	final String SELECT_BY_ID_ = "SELECT * FROM KhachHang WHERE maKH = ?";
//
//	@Override
//	public void insert(KhachHang entity) {
//		jdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getHoTen(), entity.getSanpham(), entity.getSoDT(), entity.getDiachi());
//	}
//
//	@Override
//	public void update(KhachHang entity) {
//		jdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getSanpham(), entity.getSoDT(), entity.getDiachi(), entity.getMaKH());
//	}
//
//	@Override
//	public void delete(String id) {
//		jdbcHelper.update(DELETE_SQL, id);
//	}
//
//	@Override
//	public List<KhachHang> selectAll() {
//		return selectBySql(SELECT_ALL);
//	}
//
//	@Override
//	public KhachHang selectById(String id) {
//		List<KhachHang> list = selectBySql(SELECT_BY_ID_, id);
//		if (list.isEmpty()) {
//			return null;
//
//		}
//		return list.get(0);
//	}
//
//	@Override
//	public List<KhachHang> selectBySql(String sql, Object... args) {
//		List<KhachHang> list = new ArrayList<>();
//		try {
//			ResultSet rs = jdbcHelper.query(sql, args);
//			while (rs.next()) {
//				KhachHang entity = new KhachHang();
//				entity.setMaKH(rs.getString("maKH"));
//				entity.setHoTen(rs.getString("hotenKH"));
//				entity.setSanpham(rs.getString("sanpham"));
//				entity.setSoDT(rs.getString("soDT"));
//				entity.setDiachi(rs.getString("diaChi"));
//				list.add(entity);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
//
	final String INSERT_SQL = "INSERT INTO KhachHang (maKH, hotenKH, sanpham, soDT, diaChi) VALUES (?, ?, ?, ?, ?)";
	final String UPDATE_SQL = "UPDATE KhachHang SET hotenKH = ?, sanpham = ?, soDT = ?, diaChi = ? WHERE maKH = ?";
	final String DELETE_SQL = "DELETE FROM KhachHang WHERE maKH = ?";
	final String SELECT_ALL = "SELECT * FROM KhachHang";
	final String SELECT_BY_ID_ = "SELECT * FROM KhachHang WHERE maKH = ?";

	@Override
	public void insert(KhachHang entity) {
		jdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getHoTen(), entity.getSanpham(), entity.getSoDT(), entity.getDiachi());
	}

	@Override
	public void update(KhachHang entity) {
jdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getSanpham(), entity.getSoDT(), entity.getDiachi(), entity.getMaKH());
	}

	@Override
	public void delete(String id) {
jdbcHelper.update(DELETE_SQL, id);
	}

	@Override
	public List<KhachHang> selectAll() {
return selectBySql(SELECT_ALL);
	}

	@Override
	public KhachHang selectById(String id) {
List <KhachHang> list = selectBySql(SELECT_BY_ID_, id);
		if (list.isEmpty()) {
			return null;
			
		}
		return list.get(0);
	}

	@Override
	public List<KhachHang> selectBySql(String sql, Object... args) {
List <KhachHang> list = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.query(sql, args);
			while (rs.next()){
				KhachHang entity = new KhachHang();
				entity.setMaKH(rs.getString("maKH"));
				entity.setHoTen(rs.getString("hotenKH"));
				entity.setSanpham(rs.getString("sanpham"));
				entity.setSoDT(rs.getString("soDT"));
				entity.setDiachi(rs.getString("diaChi"));
				list.add(entity);
                                
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}

