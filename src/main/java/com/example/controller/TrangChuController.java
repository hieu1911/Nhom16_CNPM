package com.example.controller;

import com.example.services.MySqlConnection;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrangChuController extends Controller {
    public TrangChuController() {
        setData();
    }

    public void setData() {
        try {
            Connection connection = MySqlConnection.getMySqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("tong"));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM ho_khau";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay < NOW()";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_vang WHERE denNgay < NOW()";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            }
            preparedStatement.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
