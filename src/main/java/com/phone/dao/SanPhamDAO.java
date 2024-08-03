/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.DAO;

import com.phone.dao.QUANLYCUAHANGDAO;
import com.phone.entity.SanPham;
import com.phone.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
public class SanPhamDAO extends QUANLYCUAHANGDAO<SanPham, String> {

	final String INSERT_SP_SQL = "INSERT INTO SanPham (maSP, tenSP, giaTien, soluonghangtrongkho) VALUES (?, ?, ?, ?)";
	final String UPDATE_SP_SQL = "UPDATE SanPham SET tenSP = ?, giaTien = ?, soluonghangtrongkho = ? WHERE maSP = ?";
	final String DELETE_SP_SQL = "DELETE FROM SanPham WHERE maSP = ?";
	final String SELECT_ALL_SP = "SELECT * FROM SanPham";
	final String SELECT_BY_ID_SP = "SELECT * FROM SanPham WHERE maSP = ?";

	@Override
	public void insert(SanPham entity) {
		jdbcHelper.update(INSERT_SP_SQL, entity.getMaSP(), entity.getTenSP(), entity.getGiaTien(),entity.getSoluongSP());
	}

	@Override
	public void update(SanPham entity) {
		jdbcHelper.update(UPDATE_SP_SQL, entity.getTenSP(), entity.getGiaTien(),entity.getSoluongSP(),entity.getMaSP());
	}

	@Override
	public void delete(String id) {
		jdbcHelper.update(DELETE_SP_SQL, id);
	}

	@Override
	public List<SanPham> selectAll() {
	return selectBySql(SELECT_ALL_SP);
	}

	@Override
	public SanPham selectById(String id) {
		List <SanPham> list = selectBySql(SELECT_BY_ID_SP, id);
		if (list.isEmpty()) {
			return null;
			
		}
		return list.get(0);
	}
	

	@Override
	public List<SanPham> selectBySql(String sql, Object... args) {
		List <SanPham> list = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.query(sql, args);
			while (rs.next()){
				SanPham entity = new SanPham();
				entity.setMaSP(rs.getString("maSP"));
				entity.setTenSP(rs.getString("tenSP"));
				entity.setGiaTien(rs.getDouble("giaTien"));
				entity.setSoluongSP(rs.getInt("soluonghangtrongkho"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
