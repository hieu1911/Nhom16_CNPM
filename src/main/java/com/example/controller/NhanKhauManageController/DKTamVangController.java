package com.example.controller.NhanKhauManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DKTamVangController {
	@FXML
    private Button checkBt;
    @FXML
    private DatePicker denNgayDpk;
    @FXML
    private Button huyBoBt;
    @FXML
    private TextArea liDoTa;
    @FXML
    private TextField maGiayTamVangTf;
    @FXML
    private TextField noiTamTruTf;
    @FXML
    private TextField soCMTTf;
    @FXML
    private Button taoBt;
    @FXML
    private DatePicker tuNgayDpk;
    
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
