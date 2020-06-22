import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Random;

public class sudokuWindow {

	private JFrame frame;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sudokuWindow window = new sudokuWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sudokuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		List<JTextField> list = new ArrayList<JTextField>();

		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

		Sudoku s = new Sudoku(board);
		//On déclare notre classe sudoku
		
		//On initialise notre fenètre en lui donnant les bon paramètres (taille,titre, etc)
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sudoku");

		
		//On déclare tout les éléments d'interface dont nous aurons besoin
		JButton check = new JButton("Vérifier");
		JLabel lblNewLabel = new JLabel("Jeu de sudoku");
		JPanel panel = new JPanel();
		JButton facile = new JButton("Facile");
		JButton moyen = new JButton("Moyen");
		JButton difficile = new JButton("Difficile");
		JButton help = new JButton("Aide");
		JButton giveup = new JButton("Abandon");
		JButton play = new JButton("Play");
		JButton reset = new JButton("Reset");
		JLabel victoire = new JLabel("Bravo");

		
		//On met une bordure de sélection autour du bouton facile
		Border select = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
		facile.setBorder(select);

		//On affiche le titre Jeu de Sudoku de l'interface
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(165, 11, 440, 55);
		frame.getContentPane().add(lblNewLabel);

		
		//On met en place notre grille, il s'agit d'un JPanel délimité en 81 sous jtextfield pour l'input utilisateur
		panel.setBounds(46, 167, 546, 559);
		panel.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 81; i++) {
			JTextField textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			if(i%9==2) {
			Border carre= textField.getBorder();
			Border lines = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
			Border res=BorderFactory.createCompoundBorder(lines, carre);
			textField.setBorder(res);}
			
			//Ce bloc nous permet de dessiner les lignes noires qui séparent les cases
			if(i%9==6) {
				Border carre= textField.getBorder();
				Border lines = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
				Border res=BorderFactory.createCompoundBorder(lines, carre);
				textField.setBorder(res);}
			if(i>17 && i<27) {
				Border carre= textField.getBorder();
				Border lines = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
				Border res=BorderFactory.createCompoundBorder(lines, carre);
				textField.setBorder(res);}
			if(i>53 && i<63) {
				Border carre= textField.getBorder();
				Border lines = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK);
				Border res=BorderFactory.createCompoundBorder(lines, carre);
				textField.setBorder(res);}
			
			//Permet de remettre le texte noir quand l'utilisateur sélectionne une case
			textField.addFocusListener(new FocusListener(){
	            @Override
	            public void focusGained(FocusEvent e) {
	                textField.setForeground(Color.BLACK);
	            }
	            @Override
	            public void focusLost(FocusEvent e) {
	            }
	        });

			//On affiche la case et on l'ajoute à une liste pour faciliter l'accès.
			panel.add(textField);
			list.add(textField);

		}
		frame.getContentPane().add(panel);

		//On ajoute le bouton facile et on fait en sorte que la difficulté choisie soit entourée de noir
		facile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.setDif(1);
				Border select = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
				facile.setBorder(select);
				moyen.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				difficile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			}
		});
		facile.setBounds(644, 167, 89, 23);
		frame.getContentPane().add(facile);
		
		//On ajoute le bouton moyen et on fait en sorte que la difficulté choisie soit entourée de noir
		moyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.setDif(2);
				Border select = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
				moyen.setBorder(select);
				facile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				difficile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			}
		});
		moyen.setBounds(644, 204, 89, 23);
		frame.getContentPane().add(moyen);

		//On ajoute le bouton difficile et on fait en sorte que la difficulté choisie soit entourée de noir
		difficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.setDif(3);
				Border select = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
				difficile.setBorder(select);
				moyen.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				facile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			}
		});
		difficile.setBounds(644, 238, 89, 23);
		frame.getContentPane().add(difficile);

		//On définit la zone de texte de victoire ou d'abandon pour usage plus tard
		victoire.setHorizontalAlignment(SwingConstants.CENTER);
		victoire.setForeground(Color.RED);
		victoire.setFont(new Font("Tahoma", Font.PLAIN, 50));
		victoire.setBounds(180, 67, 404, 86);

		//Le bouton vérifier va s'assurer que toutes les cases sont bien remplies, si il y a une erreur on va changer la police en rouge, si toutes les cases sont remplies
		//correctement on va afficher le fait que l'utilisateur à gagné
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = 0;
				int b = 0;
				int score = 0;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (list.get(a).getText().isEmpty()) {
							b = 0;
						} else {
							try {
							//On va passer du string du text field à un int que nos fonction peuvent utiliser
							//Pour gérer les inputs qui ne sont pas des ints, on va utiliser un try catch sur l'exception NumberFormatException
							b = Integer.parseInt(list.get(a).getText());
							}catch(NumberFormatException e) {
							b=0;
							}
						}
						if (b == s.getSol(i, j)) {
							s.fillIn(i, j, b);
							score++;
							list.get(a).setForeground(Color.black);
						} else {
							if (!list.get(a).getText().isEmpty()) {
								list.get(a).setForeground(Color.red);
							}

						}
						a++;
					}
				}
				if (score == 81) {
					frame.getContentPane().add(victoire);
					victoire.setText("Bravo");
					frame.remove(giveup);
				}
				frame.repaint();

			}
		});
		check.setBounds(632, 168, 99, 38);

		//La fonction va chercher une case vide et la remplir avec une valeur de la table de solution
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = 0;
				int b = 0;
				boolean goodnumber = false;
				Random rand = new Random();
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (s.getNum(i, j) == 0) {
							goodnumber = true;
						}
					}
				}
				while (goodnumber) {
					a = rand.nextInt(9);
					b = rand.nextInt(9);
					if (s.getNum(a, b) == 0) {
						goodnumber = false;
					}
				}
				s.fillIn(a, b, s.getSol(a, b));
				list.get(a * 9 + b).setText(String.valueOf(s.getNum(a, b)));
			}
		});
		help.setBounds(632, 217, 99, 32);

		
		//Bouton abandon qui va simplement faire afficher le texte abandon et forcer le joueur à appuyer sur le bouton reset
		giveup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = 0;

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						list.get(a).setText(String.valueOf(s.getSol(i, j)));
						a++;
					}
				}

				frame.getContentPane().add(victoire);
				victoire.setText("Abandon");
				frame.remove(check);
				frame.repaint();
			}
		});
		giveup.setBounds(632, 260, 99, 32);

		
		//Va forcer l'utilisateur à choisir une difficulté et va relancer la procédure de génération de grille de sudoku
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s.reset();
				frame.remove(victoire);
				for (int i = 0; i < 81; i++) {
					list.get(i).setText("");
				}
				frame.getContentPane().add(difficile);
				frame.getContentPane().add(moyen);
				frame.getContentPane().add(facile);
				frame.getContentPane().add(play);
				frame.remove(reset);
				frame.remove(help);
				frame.remove(check);
				frame.remove(giveup);
				frame.repaint();
			}
		});
		reset.setBounds(632, 305, 99, 38);

		//Va lancer la partie et générer la grille a partir de la difficulté choisie
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(difficile);
				frame.remove(moyen);
				frame.remove(facile);
				frame.remove(play);
				frame.getContentPane().add(reset);
				frame.getContentPane().add(help);
				frame.getContentPane().add(check);
				frame.getContentPane().add(giveup);
				s.Game();
				
				int a = 0;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (s.getNum(i, j) != 0) {
							list.get(a).setText(String.valueOf(s.getNum(i, j)));
							list.get(a).setForeground(Color.BLACK);
						}
						a++;
					}
				}	
				frame.repaint();
			}
		});
		play.setBounds(337, 112, 89, 23);
		frame.getContentPane().add(play);
		
		


	}
}
