package com.example.controller.HoKhauManageController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.services.NhanKhauService;
import com.example.model.NhanKhau;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ChonThanhVienController extends ThemMoiHoKhauController  implements Initializable {
	@FXML
    private Button themThanhVienBt;
    @FXML
    private Button xoaThanhVienBt;
    @FXML
    private TableView<NhanKhau> nhanKhauTv;
	@FXML
    private TableColumn<NhanKhau, Integer> soCMTCol;
	@FXML
    private TableColumn<NhanKhau, String> hoTenCol;
	@FXML
    private TableColumn<NhanKhau, Date> ngaySinhCol;
	@FXML
    private TableColumn<NhanKhau, String> gioiTinhCol;
	@FXML
    private TableColumn<NhanKhau, String> diaChiHienNayCol;
	private ObservableList<NhanKhau> nhanKhauList;
    
    @FXML
    private TextField hoTenTf;
    @FXML
    private Button luuThayDoiBt;

    @FXML
    private TableView<ThanhVienCuaHo> thanhVienCuaHoTv;
    @FXML
    private TableColumn<ThanhVienCuaHo, Date> ngaySinhTVCol;
    @FXML
    private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHoTVCol;   
    @FXML
    private TableColumn<ThanhVienCuaHo, String> hoTenTVCol;
    private ObservableList<ThanhVienCuaHo> thanhVienCuaHoList;
    
    private boolean checkPerson = false;
    private int idNhanKhau;
    private String hoTen;
    private Date namSinh;
    private String quanHeVoiChuHo;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	
    	nhanKhauList = FXCollections.observableArrayList(
				
		);
    	soCMTCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
		gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
		diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
		nhanKhauTv.setItems(nhanKhauList);
		showInforNhanKhau();
		// chooseThanhVien : chọn nhan khau chưa có trong hộ khẩu nào
		nhanKhauTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
				 int id = nhanKhauTv.getSelectionModel().getSelectedItem().getID();
				 
				 
				 NhanKhauService conn = new NhanKhauService();
				 if (conn.checkPerson(id)) {
					 checkPerson = true;
					 System.out.println("co the chộn đuọccư");
					 idNhanKhau = id;
					 hoTen = nhanKhauTv.getSelectionModel().getSelectedItem().getHoTen();
					 namSinh = nhanKhauTv.getSelectionModel().getSelectedItem().getNamSinh();
//					 quanHeVoiChuHo = nhanKhauTv.getSelectionModel().getSelectedItem().;
					 
				 } else {
					 // hiện thông báo không chọn được
					 System.out.println("khong chon duọc");
				 }
			}
		});
		
		thanhVienCuaHoList = FXCollections.observableArrayList(
				
		);
    	hoTenTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
    	ngaySinhTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
    	quanHeVoiChuHoTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
    	thanhVienCuaHoTv.setItems(thanhVienCuaHoList);
    	
		    	
	}
    
    public void showInforNhanKhau() {
    	NhanKhauService conn = new NhanKhauService();
		ResultSet rs = conn.getNhanKhau();
		try {
			if (rs != null) {
				while (rs.next()) {
					NhanKhau nhanKhau = new NhanKhau();
					nhanKhau.setID(rs.getInt("ID"));
					nhanKhau.setHoTen(rs.getString("hoTen"));
					nhanKhau.setNamSinh(rs.getDate("namSinh"));
					nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
					nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
					nhanKhauList.add(nhanKhau);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void luuThayDoi(ActionEvent event) {

    }

    @FXML
    void themThanhVien(ActionEvent event) throws SQLException {
    	// nếu nhan khâu chưa nằm trong hộ nào thì thêm quan hệ vói chủ hộ và them vào cơ sở dữ liệu Quan hê vói chủ hộ nếu xác nhận
    	if (checkPerson == true) {
    		TextInputDialog dialog = new TextInputDialog("");
    		dialog.setTitle("Quan he voi chu ho");
    		dialog.setHeaderText("Look, a Text Input Dialog");
    		dialog.setContentText("Please enter your relationship:");

    		// Traditional way to get the response value.
    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    		    ThanhVienCuaHo tvch = new ThanhVienCuaHo();
        		tvch.setIdNhanhKhau(idNhanKhau);
        		tvch.setHoTen(hoTen);
        		tvch.setNamSinh(namSinh);
        		tvch.setQuanHeVoiChuHo(result.get());
        		thanhVienCuaHoList.add(tvch);
    		}	   		
    	}
    }

    @FXML
    void xoaThanhVien(ActionEvent event) {

    }

	

}