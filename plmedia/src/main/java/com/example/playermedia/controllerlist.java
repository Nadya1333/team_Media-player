package com.example.playermedia;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class controllerlist implements Initializable {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TableView<tablelist> table;

        @FXML
        private TableColumn<tablelist, String> album;

        @FXML
        private TableColumn<tablelist, String> author;

        @FXML
        private TableColumn<tablelist, Integer> num;

        @FXML
        private TableColumn<tablelist, Integer> time;

        @FXML
        private TableColumn<tablelist, String> track;

        @FXML
        void initialize() {
        }

        // ObservableList<tablelist> list = FXCollections.observableArrayList(
               // new tablelist();
        //);

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                num.setCellValueFactory(new PropertyValueFactory <tablelist, Integer>("№"));
                track.setCellValueFactory(new PropertyValueFactory <tablelist, String>("Track"));
                time.setCellValueFactory(new PropertyValueFactory <tablelist, Integer>("Time"));
                author.setCellValueFactory(new PropertyValueFactory <tablelist, String>("Author"));
                album.setCellValueFactory(new PropertyValueFactory <tablelist, String>("Album"));

        }
}



