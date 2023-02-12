package com.example.services;

import com.example.bean.NhanKhauBean;
import com.example.model.ChungMinhThu;
import com.example.model.GiaDinh;
import com.example.model.NhanKhau;
import com.example.model.TieuSu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			Connection connection = MySqlConnection.getMySqlConnection();
	    	Statement stmt  = connection.createStatement();
	    	rs    = stmt.executeQuery(query);
	    	
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
            Connection connection = MySqlConnection.getMySqlConnection();
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
			e.printStackTrace();
        }
        return true;
    }
	
    public void insertNhanKhau(int ID, String hoTen, String bietDanh, Date namSinh, String gioiTinh, String noiSinh,  String nguyeQuan, 
    		String dantoc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String diaChiHienNay, String trinhDoHocVan, String TrinhDoChuyenMon,
    		String bietTiengDanToc, String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec) {
    	try {
			
			Connection connection = MySqlConnection.getMySqlConnection();
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	public List<NhanKhauBean> statisticNhanKhau(int TuTuoi, int denTuoi, String gender, String Status, int tuNam, int denNam) {
		List<NhanKhauBean> list = new ArrayList<>();

		String query = "SELECT * FROM nhan_khau "
				+ " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
				+ " LEFT JOIN tam_tru ON nhan_khau.ID = tam_tru.idNhanKhau "
				+ " LEFT JOIN tam_vang ON nhan_khau.ID = tam_vang.idNhanKhau "
				+ " WHERE ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) >= "
				+ TuTuoi
				+ " AND ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) <= "
				+ denTuoi;
		if (!gender.equalsIgnoreCase("Toan Bo")) {
			query += " AND nhan_khau.gioiTinh = '" + gender + "'";
		}
		if (Status.equalsIgnoreCase("Toan bo")) {
			query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
					+ " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
		} else if (Status.equalsIgnoreCase("Thuong tru")) {
			query += " AND tam_tru.denNgay IS NULL";

		} else if (Status.equalsIgnoreCase("Tam tru")) {
			query += " AND (YEAR(tam_tru.tuNgay) BETWEEN "
					+ tuNam
					+ " AND "
					+ denNam
					+ ")";
		} else if (Status.equalsIgnoreCase("Tam vang")) {
			query += " AND (YEAR(tam_vang.tuNgay) BETWEEN "
					+ tuNam
					+ " AND "
					+ denNam
					+ ")";
		}
		query += " ORDER BY ngayTao DESC";
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
			PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			int idNhanKhau = -1;
			while (rs.next()){
				NhanKhauBean nhanKhauBean = new NhanKhauBean();
				NhanKhau nhanKhau = nhanKhauBean.getNhanKhau();
				ChungMinhThu chungMinhThu = nhanKhauBean.getChungMinhThu();
				idNhanKhau = rs.getInt("idNhanKhau");
				nhanKhau.setID(idNhanKhau);
				nhanKhau.setBietDanh(rs.getString("bietDanh"));
				nhanKhau.setHoTen(rs.getString("hoTen"));
				nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
				nhanKhau.setNamSinh(rs.getDate("namSinh"));
				nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
				nhanKhau.setTonGiao(rs.getString("tonGiao"));
				nhanKhau.setDanToc(rs.getString("danToc"));
				nhanKhau.setQuocTich(rs.getString("quocTich"));
				nhanKhau.setSoHoChieu(rs.getString("soHoChieu"));
				nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
				nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
				// con nhieu nua
				chungMinhThu.setIdNhanKhau(rs.getInt("idNhanKhau"));
				chungMinhThu.setSoCMT(rs.getString("soCMT"));
				chungMinhThu.setNgayCap(rs.getDate("ngayCap"));
				chungMinhThu.setNoiCap(rs.getString("noiCap"));

				if (idNhanKhau > 0) {
					String sql = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
					PreparedStatement prst = (PreparedStatement)connection.prepareStatement(sql);
					ResultSet rs_temp = prst.executeQuery();
					List<TieuSu> listTieuSu = new ArrayList<>();
					while (rs_temp.next()) {
						TieuSu tieuSu = new TieuSu();
						tieuSu.setID(rs_temp.getInt("ID"));
						tieuSu.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
						tieuSu.setTuNgay(rs_temp.getDate("tuNgay"));
						tieuSu.setDenNgay(rs_temp.getDate("denNgay"));
						tieuSu.setNgheNghiep(rs_temp.getString("ngheNghiep"));
						tieuSu.setNoiLamViec(rs_temp.getString("noiLamViec"));
						listTieuSu.add(tieuSu);
					}
					nhanKhauBean.setListTieuSu(listTieuSu);
					prst.close();

					sql = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
					prst = (PreparedStatement)connection.prepareStatement(sql);
					rs_temp = prst.executeQuery();
					List<GiaDinh> listGiaDinh = new ArrayList<>();
					while (rs_temp.next()) {
						GiaDinh giaDinh = new GiaDinh();
						giaDinh.setID(rs_temp.getInt("ID"));
						giaDinh.setHoTen(rs_temp.getString("hoTen"));
						giaDinh.setNamSinh(rs_temp.getDate("namSinh"));
						giaDinh.setGioiTinh(rs_temp.getString("gioiTinh"));
						giaDinh.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
						giaDinh.setDiaChiHienTai(rs_temp.getString("diaChiHienTai"));
						giaDinh.setNgheNghiep(rs_temp.getString("ngheNghiep"));
						giaDinh.setQuanHeVoiNhanKhau(rs_temp.getString("quanHeVoiNhanKhau"));
						listGiaDinh.add(giaDinh);
					}
					nhanKhauBean.setListGiaDinh(listGiaDinh);
					prst.close();
				}
				list.add(nhanKhauBean);
			}
			preparedStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list;
	}
}
