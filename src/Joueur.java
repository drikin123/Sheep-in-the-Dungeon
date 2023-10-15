public class Joueur extends Entite{

    public Joueur(int resistance) {
        super(resistance);
    }

    @Override
    public String toString(String background) {
        String[] a=background.split("");
        return a[0]+"$"+a[2];
    }


    @Override
    public void action(Case courante,Case cible) {
        Entite a=(Entite)courante.getContenu();
        CaseTraversable co=(CaseTraversable) courante;
        CaseTraversable ci=(CaseTraversable) cible;
        co.entre(a);
        courante=co;
        if(courante instanceof Sortie && Jeu.berg==true) {
            co.vide();
            courante=co;
        }
        else if(ci.estLibre()) {
            ci.entre(co.getContenu());
            co.vide();
            courante=co;
            cible=ci;
        }
        else if(ci.getContenu() instanceof Obstacle) {
            resDim(ci.getContenu());
            cible=ci;
            if(mort(ci.getContenu())) {
                ci.vide();
                cible=ci;
            }
        }
        else if(ci.getContenu() instanceof Monstre) {
            resDim(co.getContenu());
            courante=co;
            if(mort(co.getContenu())) {
                co.vide();
                courante=co;
                Jeu.sortis/=2;
            }
        }
		/*Entite a=(Entite)courante.getContenu();
		CaseTraversable co=(CaseTraversable) courante;
		CaseTraversable ci=(CaseTraversable) cible;
		co.entre(a);
		courante=co;
		if(ci.getContenu() instanceof Obstacle) {
			resDim(ci.getContenu());
			cible=ci;
			if(mort(ci.getContenu())) {
				ci.vide();
				cible=ci;
			}
		}
		else if(ci.getContenu() instanceof Monstre) {
			resDim(co.getContenu());
			courante=co;
			if(mort(co.getContenu())) {
				co.vide();
				courante=co;
			}
		}*/
    }

    @Override
    public Entite getContenuapres(Case courante, Case cible) {
        action(courante,cible);
        return cible.getContenu();
    }

    public static void main(String[] args) {
        Joueur a=new Joueur(50);
        System.out.println(a.toString("###"));
    }


}