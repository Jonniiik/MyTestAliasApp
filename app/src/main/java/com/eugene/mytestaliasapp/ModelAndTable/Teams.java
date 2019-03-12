package com.eugene.mytestaliasapp.ModelAndTable;

public class Teams {
    private int id;
    private String nameTeam;
    private String namePlayerOne;
    private String namePlayerTwo;
    private int score;
    private int attempt;

    public Teams() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getNamePlayerOne() {
        return namePlayerOne;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public String getNamePlayerTwo() {
        return namePlayerTwo;
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
}
