package com.example.controller.HoKhauManageController;



import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.services.NhanKhauService;
import com.example.model.NhanKhau;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChonChuHoController extends ThemMoiHoKhauController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private TextField chonChuHoTf;
    @FXML
    private TextField chuHoTf;
    @FXML
    private Button xacNhanBt;
    @FXML
    private Button huyBoBt;
    @FXML
    private TableView<NhanKhau> chonChuHoTv;
    @FXML
    private TableColumn<NhanKhau, String> diaChiHienNayCol;
    @FXML
    private TableColumn<NhanKhau, String> gioiTinhCol;
    @FXML
    private TableColumn<NhanKhau, String> hoTenCol;
    @FXML
    private TableColumn<NhanKhau, Date> ngaySinhCol;
    @FXML
    private TableColumn<NhanKhau, String> soCMTCol;
    private ObservableList<NhanKhau> chuHoList;
    
    private String hoTen ;
    private Date ngaySinh;
    private String soCMT;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		chuHoList = FXCollections.observableArrayList(
				
		);
    	hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
    	ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
    	gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
    	diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
    	soCMTCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("soCMT"));;
    	chonChuHoTv.setItems(chuHoList);
		showInfor();
		
		chonChuHoTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
				 int id = chonChuHoTv.getSelectionModel().getSelectedItem().getID();
				 
				 NhanKhauService conn = new NhanKhauService();
				 if (conn.checkPerson(id)) {
					 hoTen = chonChuHoTv.getSelectionModel().getSelectedItem().getHoTen();
					 soCMT = chonChuHoTv.getSelectionModel().getSelectedItem().getSoCMT();
					 ngaySinh = chonChuHoTv.getSelectionModel().getSelectedItem().getNamSinh();
					 chuHoTf.setText(hoTen);
				 } else {
					 // hiện thông báo không chọn được
					 chuHoTf.setText("");
				 }
			}
		});
	}
	    
    private void showInfor() {
    	NhanKhauService conn = new NhanKhauService();
		ResultSet rs = conn.chonChuHoSelect();
		try {
			if (rs != null) {
				while (rs.next()) {
					NhanKhau nk = new NhanKhau();
					nk.setID(rs.getInt("ID"));
					nk.setSoCMT(rs.getString("soCMT"));
					nk.setHoTen(rs.getString("hoTen"));
					nk.setNamSinh(rs.getDate("namSinh"));
					nk.setGioiTinh(rs.getString("gioiTinh"));
					nk.setDiaChiHienNay(rs.getString("diaChiHienNay"));
					chuHoList.add(nk);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    
    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }
    
    @FXML
    void xacNhan(ActionEvent event) throws IOException {
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hokhaumanage/them-moi-ho-khau.fxml"));	
//		root = loader.load();	
//    	ThemMoiHoKhauController themHoKhau = loader.getController();
//    	themHoKhau.setThongTinChuHo(hoTen, ngaySinh, soCMT);
//        Node node = (Node) event.getSource();
//        stage = (Stage) node.getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
    	
    	System.out.println("ngay sinh la " + ngaySinh);
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	
//    	DataHoKhauMoi.hoTenChuHo = hoTen;
//    	super.setChuHoTf(hoTen);
    	stage.close();
//    	System.out.println("dong nay duoc doc sau khi close stage");
//    	super.setChuHoTf(hoTen);
//    	soCMTTf.setText(soCMT);
//    	super.setSoCMTTf(soCMT);
    	
//    	ThemMoiHoKhauController tm = new ThemMoiHoKhauController();
//    	tm.setThongTinChuHo(hoTen, ngaySinh, soCMT);
//    	stage.setUserData(tm);
    	
    	
    	
    }

    
    
    
    
}
