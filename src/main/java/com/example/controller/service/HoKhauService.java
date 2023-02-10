package com.example.controller.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoKhauService {
	public ResultSet rs = null;
	
	public ResultSet getHoKhau() {
		String query = "SELECT ho_khau.idChuHo, maHoKhau, nhan_khau.hoTen, ho_khau.diaChi FROM ho_khau, nhan_khau where ho_khau.idChuHo = nhan_khau.ID ";
		return resultSet(query);
	}
	
	public ResultSet getThanhVien() {
		String query = "SELECT hoTen, namSinh, thanh_vien_cua_ho.quanHeVoiChuHo FROM nhan_khau, thanh_vien_cua_ho Where thanh_vien_cua_ho.idNhanKhau = nhan_khau.ID ";
		return resultSet(query);
		
	}
	
	public ResultSet getThanhVien(int id) {
		String query = "SELECT hoTen, namSinh, thanh_vien_cua_ho.quanHeVoiChuHo FROM nhan_khau, thanh_vien_cua_ho "
				+ "Where thanh_vien_cua_ho.idNhanKhau = nhan_khau.ID AND nhan_khau.ID =  " + id;
		return resultSet(query);
	}
	
	public ResultSet getGiaDinh(int id) {
		String query = "Select gia_dinh.idNhanKhau, gia_dinh.hoTen, gia_dinh.namSinh, gia_dinh.quanHeVoiNhanKhau from gia_dinh, ho_khau where gia_dinh.idNhanKhau = ho_khau.idChuHo and ho_khau.idChuHo = " + id ;
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
}
