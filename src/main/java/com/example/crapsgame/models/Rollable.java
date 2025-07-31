package com.example.crapsgame.models;

/**
 * Contrato para objetos que se pueden “lanzar” y devolver un valor entero.
 */
@FunctionalInterface
public interface Rollable {
    int roll();
}
