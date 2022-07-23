package com.example.mp7_bdevereuxv2;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable {
    String name;
    int points;
    boolean win;

    boolean turn;

    Date date;

    Player(String name, int points, boolean win, boolean turn){
        this.name = name;
        this.points = points;
        this.win = win;
        this.turn = turn;
        date = new Date();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", win=" + win +
                ", date=" + date +
                ", turn" + turn +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

