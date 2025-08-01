package com.example.crapsgame.controllers;

import com.example.crapsgame.models.AlertBox;
import com.example.crapsgame.models.Dice;
import com.example.crapsgame.models.Game;
import com.example.crapsgame.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label rollScoreLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label gamesWonLabel;


    @FXML
    private Label gamesLostLabel;

    @FXML
    private ImageView diceImageView1;

    @FXML
    private ImageView diceImageView2;

    @FXML
    private Button playButton;

    private AlertBox alertBox;

    private Player player;
    private Game game;

    public void initialize() {
        game = new Game();
        alertBox = new AlertBox();
        updateGameDisplay();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void showNicknameLabel() {
        nicknameLabel.setText(player.getNickname());
    }

    @FXML
    void onActionPlayButton(ActionEvent event) {

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        int rollScore = dice1.roll() + dice2.roll();

        this.diceImageView1.setImage(new Image(getClass().getResourceAsStream(
                dice1.getDiceImagePath()
        )));
        this.diceImageView2.setImage(new Image(getClass().getResourceAsStream(
                dice2.getDiceImagePath()
        )));
        this.rollScoreLabel.setText(String.valueOf(rollScore));

        game.playRound(rollScore);

        updateGameDisplay();

        if (game.isFirstRoll() && game.getCurrentRoll() == 0) {

            javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                    new javafx.animation.KeyFrame(
                            javafx.util.Duration.seconds(2),
                            e -> {
                                this.rollScoreLabel.setText("0");
                                this.diceImageView1.setImage(null);
                                this.diceImageView2.setImage(null);
                            }
                    )
            );
            timeline.play();
        }

    }


    // Método para actualizar la vista con los datos del modelo Game
    private void updateGameDisplay() {
        pointLabel.setText(game.getPoint() == 0 ? "-" : String.valueOf(game.getPoint()));
        gamesWonLabel.setText(String.valueOf(game.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(game.getGamesLost()));
    }


    @FXML
    void onActionHelpButton(ActionEvent event) {
        String title = "AYUDA - Cómo jugar CRAPS";
        String header = "Reglas del juego de dados CRAPS";
        String content = """
            🎲 REGLAS DEL CRAPS:
            
            PRIMER TIRO:
            • Si sacas 7 u 11: ¡GANAS inmediatamente!
            • Si sacas 2, 3 o 12: ¡PIERDES inmediatamente! (Craps)
            • Si sacas 4, 5, 6, 8, 9 o 10: Estableces el PUNTO
            
            DESPUÉS DEL PUNTO:
            • Sigue lanzando los dados
            • Si sacas tu PUNTO otra vez: ¡GANAS!
            • Si sacas 7: ¡PIERDES! (Seven Out)
            • Cualquier otro número: Continúa jugando
            
            🎯 OBJETIVO:
            Acumula la mayor cantidad de juegos ganados.
            
            ¡Buena suerte!
            """;

        alertBox.showAlertBox(title, header, content);
    }
}
