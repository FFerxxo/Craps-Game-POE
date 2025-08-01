package com.example.crapsgame.models;

public class Game {
    private int currentRoll;  // Tiro actual
    private int point;        // Punto establecido
    private int gamesWon;     // Juegos ganados
    private int gamesLost;    // Juegos perdidos
    private boolean isFirstRoll;
    private boolean gameOver;

    public Game() {
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.point = 0;
        this.currentRoll = 0;
        this.isFirstRoll = true;
        this.gameOver = false;
    }

    public void playRound(int rollValue) {
        this.currentRoll = rollValue;

        if (isFirstRoll) {
            // Primer tiro
            if (rollValue == 7 || rollValue == 11) {
                // Natural - gana
                gamesWon++;
                gameOver = true;
            } else if (rollValue == 2 || rollValue == 3 || rollValue == 12) {
                // Craps - pierde
                gamesLost++;
                gameOver = true;
            } else {
                // Establece punto
                point = rollValue;
                isFirstRoll = false;
            }
        } else {
            // Intentando hacer el punto
            if (rollValue == point) {
                // Hizo el punto - gana
                gamesWon++;
                gameOver = true;
            } else if (rollValue == 7) {
                // Seven out - pierde
                gamesLost++;
                gameOver = true;
            }
            // Si no es punto ni 7, continúa
        }
        if (gameOver) {
            startNewRound();
        }
    }
    private void startNewRound() {
        this.point = 0;
        this.currentRoll = 0;
        this.isFirstRoll = true;
        this.gameOver = false;
        // Los contadores gamesWon y gamesLost se mantienen
    }


    // Getters para mostrar en la vista
    public int getCurrentRoll() {
        return currentRoll;
    }

    public int getPoint() {
        return point;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isFirstRoll() {
        return isFirstRoll;
    }


}