/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm;

/**
 * La classe GameGrid représente la grille du jeu Lights-Off.
 * Elle contient un tableau 5x5 de lumières (allumées ou éteintes).
 */
public class GameGrid {

    /** La taille de la grille (5x5). */
    private static final int SIZE = 5;

    /**
     * Le tableau à deux dimensions représentant les lumières.
     * true = lumière allumée, false = lumière éteinte.
     */
    private boolean[][] lights = new boolean[SIZE][SIZE];

    /**
     * Initialise la grille en éteignant toutes les lumières.
     */
    public void init() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                lights[row][col] = false;
            }
        }
    }

    /**
     * Inverse l'état de la lumière à la position donnée,
     * ainsi que celles des cases voisines (haut, bas, gauche, droite).
     *
     * @param row    La ligne de la lumière à inverser.
     * @param column La colonne de la lumière à inverser.
     */
    public void switchAt(int row, int column) {
        // Inverse la lumière cliquée
        toggle(row, column);
        // Inverse les voisins (si ils existent)
        toggle(row - 1, column); // haut
        toggle(row + 1, column); // bas
        toggle(row, column - 1); // gauche
        toggle(row, column + 1); // droite
    }

    /**
     * Inverse l'état d'une lumière si la position est valide.
     *
     * @param row    La ligne.
     * @param column La colonne.
     */
    private void toggle(int row, int column) {
        if (row >= 0 && row < SIZE && column >= 0 && column < SIZE) {
            lights[row][column] = !lights[row][column];
        }
    }

    /**
     * Vérifie si la lumière à la position donnée est allumée.
     *
     * @param row    La ligne.
     * @param column La colonne.
     * @return true si la lumière est allumée, false sinon.
     */
    public boolean isOn(int row, int column) {
        return lights[row][column];
    }

    /**
     * Vérifie si toutes les lumières sont éteintes.
     *
     * @return true si toutes les lumières sont éteintes, false sinon.
     */
    public boolean isOff() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (lights[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

}