public class Jeu {

    Terrain terrain;
    static int sortis=0;
    static boolean berg=false;
    static boolean kil=false;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
    }

    public void tour() {


        this.terrain.mmtemps2();
        this.terrain.print();


    }

    public void droitej() {
        this.terrain.droite();
        this.terrain.print();

    }

    public void gauchej() {
        this.terrain.gauche();
        this.terrain.print();

    }

    public void hautj() {
        this.terrain.haut();
        this.terrain.print();

    }

    public void basj() {
        this.terrain.bas();
        this.terrain.print();

    }

    public boolean partieFinie() {
        int a=0;
        int b=0;
        int c=0;
        for (int h=0; h<this.terrain.getHauteur(); h++) {
            for (int l=0; l<this.terrain.getLargeur(); l++) {
                if(terrain.getCarte(h, l).getContenu() instanceof Personnage) {
                    a++;
                }
                if(terrain.getCarte(h, l).getContenu() instanceof Joueur) {
                    b++;
                }

                if(terrain.getCarte(h, l).getContenu() instanceof Monstre) {
                    c++;
                }
            }
        }
        return a==0 || b==0 || c==0 || berg==true;
    }

    public static void main(String[] args) {
        Jeu j = new Jeu("laby2.txt");
        j.terrain.print();
        int a=0;
        int b=0;
        for (int h=0; h<j.terrain.getHauteur(); h++) {
            for (int l=0; l<j.terrain.getLargeur(); l++) {
                if(j.terrain.getCarte(h, l).getContenu() instanceof Personnage) {
                    b++;
                }
            }
        }
        while(j.partieFinie()==false) {
            j.tour();
            for (int h=0; h<j.terrain.getHauteur(); h++) {
                for (int l=0; l<j.terrain.getLargeur(); l++) {
                    if(j.terrain.getCarte(h, l).getContenu() instanceof Personnage) {
                        a++;
                    }
                }
            }
            if(a==0) {
                b-=sortis;
                System.out.println();
                System.out.println("Il y a eu "+sortis+" personnage(s) sauve(s) et "+b+" personnage(s) mange(s)!");
                break;
            }
            a=0;
        }

    }

}