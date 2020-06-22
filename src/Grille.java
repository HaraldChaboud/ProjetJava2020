import java.util.Random;

class Sudoku {

	//On déclare deux grilles une pour la grille en cours, une pour la grille finale
	//On déclare aussi une variable pour la difficulté et sample est la liste de nombre pour l'aléatoire du solveur
	private int[][] sudoku;
	private int[][] sudokuSol;
	private int diffi = 1;
	private int[] sample;

	//Constructeur par défaut
	public Sudoku() {
		sudoku = new int[9][9];
		sudokuSol = new int[9][9];
		sample = new int[9];
	}
	//Constructeur avec paramètre
	public Sudoku(int sudoku[][]) {
		this.sudoku = sudoku;
		sample = new int[9];
		sudokuSol = new int[9][9];
	}

	//Getter et setters
	public void setDif(int a) {
		diffi = a;
	}

	public int getNum(int a, int b) {
		return sudoku[a][b];
	}

	public int getSol(int a, int b) {
		return sudokuSol[a][b];
	}

	
	//Remet la grille sudoku à zéro
	public void reset() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = 0;
			}
		}
	}

	//Fonction qui vérifie qu'un nombre soit valide pour une ligne donnée
	private boolean isAlligne(int ligne, int num) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[ligne][i] == num) {
				return true;
			}
		}
		return false;
	}

	//Fonction qui vérifie qu'un nombre soit valide pour une colonneonne donnée
	private boolean isAlcolonne(int colonne, int num) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][colonne] == num) {
				return true;
			}
		}
		return false;
	}

	//Fonction qui vérifie qu'un nombre soit valide pour une zone de 9x9 donnée
	private boolean isAlBox(int ligne, int colonne, int num) {
		int groupeX = ligne - ligne % 3;
		int groupeY = colonne - colonne % 3;
		for (int i = groupeX; i < groupeX + 3; i++) {
			for (int j = groupeY; j < groupeY + 3; j++) {
				if (sudoku[i][j] == num) {
					return true;
				}
			}

		}
		return false;
	}

	//Utilise les trois fonctions définies précédemment pour vérifier la validité d'un nombre
	private boolean isAl(int ligne, int colonne, int num) {
		return !(isAlligne(ligne, num) || isAlcolonne(colonne, num) || isAlBox(ligne, colonne, num));
	}

	
	//Va rendre l'ordre des chiffres du solveur aléatoire pour générer une grille différente à chaque fois
	public void genSample() {
		int cmp;
		Random rand = new Random();
		boolean there = true;
		sample = new int[9];
		for (int i = 0; i < 9; i++) {
			there = true;
			while (there) {
				cmp = 0;
				sample[i] = rand.nextInt(9) + 1;
				for (int j = 0; j < 9; j++) {
					if (sample[j] == sample[i]) {
						cmp++;
					}
				}
				if (cmp == 1) {
					there = false;
				}
			}
		}
	}

	//Solveur par récursion est utilisé pour créer la grille et vérifier à chaque fois qu'on enlève un chiffre
	//l'ordre des chiffres qui se trouve dans genSample est aléatoire à chaque itération
	private boolean solve() {
		genSample();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					for (int k = 0; k < 9; k++) {

						if (isAl(i, j, sample[k])) {
							sudoku[i][j] = sample[k];
							if (solve()) {
								return true;
							} else {
								sudoku[i][j] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	//Fonction qui utilise plusieurs itérations du solveur pour s'assurer que chaque configuration n'a qu'une seule solution possible
	private void createGrid() {
		Copy(sudokuSol, sudoku);
		boolean wrongNum = true;
		boolean yes = false;
		int a = 0;
		int b = 0;
		int i = 0;
		int dif2 = 0;
		switch (diffi) {
		case 1:
			dif2 = 25;
			break;
		case 2:
			dif2 = 35;
			break;
		case 3:
			dif2 = 40;
			break;
		}
		int backup[][] = new int[9][9];
		int backup2[][] = new int[9][9];
		int backup3[][] = new int[9][9];
		Copy(backup, sudoku);
		Random rand = new Random();

		while (i < dif2) {
			yes = true;
			wrongNum = true;
			Copy(backup2, sudoku);
			while (wrongNum) {
				a = rand.nextInt(9);
				b = rand.nextInt(9);
				if (sudoku[a][b] != 0) {
					wrongNum = false;
				}
			}
			sudoku[a][b] = 0;
			Copy(backup3, sudoku);
			for (int j = 0; j < 3; j++) {
				solve();
				if (isEqual(sudoku, backup)) {
					Copy(sudoku, backup3);
				} else {
					Copy(sudoku, backup2);
					Copy(backup3, backup2);
					yes = false;
				}
			}
			if (yes) {
				i++;
			}

		}
	}

	//Simple fonction pour copier les contenus d'une grille dans une autre
	public void Copy(int a[][], int b[][]) {
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				a[i][j] = b[i][j];
			}

		}
	}

	//Nous permet de comparer et de savoir si deux grilles sont égales
	public boolean isEqual(int a[][], int b[][]) {
		int cmp = 0;
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				if (a[i][j] != b[i][j]) {
					cmp++;
				}
			}

		}
		if (cmp > 0) {
			return false;
		} else {
			return true;
		}
	}

	//Va remplir les cases dans le sudoku mais vérifie les conditions de placement avant
	public boolean fillIn(int ligne, int colonne, int num) {
		if (this.isAl(ligne, colonne, num)) {
			sudoku[ligne][colonne] = num;
		} else {
			return false;
		}
		return true;
	}
	
	//fonction qui permet de générer completement une grille valide
	public void Game() {
		solve();
		createGrid();
	}

}