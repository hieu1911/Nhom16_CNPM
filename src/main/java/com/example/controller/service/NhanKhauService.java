package com.example.controller.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NhanKhauService {
	ResultSet rs  = null;
	public ResultSet getNhanKhau() {
    	String query = "SELECT ID, hoTen, namSinh, gioiTinh, diaChiHienNay FROM nhan_khau ";
    	return resultSet(query);
    }
	
	public ResultSet chonChuHoSelect() {
		String query = "SELECT  chung_minh_thu.soCMT, nhan_khau.ID, hoTen, namSinh, gioiTinh, diaChiHienNay FROM nhan_khau, chung_minh_thu where chung_minh_thu.idNhanKhau = nhan_khau.ID ";
		return resultSet(query);
    }
    
	public ResultSet resultSet (String query) {
		try {
			Connection connection = MySqlConnection.getMysqlConnection();
	    	Statement stmt  = connection.createStatement();
	    	rs    = stmt.executeQuery(query);
	    	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
	}
	
	// kiểm tra nhân khẩu đã nằm trong hộ khẩu nào chưa
	public boolean checkInHoKhau (int idNhanKhau) throws SQLException {
		String query = "SELECT nhan_khau.ID from nhan_khau, thanh_vien_cua_ho where nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau and nhan_khau.ID = " + idNhanKhau;
		if (resultSet(query).next()) {
			return false;
		} else 
			return true;
	}
	public boolean checkPerson(int id) {
        try {
            Connection connection = MySqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN thanh_vien_cua_ho ON ho_khau.ID = thanh_vien_cua_ho.idHoKhau"
                        + " WHERE ho_khau.idChuHo = "
                        + id 
                        + " OR thanh_vien_cua_ho.idNhanKhau = "
                        + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
	
    public void insertNhanKhau(int ID, String hoTen, String bietDanh, Date namSinh, String gioiTinh, String noiSinh,  String nguyeQuan, 
    		String dantoc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String diaChiHienNay, String trinhDoHocVan, String TrinhDoChuyenMon,
    		String bietTiengDanToc, String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec) {
    	try {
			
			Connection connection = MySqlConnection.getMysqlConnection();
			String INSERT_QUERY = "INSERT INTO nhan_khau (ID, hoTen, bietDanh, namSinh, soCMT, gioiTinh, noiSinh, nguyenQuan, "
					+ "danToc, tonGiao, quocTich, soHoChieu, noiThuongTru, diaChiHienNay, trinhDoHocVan, TrinhDoChuyenMon,"
					+ "bietTiengDanToc, trinhDoNgoaiNgu, ngheNghiep, noiLamViec) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);
			stmt.setInt(1, ID);
			stmt.setString(2, hoTen);
			stmt.setString(3, bietDanh);
			stmt.setDate(4, namSinh);
			stmt.setString(5, gioiTinh);
			stmt.setString(6, noiSinh);
			stmt.setString(7, nguyeQuan);
			stmt.setString(8, dantoc);
			stmt.setString(9, tonGiao);
			stmt.setString(10, quocTich);
			stmt.setString(11, soHoChieu);
			stmt.setString(12, noiThuongTru);
			stmt.setString(13, diaChiHienNay);
			stmt.setString(14, trinhDoHocVan);
			stmt.setString(15, TrinhDoChuyenMon);
			stmt.setString(16, bietTiengDanToc);
			stmt.setString(17, trinhDoNgoaiNgu);
			stmt.setString(18, ngheNghiep);
			stmt.setString(19, noiLamViec);
			
			int i = stmt.executeUpdate();
            if (i > 0) {
            	System.out.println("insert successfully!");
            } else {
            	System.out.println("Not insert database!");
            }	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
