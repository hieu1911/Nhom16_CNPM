package com.example.controller.HoKhauManageController;

import java.io.IOException;

import com.example.controller.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChuyenDiController extends Controller{

    @FXML
    private TextField diaChiChuyenDenTf;
    @FXML
    private TextField diaChiHienTaiTf;
    @FXML
    private TextField tenChuHoTf;
    @FXML
    private Button xacNhanBt;
    @FXML
    private Button huyBoBt;
    @FXML
    private TextArea liDoChuyenTa;
    @FXML
    private TextField nhapMaHoKhauTf;
    @FXML
    private TextField maHoKhauTf;
    @FXML
    private TextField maKhuVucTf;
    @FXML
    private TableView<?> hoKhauTv;
    @FXML
    private TableColumn<?, ?> diaChiCol;  
    @FXML
    private TableColumn<?, ?> maHoKhauCol;  
    @FXML
    private TableColumn<?, ?> tenChuHoCol;
    

    @FXML
    void huyBo(ActionEvent event) throws IOException {
    	huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void xacNhan(ActionEvent event) {

    }

}
