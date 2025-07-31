package com.example.crapsgame.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Lógica principal del juego de Craps.
 */
public class Game {

    // ---------- estado ----------
    private final Rollable dice = new Dice();   // usa la interfaz
    private int lastRoll;                       // suma de los dos dados
    private int point;                          // 0 = sin punto
    private int wins;
    private int losses;
    private final List<Integer> rollHistory = new ArrayList<>();

    // ---------- lógica ----------
    /**
     * Lanza dos dados, actualiza el estado según las reglas de Craps
     * y devuelve la suma obtenida.
     */
    public int rollDice() {
        int sum = dice.roll() + dice.roll();
        lastRoll = sum;
        rollHistory.add(sum);

        if (point == 0) {               // tiro de salida
            switch (sum) {
                case 7, 11 -> wins++;
                case 2, 3, 12 -> losses++;
                default -> point = sum; // se establece el punto
            }
        } else {                        // ya hay punto
            if (sum == point) { wins++;  point = 0; }
            else if (sum == 7) { losses++; point = 0; }
            // de lo contrario la partida continúa
        }
        return sum;
    }

    /** Reinicia la ronda sin borrar el marcador global. */
    public void resetRound() {
        lastRoll = 0;
        point = 0;
        rollHistory.clear();
    }

    // ---------- getters ----------
    public int getLastRoll()          { return lastRoll; }
    /** Devuelve 0 si aún no hay punto establecido. */
    public int getPoint()             { return point; }
    public int getWins()              { return wins; }
    public int getLosses()            { return losses; }
    public List<Integer> getHistory() { return rollHistory; }
}
