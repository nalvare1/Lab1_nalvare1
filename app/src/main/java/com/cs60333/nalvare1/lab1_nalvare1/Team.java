package com.cs60333.nalvare1.lab1_nalvare1;

import java.io.Serializable;

/**
 * Created by apple on 3/1/17.
 */

public class Team implements Serializable {
    String imageName;
    String teamName;
    String dateAbbrev;
    String dateAndTime;
    String teamMascot;
    String teamRecord;
    String gameScore;
    String teamID;

    public Team(String[] inputString) { //team_name, team_logo, image_name, date_abbrev, date_and_time, team_mascot, team_record, game_score) {
        String image_name = inputString[0];
        String team_name = inputString[1];
        String date_abbrev = inputString[2];
        String date_and_time = inputString[3];
        String team_mascot = inputString[4];
        String team_record = inputString[5];
        String game_score = inputString[6];
        String team_ID = inputString[7];

        setTeamName(team_name);
        setImageName(image_name);
        setDateAbbrev(date_abbrev);
        setDateAndTime(date_and_time);
        setTeamMascot(team_mascot);
        setTeamRecord(team_record);
        setGameScore(game_score);
        setTeamID(team_ID);
    }

    //teamName
    public void setTeamName(String team_name) { this.teamName = team_name; }
    public String getTeamName() { return this.teamName; }
    //imageName
    public void setImageName(String image_name) { this.imageName = image_name; }
    public String getImageName() { return this.imageName; }
    //dateAbbrev
    public void setDateAbbrev(String date_abbrev) { this.dateAbbrev = date_abbrev; }
    public String getDateAbbrev() { return this.dateAbbrev; }
    //dateAndTime
    public void setDateAndTime(String date_and_time) { this.dateAndTime = date_and_time; }
    public String getDateAndTime() { return this.dateAndTime; }
    //mascot
    public void setTeamMascot(String team_mascot) { this.teamMascot = team_mascot; }
    public String getTeamMascot() { return this.teamMascot; }
    //teamRecord
    public void setTeamRecord(String team_record) { this.teamRecord = team_record; }
    public String getTeamRecord() { return this.teamRecord; }
    //gameScore
    public void setGameScore(String game_score) { this.gameScore = game_score; }
    public String getGameScore() { return this.gameScore; }

    //teamID (lab 7):
    public void setTeamID(String team_ID) { this.teamID = team_ID; }
    public String getTeamID() { return this.teamID; }

}