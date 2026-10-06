# Ligths-Off

Jeu de réflexion **Lights Out** réalisé en **Java** avec **JavaFX**, dans le cadre
du cours d'IHM (BUT Informatique, IUT d'Artois à Lens).

Le but : éteindre toutes les lumières de la grille. Cliquer sur une lumière
inverse son état ainsi que celui de ses quatre voisines (haut, bas, gauche, droite).

## Fonctionnalités

- Grille de 5x5 lumières
- Génération d'une partie aléatoire **toujours résoluble** : la grille de départ est
  obtenue en simulant des clics aléatoires sur une grille éteinte, il existe donc
  toujours une solution
- Bouton pour lancer une nouvelle partie à tout moment
- Détection de la victoire : la grille se désactive quand toutes les lumières sont éteintes

## Règles du jeu

1. Clique sur **Nouvelle partie** pour générer une grille.
2. Un clic sur une lumière inverse son état et celui de ses voisines directes.
3. Tu gagnes quand toutes les lumières (jaunes) sont éteintes (grises).

## Prérequis

- Java 17 ou supérieur
- Rien d'autre : Gradle est fourni via le wrapper (`gradlew`)

## Lancer le jeu

```bash
git clone https://github.com/lights-off.git
cd light-off
./gradlew run
```

Sous Windows : `gradlew.bat run`

## Architecture

Le projet suit le principe **modèle / vue / contrôleur** :

| Classe / fichier | Rôle |
|---|---|
| `GameGrid` | Modèle : grille logique 5x5, inversion d'une lumière et de ses voisines, test de victoire |
| `lightsOffController` | Contrôleur : gère les clics, génère les parties, met à jour l'affichage |
| `lightsOff.fxml` | Vue : description de l'interface (grille de boutons) |
| `lightsOffApplication` | Point d'entrée de l'application JavaFX |

Le code source se trouve dans `src/main/` (package `fr.univartois.butinfo.ihm`).

## Technologies

Java, JavaFX (FXML), Gradle

## Auteure

Alice DEMEY — BUT Informatique, IUT d'Artois (Lens)
