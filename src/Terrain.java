import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Terrain {

	private int hauteur, largeur;
	private Case[][] carte;

	/* Initialisation d'un terrain Ã  partir de la description donnÃ©e par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la premiÃ¨re ligne
       - puis dessin du terrain (un caractÃ¨re == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
	public Terrain(String file) {
		try {
			Scanner sc = new Scanner(new FileInputStream(file));
			this.hauteur = sc.nextInt();
			this.largeur = sc.nextInt();
			int resistanceJoueur = sc.nextInt();
			sc.nextLine();
			this.carte = new Case[hauteur][largeur];
			for (int l=0; l<hauteur; l++) {
				String line = sc.nextLine();
				for (int c=0; c<largeur; c++) {
					Case cc;
					Character ch = line.charAt(c);
					switch (ch) {
						case '#': cc = new CaseIntraversable(l, c); break;
						case ' ': cc = new CaseLibre(l, c); break;
						case 'o': cc = new Sortie(l, c); break;
						case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
						case 'H': cc = new CaseLibre(l, c, new Joueur(resistanceJoueur)); break;
						case 'K': cc = new CaseLibre(l, c, new Killer(10)); break;
						case '^': case '>': case 'v': case '<':
							cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
							break;
						case 'm': case '»': case 'w': case '«':
							cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
							break;
						default:  cc = null; break;
					}
					carte[l][c] = cc;
				}
			}
			sc.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}

	public void print() {
		for(Case[] c : carte) {
			System.out.println();
			for(Case m : c) {
				if(m instanceof CaseLibre) {
					System.out.print(m.toString(m.toString()));
				}else{System.out.print(m);}
			}
		}
	}

	public Case[][] getCarte() {
		return carte;
	}

	public Case getCarte(int h,int l) {
		return carte[h][l];
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getHauteur(Terrain t) {
		return t.getHauteur();
	}

	public int getLargeur() {
		return largeur;
	}

	public int getLargeur(Terrain t) {
		return t.getLargeur();
	}

	public void setCarte(int h,int l,int h2,int l2) {
		carte[h][l]=carte[h2][h2];
	}

	public Entite test(int h,int l,int h2,int l2) {
		return ((Entite) carte[h][l].getContenu()).getContenuapres(carte[h][l],carte[h2][l2]);
	}

	public void droitek() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Killer) {
					test(h,l,h,l+1);l++;
				}
			}
		}
	}


	public void bask() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Killer) {
					test(h,l,h+1,l);h++;
				}
			}
		}
	}


	public void gauchek() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Killer) {
					test(h,l,h,l-1);
				}
			}
		}
	}



	public void hautk() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Killer) {
					test(h,l,h-1,l);
				}
			}
		}
	}

	public void droite() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					test(h,l,h,l+1);l++;
				}
			}
		}
	}


	public void bas() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					test(h,l,h+1,l);h++;
				}
			}
		}
	}


	public void gauche() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					test(h,l,h,l-1);
				}
			}
		}
	}



	public void haut() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					test(h,l,h-1,l);
				}
			}
		}
	}

	public boolean sort() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					if(carte[h][l+1].getContenu() instanceof Obstacle || carte[h][l-1].getContenu() instanceof Obstacle || carte[h+1][l].getContenu() instanceof Obstacle || carte[h-1][l].getContenu() instanceof Obstacle) {
						return true;
					}
				}
			}
		}
		return false;
	}


	public void videB() {
		for (int h=0; h<this.hauteur; h++) {
			for (int l=0; l<this.largeur; l++) {
				if(this.getCarte(h, l).getContenu() instanceof Joueur) {
					((CaseTraversable) carte[h][l]).vide();
				}
			}
		}
	}

	public void mmtemps2() {
		for (int h=0; h<hauteur; h++) {
			for (int l=0; l<largeur; l++) {
				if(carte[h][l].getContenu() instanceof Personnage ) {
					Random rand = new Random();
					int rand_int = rand.nextInt(2);
					if(carte[h][l+1] instanceof CaseIntraversable==false && carte[h][l+1].getContenu() instanceof Joueur==false && rand_int==0) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.est);test(h,l,h,l+1);l++;}
					else if(carte[h+1][l] instanceof CaseIntraversable==false && carte[h+1][l].getContenu() instanceof Joueur==false && rand_int==0) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.sud);test(h,l,h+1,l);h++;}
					else if(carte[h][l-1] instanceof CaseIntraversable==false && carte[h][l-1].getContenu() instanceof Joueur==false && rand_int==1) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.ouest);test(h,l,h,l-1);}
					else if(carte[h-1][l] instanceof CaseIntraversable==false && carte[h-1][l].getContenu() instanceof Joueur==false && rand_int==1) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.nord);test(h,l,h-1,l);}
				}
				else if(carte[h][l].getContenu() instanceof Monstre ) {
					Random rand = new Random();
					int rand_int = rand.nextInt(2);
					if(carte[h][l+1] instanceof CaseIntraversable==false && carte[h][l+1].getContenu() instanceof Monstre==false && rand_int==1) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.est);test(h,l,h,l+1);l++;}
					else if(carte[h+1][l] instanceof CaseIntraversable==false && carte[h][l+1].getContenu() instanceof Monstre==false && rand_int==1) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.sud);test(h,l,h+1,l);h++;}
					else if(carte[h][l-1] instanceof CaseIntraversable==false && carte[h][l-1].getContenu() instanceof Monstre==false && rand_int==0) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.ouest);test(h,l,h,l-1);}
					else if(carte[h-1][l] instanceof CaseIntraversable==false && carte[h-1][l].getContenu() instanceof Monstre==false && rand_int==0) {((EntiteMobile) carte[h][l].getContenu()).setDir(Direction.nord);test(h,l,h-1,l);}
				}
			}
		}
	}




	public static void main(String[] args) {
		Terrain a=new Terrain("laby2.txt");
		a.print();
		a.mmtemps2();

		a.print();
	}

}