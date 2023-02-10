package com.example.controller.NhanKhauManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class KhaiTuController {

    @FXML
    private Button checkBt;

    @FXML
    private TextField chetLucTf;

    @FXML
    private TextField hoTenNguoiChetTf;

    @FXML
    private Button huyBoBt;

    @FXML
    private TextArea liDoTa;

    @FXML
    private DatePicker namSinhDpk;

    @FXML
    private TextField noiChetTf;

    @FXML
    private TextField soCMTTf;

    @FXML
    private Button taoBt;

    @FXML
    void check(ActionEvent event) {

    }

    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void tao(ActionEvent event) {

    }

}
