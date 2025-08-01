package com.example.crapsgame.models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dado clásico de 6 caras que implementa {@link Rollable}.
 */
public class Dice implements Rollable {

    /** Carpeta de imágenes: src/main/resources/com/example/crapsgame/images/ */
    public static final String IMAGES_PATH = "/com/example/crapsgame/images/dices/dice";

    /** Valor actual del dado (1 – 6). */
    private int value = 1;

    /** Lanza el dado y devuelve el nuevo valor. */
    @Override
    public int roll() {
        value = ThreadLocalRandom.current().nextInt(1, 7);
        return value;
    }

    public int getValue() {
        return value;
    }

    /** Ruta PNG correspondiente a la última tirada (dice-1.png … dice-6.png). */
    public String getImagePath() {
        return IMAGES_PATH + value + ".png";
    }

    /** Alias para compatibilidad con tu GameController. */
    public String getDiceImagePath() {
        return getImagePath();
    }
}
