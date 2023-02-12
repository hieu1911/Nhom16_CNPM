package com.example.controller.HoKhauManageController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.main.QuanLyNhanKhau;

import com.example.controller.Controller;
import com.example.services.HoKhauService;
import com.example.model.HoKhau;
import com.example.model.ThanhVienCuaHo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TachHoKhauController extends Controller implements Initializable {
	
	@FXML
    private TextField maHoKhauTf;
    @FXML
    private TableView<HoKhau> chonHoCanTachTv;
    @FXML
    private TableColumn<HoKhau, String> maHoKhauCol;
    @FXML
    private TableColumn<HoKhau, String> hoTenChuHoCol;
    @FXML
    private TableColumn<HoKhau, String> diaChiCol;
    private ObservableList<HoKhau> hoKhauCanTachList;
    
    @FXML
    private TextField chuHoHienTaiTf;
    @FXML
    private TextField chuHoMoiTf;
    @FXML
    private TextField diaChiTf;
    @FXML
    private TextField maHoKhauMoiTf;
    @FXML
    private TextField maKhuVucTf;
    
    @FXML
    private TableView<ThanhVienCuaHo> nhanKhau1Tv;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> hoTen1Col;
    @FXML
    private TableColumn<ThanhVienCuaHo, Date> ngaySinh1Col;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHo1Col; 
    private ObservableList<ThanhVienCuaHo> nhanKhauList1;

    @FXML
    private Button themNguoiBt;
    @FXML
    private Button xoaNguoiBt;
    
    @FXML
    private TableView<ThanhVienCuaHo> nhanKhau2Tv;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> hoTen2Col;
    @FXML
    private TableColumn<ThanhVienCuaHo, Date> ngaySinh2Col;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHo2Col;
    private ObservableList<ThanhVienCuaHo> nhanKhauList2;

    @FXML
    private Button xacNhanBt;
    @FXML
    private Button huyBoBt;    
    
    private int idChuHo;
    private int idNhanKhau1;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	hoKhauCanTachList = FXCollections.observableArrayList(
				
		);
    	maHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("maHoKhau"));
    	hoTenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("hoTenChuHo"));
    	diaChiCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("diaChi"));
    	chonHoCanTachTv.setItems(hoKhauCanTachList);
    	showInfor();
    	
    	//chưa sửa
    	chonHoCanTachTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            	nhanKhauList1.clear();
            	idChuHo = chonHoCanTachTv.getSelectionModel().getSelectedItem().getIdChuHo();
            	String hoTenChuHoHienTai = chonHoCanTachTv.getSelectionModel().getSelectedItem().getHoTenChuHo();
				hienThongTinGiaDinh();
				chuHoHienTaiTf.setText(hoTenChuHoHienTai);
			}
		});
    	
    	nhanKhauList1 = FXCollections.observableArrayList(
				
		);
    	hoTen1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
    	ngaySinh1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
    	quanHeVoiChuHo1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
    	nhanKhau1Tv.setItems(nhanKhauList1);
    	nhanKhau1Tv.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				idNhanKhau1 = nhanKhau1Tv.getSelectionModel().getSelectedItem().getIdHoKhau();
				System.out.println("da lay dc id nhan khau " + idNhanKhau1);
			}
    		
    	});
    	
    	
    	nhanKhauList2 = FXCollections.observableArrayList(
				
		);
    	hoTen2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
    	ngaySinh2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
    	quanHeVoiChuHo2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
    	nhanKhau2Tv.setItems(nhanKhauList2);
	}
    
    private void showInfor() {
    	HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getHoKhau();
		try {
			if (rs != null) {
				while (rs.next()) {
					HoKhau hk = new HoKhau();
					hk.setIdChuHo(rs.getInt("idChuHo"));
					hk.setMaHoKhau(rs.getString("maHoKhau"));
					hk.setHoTenChuHo(rs.getString("hoTen"));
					hk.setDiaChi(rs.getString("diaChi"));
					hoKhauCanTachList.add(hk);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    private void hienThongTinGiaDinh() {
    	HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getGiaDinh(idChuHo);
		try {
			if (rs != null) {
				while (rs.next()) {
					ThanhVienCuaHo tvch = new ThanhVienCuaHo();
					tvch.setIdNhanhKhau(rs.getInt("idNhanKhau"));
					tvch.setHoTen(rs.getString("hoTen"));
					tvch.setNamSinh(rs.getDate("namSinh"));
					tvch.setQuanHeVoiChuHo(rs.getString("quanHeVoiNhanKhau"));
					nhanKhauList1.add(tvch);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    @FXML
    void themNguoi(ActionEvent event) {

    }
    @FXML
    void xoaNguoi(ActionEvent event) {

    }
    
    @FXML
    void xacNhan(ActionEvent event) {

    }
    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }
	
    

}
