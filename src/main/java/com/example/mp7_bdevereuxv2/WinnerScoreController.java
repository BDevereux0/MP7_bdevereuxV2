package com.example.mp7_bdevereuxv2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WinnerScoreController {

    @FXML
    Button newGameBtn;

    @FXML
    Button scoreBoardBtn;

    @FXML
    Button exitBtn;

    @FXML
    public void exitOnAction(){
        Platform.exit();
    }

    @FXML
    public void newGameOnAction(){
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
    public void scoreBoardOnAction(){
        Stage stage = new Stage();
        Scene scene;
        try {
            //close window
            Stage stage2 = (Stage) scoreBoardBtn.getScene().getWindow();
            stage2.close();

            //load scoreBoard stage
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
