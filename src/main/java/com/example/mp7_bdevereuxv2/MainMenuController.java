package com.example.mp7_bdevereuxv2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenuController {


    @FXML
    private Button newGameBtn;

    @FXML
    private Button scoreboardBtn;

    @FXML
    public void initialize(){

    }
    @FXML
    public void newGameBtnOnAction(){
        Stage stage = new Stage();
        Scene scene;

        try {
            //close window
            Stage stage1 = (Stage) newGameBtn.getScene().getWindow();
            stage1.close();

            //load playerName stage
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playerName-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Name Screen");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void scoreboardBtnOnClick(){
        Stage stage = new Stage();
        Scene scene;
        try {
            //close window
            Stage stage2 = (Stage) scoreboardBtn.getScene().getWindow();
            stage2.close();

            //load playerName stage
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scoreboard-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Scoreboard");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}