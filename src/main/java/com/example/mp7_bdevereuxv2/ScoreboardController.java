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
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreboardController  {

    ObjectInputStream ois;

    Scanner scanner;

    ObservableList<ScoreboardData> list = FXCollections.observableArrayList();

    @FXML
    Button newGameBtn;

    @FXML
    Button exitBtn;

    @FXML
    TableView<ScoreboardData> tableView;

    @FXML
    TableColumn<ScoreboardData, String> nameTV;

    @FXML
    TableColumn<ScoreboardData, Date> dateTV;

    @FXML
    TableColumn<ScoreboardData, Integer> scoreTV;

    @FXML
    TableColumn<ScoreboardData, Boolean> resultTV;

    @FXML
    TextArea streamTA;

    @FXML
    public void newGameOnAction() {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitOnAction() {
        Platform.exit();
    }

    public void initialize() throws IOException {
        initColumns();
        readFile();

    }

    private void initColumns() {
        nameTV.setCellValueFactory(new PropertyValueFactory<>("Name"));
        dateTV.setCellValueFactory(new PropertyValueFactory<>("Date"));
        scoreTV.setCellValueFactory(new PropertyValueFactory<>("Points"));
        resultTV.setCellValueFactory(new PropertyValueFactory<>("Win"));
    }

    private void streamView(List<ScoreboardData> list){
      list.stream()
                .filter(e-> Boolean.parseBoolean(e.getWin()))
                .collect(Collectors.groupingBy(e->e.getName(), Collectors.counting()))
                .forEach((k,v)-> streamTA.appendText(k + " has " + v + " wins\n"));


    }

    public void accessList(ObservableList<ScoreboardData> list) {
        tableView.getItems().addAll(list);
        streamView(list);
    }

    public void readFile() throws IOException {
        ExecutorService service = null;
        /*try {
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
           });*/

        try {

            Thread.sleep(500);
            service = Executors.newSingleThreadExecutor();
            scanner = new Scanner(new FileReader("src/playerlist.txt"));

            service.execute(() -> {
                String input;

                while (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                    String[] scoreboardDataFeed = input.split(",");
                    ScoreboardData sbd = new ScoreboardData(scoreboardDataFeed[0], scoreboardDataFeed[1],
                            scoreboardDataFeed[2], scoreboardDataFeed[3]);
                    list.add(sbd);
                }

                accessList(list);

            });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (service != null) {
                service.shutdown();
            }

        }
    }
}
