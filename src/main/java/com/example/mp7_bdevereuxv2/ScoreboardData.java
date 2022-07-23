package com.example.mp7_bdevereuxv2;

import java.util.Date;

public class ScoreboardData {
    String name;
    String date;
    String points;
    String win;

   public ScoreboardData(String name, String date, String points, String win){
      this.name = name;
      this.date = date;
      this.points = points;
      this.win = win;
    }

    @Override
    public String toString() {
        return "ScoreboardData{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", points=" + points +
                ", win=" + win +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
