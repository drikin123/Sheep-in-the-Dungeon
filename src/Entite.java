public abstract class Entite{
	private int resistance;

	public Entite(int resistance) {
		this.resistance=resistance;
	}

	public int getRes(Entite e) {
		return e.resistance;
	}

	public int getRes() {
		return resistance;
	}

	public void resDim() {
		this.resistance--;
	}

	public void resDim(Entite e) {
		e.resDim();
	}

	public boolean mort() {
		return resistance==0;
	}
	public boolean mort(Entite e) {
		return e.mort();
	}

	public int getCol(Case e) {
		return e.getCol();
	}

	public int getLig(Case e) {
		return e.getLig();
	}

	public void setLigCol(Case e,int a, int b) {
		e.setCoLig(a,b);
	}



	public abstract String toString(String background);
	public abstract void action(Case courante, Case cible);
	public abstract Entite getContenuapres(Case courante, Case cible);

}