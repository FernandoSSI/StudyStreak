package com.example.studystreak.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQstsController implements Initializable {


    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button closeBtn;
    @FXML
    private Button minimizeBtn;
    @FXML
    private VBox subjectsVbox;
    @FXML
    private Button addSubjectBtn;


    private double xOffset = 0;
    private double yOffset = 0;

    public void drag(){
        mainPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        mainPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public void close(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void minimize(){
        Stage stage = (Stage) minimizeBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void addSubject(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drag();
    }
}
