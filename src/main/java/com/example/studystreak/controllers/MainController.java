package com.example.studystreak.controllers;

import com.example.studystreak.utils.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Button closeBtn;
    @FXML
    private Button minimizeBtn;
    @FXML
    private Button addBtn;
    @FXML
    private ImageView emoteImg;
    @FXML
    private ImageView sequenceImg;
    @FXML
    private Text questCount;
    @FXML
    private Text seqCount;
    @FXML
    private Text date;
    @FXML
    private Text totalQstCount;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    private double xOffset = 0;
    private double yOffset = 0;

    public MainController() {
    }

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

    public void add(){
        DatabaseManager.updateCounter(DatabaseManager.getCounter().getCurrentStreak() + 1, 10, 1, new Date());
        seqCount.setText(String.valueOf(DatabaseManager.getCounter().getCurrentStreak()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drag();
        seqCount.setText(String.valueOf(DatabaseManager.getCounter().getCurrentStreak()));
        questCount.setText(String.valueOf(DatabaseManager.getCounter().getDayQst()));
        date.setText(dateFormat.format(DatabaseManager.getCounter().getDate()));
        totalQstCount.setText(String.valueOf(DatabaseManager.getCounter().getTotalQst()) + " questões");

    }
}