

public class Sortie extends CaseTraversable{

	public Sortie(int lig, int col, Entite contenu) {
		super(lig, col, contenu);
	}
	
	public Sortie(int lig, int col) {
		super(lig, col, null);
	}

	@Override
	public String toString() {
		return "( )";
	}

	@Override
	public boolean estLibre() {
		return true;
	}

}
