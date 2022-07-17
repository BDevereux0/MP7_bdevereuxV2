package com.example.mp7_bdevereuxv2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController extends PlayerNameController{

    @FXML
    Button rollBtn;

    @FXML
    Button holdBtn;

    @FXML
    TextArea player1ScoreTA;

    @FXML
    TextArea player2ScoreTA;

    @FXML
    TextArea currentRollAmountTA;

    @FXML
    TextArea playerTurnTA;

    int total =0;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public void initialize(){
        playerTurnTA.setText(checkPlayerTurn());
    }

    private int rollValue(){
        //generates the die amount
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            arrayList.add(i);
        }
        //shuffles arraylist
        Collections.shuffle(arrayList);

        //die amount
        int x = arrayList.get(0);
        return x;
    }

    public String checkPlayerTurn(){

        if (player1.isTurn()){
            return player1.getName();
        }else
            return player2.getName();
    }
    public void swapTurns(){
        if (player1.isTurn()){
            player1.setTurn(false);
        }else if (!player1.isTurn()) {
            player1.setTurn(true);
        }

        if (player2.isTurn()){
            player2.setTurn(false);
        }else if (!player2.isTurn()) {
            player2.setTurn(true);
        }
    }
    public void setScore(){
        if (player1.isTurn()){
            player1.setPoints(player1.getPoints()+ total);
        }else
            player2.setPoints(player2.getPoints()+ total);
    }

    public void checkWin() throws IOException {

        if (player1.getPoints() >= 10 || player2.getPoints() >= 10){
            if (player1.getPoints() >=10){
                player1.setWin(true);
            }else {
                player2.setWin(true);
            }

            try {
                oos = new ObjectOutputStream(new FileOutputStream("src/playerlist.dat"));
                oos.writeObject(player1);
                oos.writeObject(player2);
                System.out.println("Writing to file..");
            }catch (FileNotFoundException e){
                System.out.println("File not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if (oos != null){
                    oos.close();
                }
            }
        }
    }
    @FXML
    private void rollBtnOnClick(){
        currentRollAmountTA.setText(String.valueOf(rollValue()));
        playerTurnTA.setText(checkPlayerTurn());

        if (Integer.valueOf(currentRollAmountTA.getText())  == 1){
            total = 0;
            swapTurns();
            }else
                 total = Integer.valueOf(currentRollAmountTA.getText()) + total;
    }
    @FXML
    private void holdBtnOnClick() throws IOException {
        setScore();
        checkWin();
        swapTurns();
        total = 0;
        playerTurnTA.setText(checkPlayerTurn());
        player1ScoreTA.setText(String.valueOf(player1.points));
        player2ScoreTA.setText(String.valueOf(player2.points));


    }
}