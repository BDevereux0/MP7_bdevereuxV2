package com.example.mp7_bdevereuxv2;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerNameController {

    @FXML
    Button beginGameBtn;

    @FXML
    TextArea player1TA;

    @FXML
    TextArea player2TA;

    static Player player1;
    static Player player2;


    @FXML
    public void beginGameBtnOnClick(){
        player1 = new Player(player1TA.getText(), 0, false, true);
        player2 = new Player(player2TA.getText(), 0, false, false);
        Scene scene;
        Stage stage = new Stage();

        try {
            //close window
            Stage stage1 = (Stage) beginGameBtn.getScene().getWindow();
            stage1.close();

            //load gameview stage
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Game Screen");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
