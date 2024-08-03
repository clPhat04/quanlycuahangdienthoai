/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phone.dao;

import com.phone.utils.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhatLe
 */
public class ThongKeDAO {
	 //class trung gian không có entity
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
    try (ResultSet rs = jdbcHelper.query(sql, args)) {
        List<Object[]> list = new ArrayList<>();
        while (rs.next()) {
            Object[] vals = new Object[cols.length];
            for (int i = 0; i < cols.length; i++) {
                vals[i] = rs.getObject(cols[i]);
            }
            list.add(vals);
        }
        return list;
    } catch (SQLException e) {
        // Ghi log hoặc xử lý lỗi theo ý muốn của bạn
        throw new RuntimeException("Error while processing database query.", e);
    }
}

    
       // Thống kê số lượng sản phẩm
//    public List<Object[]> thongKeSoLuongSanPham() {
//        String sql = "{CALL ThongKeSoLuongSanPham}";
//        String[] cols = {"ketqua"};
//      return getListOfArray(sql, cols);
//
//     
//        
//    }
    
    public List<Object[]> thongKeSoLuongSanPham() {
    String sql = "{CALL ThongKeSoLuongSanPham()}";
    String[] cols = {"ketqua"};

    try {
        // Chú ý: ResultSet không cần đóng ở đây vì nó được đóng trong getListOfArray
        return getListOfArray(sql, cols);
    } catch (Exception e) {
        // Ghi log hoặc xử lý lỗi theo ý muốn của bạn
        throw new RuntimeException("Error while processing database query.", e);
    }
}


    // Thống kê số lượng nhân viên
    public List<Object[]> thongKeSoLuongNhanVien() {
        String sql = "{CALL ThongKeSoLuongNhanVien}";
        String[] cols = {"ketqua"};
        return getListOfArray(sql, cols);
    }
      

public List<Object[]> thongKeSoLuongDonHangTheoNam(int nam) {
        String sql = "SELECT COUNT(*) AS SoLuongDonHang FROM DonHang WHERE YEAR(ngayDat) = ?";
        String[] cols = {"SoLuongDonHang"};

        return getListOfArray(sql, cols, nam);
    }








    

  
}
