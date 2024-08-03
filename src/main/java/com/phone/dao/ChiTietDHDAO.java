/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.dao;

import com.phone.entity.ChiTietDonHang;
import com.phone.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
public class ChiTietDHDAO extends QUANLYCUAHANGDAO<ChiTietDonHang, Integer> {

	final String INSERT_CTDH_SQL = "INSERT INTO ChiTietDonHang (order_detail_id, maDH, MaSanPham, soluongSP, giaTriDH, maNV) VALUES (?, ?, ?, ?, ?, ?)";
	final String UPDATE_CTDH_SQL = "UPDATE ChiTietDonHang SET maDH = ?, MaSanPham = ?, soluongSP = ?, giaTriDH = ?, maNV = ? WHERE order_detail_id = ?";
	final String DELETE_CTDH_SQL = "DELETE FROM ChiTietDonHang WHERE order_detail_id = ?";
	final String SELECT_ALL_CTDH = "SELECT * FROM ChiTietDonHang";
	final String SELECT_BY_ID_CTDH = "SELECT * FROM ChiTietDonHang WHERE order_detail_id = ?";

	@Override
	public void insert(ChiTietDonHang entity) {
		jdbcHelper.update(INSERT_CTDH_SQL, entity.getMaChiTietDH(), entity.getMaDH(), entity.getMaSP(), entity.getSoLuongSP(), entity.getGiatriDH(), entity.getMaNV());
	}

	@Override
	public void update(ChiTietDonHang entity) {
		jdbcHelper.update(UPDATE_CTDH_SQL, entity.getMaDH(), entity.getMaSP(), entity.getSoLuongSP(), entity.getGiatriDH(), entity.getMaNV(), entity.getMaChiTietDH());
	}

	@Override
	public void delete(String id) {
		jdbcHelper.update(DELETE_CTDH_SQL, id);
	}

	@Override
	public List<ChiTietDonHang> selectAll() {
		return selectBySql(SELECT_ALL_CTDH);
	}

	@Override
	public ChiTietDonHang selectById(Integer id) {
		List <ChiTietDonHang> list = selectBySql(SELECT_BY_ID_CTDH, id);
		if (list.isEmpty()) {
			return null;
			
		}
		return list.get(0);
	}
	

	@Override
	public List<ChiTietDonHang> selectBySql(String sql, Object... args) {
		List <ChiTietDonHang> list = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.query(sql, args);
			while (rs.next()){
				ChiTietDonHang entity = new ChiTietDonHang();
				entity.setMaChiTietDH(rs.getInt("order_detail_id"));
				entity.setMaDH(rs.getInt("maDH"));
				entity.setMaSP(rs.getString("MaSanPhaml"));
				entity.setSoLuongSP(rs.getInt("soluongSP"));
				entity.setGiatriDH(rs.getString("giaTriDH"));
				entity.setMaNV(rs.getString("maNV"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
