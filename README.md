# ProjetJava2020
Projet sudoku en java 2020 pour l'ESIREM


J'ai choisi le projet Sudoku et j'ai réalisé toutes les fonctions demandées, j'ai également ajouté une interface graphique complète ainsi qu'un executable JAR.
-Le programme va générer une grille selon 3 paramètres de difficulté, de facile à difficile, ensuite l'utilisateur va pouvoir saisir les bonnes valeurs dans les cases(prise en charge des exceptions en lettres ou format de nombres)
-Il y a un bouton vérifier qui permet de surligner les valeurs érronées en rouge
-Il y a également une fonction aide qui va remplir une case aléatoire de la grille pour aider l'utilisateur
-Si l'utilisateur renonce au jeu, le programme affiche Abandon et lui laisse la possibilité de recommencer une grille avec une difficulté différente et affiche la grille complétee.


Pour générer mes grilles, l'algorithme s'effectue récursivement en plusieurs fois pour s'assurer que chaque grille générée ne peut avoir qu'une seule solution (donc une grille valable), le problème de cette méthode est que pour des valeurs élevées de chiffres à enlever le temps d'exécution augmente de beaucoup donc il n'est pas rare de devoir attendre plusieurs secondes pour avoir une grille de difficulté difficile, il serait possible d'améliorer ça en parallélisant le calcul.Ou en recommençant la génération si l'on s'apperçoit que celle-ci prends trop de temps.

Des améliorations possibles au programme auraient été de rajouter un timer pour que l'utilisateur ait un autre but à atteindre et un indicateur d'aide pour afficher combien de fois l'utilisateur a appuyé de fois sur le bouton aide.

Pour compiler le programme il suffit d'utiliser les deux fichiers java fournis, le main se trouve sur sudokuWindow. Ou alors il est simplement possible de lancer le fichier executable JAR directement pour le tester.

Il faut bien attendre si en sélectionnant difficile la grille met longtemps à arriver. (potentiellement 10 secondes ou un peu plus).
