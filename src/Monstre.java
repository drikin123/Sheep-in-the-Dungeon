public class Monstre extends EntiteMobile{


	public Monstre(Direction d) {
		super(3,d);
	}

	@Override
	public String toString(String background) {
		String[] a=background.split("");
		switch (getDir()) {
			case nord:return a[0]+"m"+a[2];
			case sud:return a[0]+"w"+a[2];
			case est:return a[0]+"»"+a[2];
			case ouest:return a[0]+"«"+a[2];
		}
		return null;
	}

	@Override
	public void action(Case courante, Case cible) {
		EntiteMobile a=(EntiteMobile)courante.getContenu();
		CaseTraversable co=(CaseTraversable) courante;
		CaseTraversable ci=(CaseTraversable) cible;
		co.entre(a);
		courante=co;
		if(ci.estLibre()) {
			ci.entre(co.getContenu());
			co.vide();
			courante=co;
			cible=ci;
		}
		else if(ci.getContenu() instanceof Obstacle || ci.getContenu() instanceof Personnage|| ci.getContenu() instanceof Joueur) {
			resDim(ci.getContenu());
			cible=ci;
			if(mort(ci.getContenu())) {
				ci.vide();
				cible=ci;
			}
		}

	}

	@Override
	public Entite getContenuapres(Case courante, Case cible) {
		action(courante,cible);
		return cible.getContenu();
	}


	public static void main(String[] args){
		Direction d=Direction.est;
		Monstre a=new Monstre(d);
		System.out.println(a.getDir());
		System.out.print(a.toString("###"));
	}

}