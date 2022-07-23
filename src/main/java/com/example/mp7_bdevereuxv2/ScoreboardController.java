package com.example.mp7_bdevereuxv2;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreboardController {

    ObjectInputStream ois;

   ObservableList<Player> list = FXCollections.observableArrayList();

    @FXML
    Button newGameBtn;

    @FXML
    Button exitBtn;

    @FXML
   TableView<Player> tableView;

   @FXML
   TableColumn <Player, String>nameTV;

   @FXML
   TableColumn <Player, Date>dateTV;

   @FXML
   TableColumn<Player, Integer> scoreTV;

   @FXML
   TableColumn<Player, Boolean> resultTV;

   @FXML
   TextArea streamTA;

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
   public void exitOnAction(){
       Platform.exit();
   }

    public void initialize() throws IOException {
        initColumns();
        readFile();

    }

    private void initColumns(){
        nameTV.setCellValueFactory(new PropertyValueFactory<>("Name"));
        dateTV.setCellValueFactory(new PropertyValueFactory<>("Date"));
        scoreTV.setCellValueFactory(new PropertyValueFactory<>("Points"));
        resultTV.setCellValueFactory(new PropertyValueFactory<>("Win"));
    }

    private void streamView(ObservableList<Player> list){
        list.stream().filter(e->e.isWin())
                .collect(Collectors.groupingBy(e->e, Collectors.counting()))
               .forEach((k,v)-> streamTA.setText(k.getName() + " has " +v + " wins\n"));
    }

    public void accessList(ObservableList<Player> list){
        tableView.getItems().addAll(list);
        streamView(list);
    }

    public void readFile() throws IOException {
        ExecutorService service = null;
        try {
            Thread.sleep(500);
            service = Executors.newSingleThreadExecutor();
            ois = new ObjectInputStream(new FileInputStream("src/playerlist.txt"));
            service.execute(() -> {
                boolean end = false;
                while (!end) {
                    try {
                        list.add((Player) ois.readObject());
                    } catch (EOFException e) {
                        end = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found in while statement");
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                accessList(list);
           });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (service != null){
                service.shutdown();
            }

        }
    }
}
