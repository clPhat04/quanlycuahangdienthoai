/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.dao;

import com.phone.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
import com.phone.entity.NhanVien;

public class NhanVienDAO extends QUANLYCUAHANGDAO<NhanVien, String> {

	final String INSERT_NV_SQL = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
	final String UPDATE_NV_SQL = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, VaiTro = ? WHERE MaNV = ?";
	final String DELETE_NV_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
	final String SELECT_ALL_NV = "SELECT * FROM NhanVien";
	final String SELECT_BY_ID_NV = "SELECT * FROM NhanVien WHERE MaNV = ?";  //tại nó có khóa ngoại vs cái chi tiết đơn hàng. ko xóa đc 
	

	@Override
	public void insert(NhanVien entity) {
		jdbcHelper.update(INSERT_NV_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro());
	}

	@Override
	public void update(NhanVien entity) {
		jdbcHelper.update(UPDATE_NV_SQL, entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getMaNV());
	}

	@Override
	public void delete(String id) {
		jdbcHelper.update(DELETE_NV_SQL, id);
	}

	@Override
	public List<NhanVien> selectAll() {
		return selectBySql(SELECT_ALL_NV);
	}

	@Override
	public NhanVien selectById(String id) {
		List<NhanVien> list = selectBySql(SELECT_BY_ID_NV, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NhanVien> selectBySql(String sql, Object... args) {
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			ResultSet rs = jdbcHelper.query(sql, args);
			while (rs.next()) {
				NhanVien entity = new NhanVien();
				entity.setMaNV(rs.getString("MaNV"));
				entity.setMatKhau(rs.getString("MatKhau"));
				entity.setHoTen(rs.getString("HoTen"));
				entity.setVaiTro(rs.getBoolean("VaiTro"));
				list.add(entity);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

//    final String INSERT_SQL = "insert into NhanVien(MaNV, MatKhau, HoTen, VaiTro) values (?,?,?,?) ";
//    final String UPDATE_SQL = " update NhanVien set MatKhau = ?,  HoTen = ?, VaiTro =? where MaNV =?";
//    final String DELETE_SQL = "DELETE from NhanVien Where MaNV =?";
//    final String SELECT_ALL_SQL = "select * from NhanVien";
//    final String SELECT_BY_ID_SQL = "select * from NhanVien where MaNV = ?";
}
