package com.example.nhom16_cnpm;

import java.util.Date;

public class khai_tu {
	private int ID;
	private String soGiayKhaiTu;
	private int idNguoiKhai;
	private int idNguoiChet;
	private Date ngayKhai;
	private Date ngayChet;
	private String lyDoChet;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSoGiayKhaiTu() {
		return soGiayKhaiTu;
	}
	public void setSoGiayKhaiTu(String soGiayKhaiTu) {
		this.soGiayKhaiTu = soGiayKhaiTu;
	}
	public int getIdNguoiKhai() {
		return idNguoiKhai;
	}
	public void setIdNguoiKhai(int idNguoiKhai) {
		this.idNguoiKhai = idNguoiKhai;
	}
	public int getIdNguoiChet() {
		return idNguoiChet;
	}
	public void setIdNguoiChet(int idNguoiChet) {
		this.idNguoiChet = idNguoiChet;
	}
	public Date getNgayKhai() {
		return ngayKhai;
	}
	public void setNgayKhai(Date ngayKhai) {
		this.ngayKhai = ngayKhai;
	}
	public Date getNgayChet() {
		return ngayChet;
	}
	public void setNgayChet(Date ngayChet) {
		this.ngayChet = ngayChet;
	}
	public String getLyDoChet() {
		return lyDoChet;
	}
	public void setLyDoChet(String lyDoChet) {
		this.lyDoChet = lyDoChet;
	}
}