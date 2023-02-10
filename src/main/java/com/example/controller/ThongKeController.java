package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class ThongKeController extends Controller{
    @FXML
    MenuButton menuGioiTinh;
    @FXML
    MenuItem gtToanBo;
    @FXML
    MenuItem gtNam;
    @FXML
    MenuItem gtNu;
    @FXML
    MenuButton menuTinhTrang;
    @FXML
    MenuItem ttToanBo;
    @FXML
    MenuItem ttDocThan;
    @FXML
    MenuItem ttDaKetHon;
    @FXML
    TextField minNam;
    @FXML
    TextField maxNam;
    @FXML
    TextField minDoTuoi;
    @FXML
    TextField maxDoTuoi;
    @FXML
    Button showButton;
    @FXML
    Button exportButton;

    public ThongKeController() {

    }
}
