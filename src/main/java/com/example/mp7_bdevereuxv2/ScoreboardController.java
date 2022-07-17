package com.example.mp7_bdevereuxv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardController {
    ObjectInputStream ois;
   ObservableList<Player> list = FXCollections.observableArrayList();

   @FXML
   TableView tableView;

   @FXML
   TableColumn nameTV;

   @FXML
   TableColumn dateTV;

   @FXML
   TableColumn scoreTV;

   @FXML
   TableColumn resultTV;

   @FXML
   TextArea streamTA;

    /*public void initialize() throws IOException {
        try{
            boolean end = false;
            while (!end) {
                try {
                    ois = new ObjectInputStream(new FileInputStream("src/playerlist.dat"));
                    ois.readObject();
                    list.add((Player) ois.readObject());
                }catch (EOFException e){
                    end = true;
                }
            }
            System.out.println(list);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }*/
}
