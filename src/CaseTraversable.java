public class CaseTraversable extends Case{
	private Entite contenu;

	public CaseTraversable(int lig, int col, Entite contenu) {
		super(lig, col);
		this.contenu=contenu;
	}

	@Override
	public boolean estLibre() {
		if(this.contenu==null) {
			return true;
		}
		return false;
	}

	public int getRes(Entite e) {
		return e.getRes();
	}

	@Override
	public Entite getContenu() {
		return contenu;
	}

	public void vide() {
		this.contenu=null;
	}

	public void feu() {
		this.contenu=new Fire(100);
	}

	public void entre(Entite e) {
		this.contenu=e;
	}

	@Override
	public String toString(String background) {
		// TODO Auto-generated method stub
		return background;
	}

	@Override
	public void action(Case carte, Case carte2) {
		carte2=carte;

	}


}