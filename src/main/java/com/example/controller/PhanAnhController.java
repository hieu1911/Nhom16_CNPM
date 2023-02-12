package com.example.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PhanAnhController extends Controller{
    @FXML
    void denHoKhau(MouseEvent event) throws IOException {
    	super.denTrangChu();
    }

    @FXML
    void denNhanKhau(MouseEvent event) throws IOException {
    	super.denNhanKhau();
    }

    @FXML
    void denPhanAnh(MouseEvent event) throws IOException {
    	super.denPhanAnh();
    }

    @FXML
    void denThongKe(MouseEvent event) throws IOException {
    	super.denThongKe();
    }

    @FXML
    void denTrangChu(MouseEvent event) throws IOException {
    	super.denTrangChu();
    }
  
    
    public void themMoi(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/com/example/main/themMoi.fxml"));
    	loader.load();
    	Parent root = loader.getRoot();
    	Stage modal_dialog = new Stage(StageStyle.DECORATED);
    	modal_dialog.initModality(Modality.WINDOW_MODAL);
    	modal_dialog.initOwner(stage);
    	modal_dialog.setTitle("Them Moi");
    	Scene scene = new Scene(root);
    	modal_dialog.setScene(scene);
    	modal_dialog.show();
    }
    
    public void daGiaiQuyet(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/com/example/main/daGiaiQuyet.fxml"));
    	loader.load();
    	Parent root = loader.getRoot();
    	Stage modal_dialog = new Stage(StageStyle.DECORATED);
    	modal_dialog.initModality(Modality.WINDOW_MODAL);
    	modal_dialog.initOwner(stage);
    	modal_dialog.setTitle("Da Giai Quyet");
    	Scene scene = new Scene(root);
    	modal_dialog.setScene(scene);
    	modal_dialog.show();
    }
    
    public void dangChoDuyet(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/com/example/main/dangChoDuyet.fxml"));
    	loader.load();
    	Parent root = loader.getRoot();
    	Stage modal_dialog = new Stage(StageStyle.DECORATED);
    	modal_dialog.initModality(Modality.WINDOW_MODAL);
    	modal_dialog.initOwner(stage);
    	modal_dialog.setTitle("Dang Cho Duyet");
    	Scene scene = new Scene(root);
    	modal_dialog.setScene(scene);
    	modal_dialog.show();
    }
}


