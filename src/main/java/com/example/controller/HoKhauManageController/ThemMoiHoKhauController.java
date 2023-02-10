package com.example.controller.HoKhauManageController;

import java.io.IOException;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.controller.Controller;
import com.example.controller.service.HoKhauService;
import com.example.main.QuanLyNhanKhau;
import com.example.model.HoKhau;
import com.example.model.ThanhVienCuaHo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ThemMoiHoKhauController extends Controller implements Initializable {

    @FXML
    private Button chonChuHoBt;
    @FXML
    private Button chonThanhVienBt;
    @FXML
    private TextField chuHoTf;
    @FXML
    private TextField diaChiTf;
    @FXML
    private TextField maHoKhauTf;
    @FXML
    private TextField ngaySinhChuHoTf;
    @FXML
    protected TextField soCMTTf;
    @FXML
    private Button huyBoBt;
    @FXML
    private Button taoBt;
    @FXML
    private TextField maKhuVucTf;
    @FXML
    private TableView<ThanhVienCuaHo> thanhVienTv;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> hoTenCol;
    @FXML
    private TableColumn<ThanhVienCuaHo, Date> ngaySinhCol;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> quanHeCol;
    private ObservableList<ThanhVienCuaHo> thanhVienList;
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	thanhVienList = FXCollections.observableArrayList(
				
		);
    	hoTenCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
    	ngaySinhCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
    	quanHeCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
    	thanhVienTv.setItems(thanhVienList);
	}
    
    private void showInfor() {
    	HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getThanhVien();
		try {
			if (rs != null) {
				while (rs.next()) {
					ThanhVienCuaHo tvch = new ThanhVienCuaHo();
					tvch.setHoTen(rs.getString("hoTen"));
					tvch.setNamSinh(rs.getDate("namSinh"));
					tvch.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
					thanhVienList.add(tvch);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
    void chonChuHo(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/chon-chu-ho.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Chọn chủ hộ");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
	
	public void setThongTinChuHo(String hoTen, Date ngaySinh, String soCMT) { 
		chuHoTf.setText(hoTen);
//		LocalDate date = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		ngaySinhChuHoDpk.setValue(date);
		soCMTTf.setText(soCMT);
	}

    @FXML
    void chonThanhVien(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/chon-thanh-vien.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Chọn thành viên");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }

    
    
    public String getChuHoTf() {
		return chuHoTf.getText();
	}

	public void setChuHoTf(String chuHo) {
		chuHoTf.setText("sao khong đổi");
		System.out.println("dong nay chay trong them moi controller");
	}

	

	public void setNgaySinhChuHoTf(Date ngaySinhChuHo) {
		ngaySinhChuHoTf.setText(ngaySinhChuHo.toString());
	}

	public String getSoCMTTf() {
		return soCMTTf.getText();
	}

	public void setSoCMTTf(String soCMT) {
		soCMTTf.setText("so cmt da duoc đổi");
	}

	@FXML
    void huyBo(ActionEvent event) throws IOException {
    	huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void tao(ActionEvent event) {

    }

	

}
