
package com.phone.dao;

import com.phone.entity.DonHangg;
import com.phone.utils.jdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
public class DonHangDAO extends  QUANLYCUAHANGDAO<DonHangg, Integer>{
	final String INSERT_SQL= "INSERT INTO DonHang (maDH, maKhachHang, ngayDat, diaChiGiaoHang, Trangthai) Values (?,?,?,?,?) ";
	final String UPDATE_SQL = "Update DonHang set maKhachHang = ?, ngayDat = ? , diaChiGiaoHang = ?, Trangthai = ? where maDH = ?";
	final String DELETE_SQL= "Delete from DonHang where maDH = ?";
	final String SELECT_ALL = "SELECT * From DonHang";
	final String SELECT_BY_ID = "Select * from DonHang where MaDH= ?";
 String sql = "SELECT DISTINCT YEAR(ngayDat) AS nam FROM DonHang";
	
	@Override
	public void insert(DonHangg entity) {
		jdbcHelper.update(INSERT_SQL, entity.getMaDH(), entity.getMaKH(), entity.getNgayDH(), entity.getDiachiGH(), entity.getTrangThai());
	}

	@Override
	public void update(DonHangg entity) {
		jdbcHelper.update(UPDATE_SQL,  entity.getMaKH(), entity.getNgayDH(), entity.getDiachiGH(),entity.getTrangThai(),entity.getMaDH());	
	}

	@Override
	public void delete(String id) {
		jdbcHelper.update(DELETE_SQL, id);
	}

	@Override
	public List<DonHangg> selectAll() {
	 return selectBySql(SELECT_ALL);
	}

	@Override
	public DonHangg selectById(Integer id) {
	List<DonHangg> list = selectBySql(SELECT_BY_ID, id);
	if (list.isEmpty()){
		return null;
	}
	return list.get(0);
	}

	@Override
	public List<DonHangg> selectBySql(String sql, Object... args) {
		List<DonHangg> list = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.query(sql, args);
			while (rs.next()){
				DonHangg entity = new DonHangg();
				entity.setMaDH(rs.getInt("maDH"));
				entity.setMaKH(rs.getString("maKhachHang"));
				entity.setNgayDH(rs.getDate("ngayDat"));
				entity.setDiachiGH(rs.getString("diaChiGiaoHang"));
				entity.setTrangThai(rs.getString("Trangthai"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	
//	public List<Integer> selectByYear() throws SQLException {
//
//    Connection conn = jdbcHelper.get
//        
//    PreparedStatement stmt = conn.prepareStatement(sql);
//    
//    List<Integer> listYears = new ArrayList<>();
//    
//    ResultSet rs = stmt.executeQuery();
//            
//    while (rs.next()) {
//       listYears.add(rs.getInt(1)); 
//    }
//
//    return listYears;       
//
//}

}
