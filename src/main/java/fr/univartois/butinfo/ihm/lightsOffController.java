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

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * La classe lightsOffController est le contrôleur de l'interface graphique
 * du jeu Lights-Off.
 */
public class lightsOffController {

    /** La grille JavaFX contenant les boutons représentant les lumières. */
    @FXML
    private GridPane gridLights;

    /**
     * Le tableau des boutons de la grille, permettant d'y accéder
     * facilement par leur position (ligne, colonne).
     */
    private Button[][] lightButtons = new Button[5][5];

    /** Le modèle du jeu (la grille logique). */
    private GameGrid gameGrid = new GameGrid();

    /**
     * Méthode appelée automatiquement après l'injection des composants FXML.
     * Elle récupère les boutons de la grille et les range dans lightButtons.
     */
    @FXML
    void initialize() {
        for (Node child : gridLights.getChildren()) {
            // On récupère la ligne où le bouton se trouve.
            Integer row = GridPane.getRowIndex(child);
            if (row == null) {
                row = 0;
            }

            // On récupère la colonne où le bouton se trouve.
            Integer column = GridPane.getColumnIndex(child);
            if (column == null) {
                column = 0;
            }

            if (child instanceof Button button) {
                lightButtons[row][column] = button;
            }
        }

        // Au démarrage, la grille est désactivée jusqu'à ce que le joueur lance une partie.
        gridLights.setDisable(true);
    }

    /**
     * Démarre une nouvelle partie en initialisant la grille et en allumant
     * aléatoirement quelques lumières de façon à garantir l'existence d'une solution.
     * La stratégie consiste à simuler des clics aléatoires sur une grille éteinte :
     * ainsi, la solution existe toujours (il suffit de rejouer les mêmes clics).
     */
    @FXML
    void onNewGame() {
        // On initialise la grille logique (toutes les lumières éteintes).
        gameGrid.init();

        // On simule entre 5 et 15 clics aléatoires pour créer une configuration jouable.
        Random random = new Random();
        int nbClics = 5 + random.nextInt(11);
        for (int i = 0; i < nbClics; i++) {
            int row = random.nextInt(5);
            int col = random.nextInt(5);
            gameGrid.switchAt(row, col);
        }

        // On active la grille pour que le joueur puisse jouer.
        gridLights.setDisable(false);

        // On met à jour l'affichage.
        updateView();
    }

    /**
     * Gère le clic sur une lumière de la grille.
     * Met à jour le modèle et l'affichage, et détecte la fin de partie.
     *
     * @param event L'événement déclenché par le clic sur un bouton.
     */
    @FXML
    void onLightClick(ActionEvent event) {
        // On récupère le bouton cliqué.
        Button button = (Button) event.getSource();

        // On récupère sa position dans la grille.
        Integer row = GridPane.getRowIndex(button);
        if (row == null) {
            row = 0;
        }
        Integer column = GridPane.getColumnIndex(button);
        if (column == null) {
            column = 0;
        }

        // On joue le coup dans le modèle.
        gameGrid.switchAt(row, column);

        // On met à jour l'affichage.
        updateView();

        // Si toutes les lumières sont éteintes, la partie est terminée.
        if (gameGrid.isOff()) {
            gridLights.setDisable(true);
        }
    }

    /**
     * Met à jour l'affichage des boutons de la grille en fonction
     * de l'état des lumières dans le modèle.
     * "*" = lumière allumée, " " = lumière éteinte.
     */
    private void updateView() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (gameGrid.isOn(row, col)) {
                    lightButtons[row][col].setText("*");
                    lightButtons[row][col].setStyle("-fx-background-color: #FFD700; -fx-font-size: 36;");
                } else {
                    lightButtons[row][col].setText(" ");
                    lightButtons[row][col].setStyle("-fx-background-color: #444444; -fx-font-size: 36;");
                }
            }
        }
    }

}