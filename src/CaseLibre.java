

public class CaseLibre extends CaseTraversable{
	public CaseLibre(int lig, int col) {
		super(lig, col, null);
	}
	
	public CaseLibre(int lig, int col, Entite e) {
		super(lig, col, e);
	}
	
	@Override
	public String toString() {
		return "   ";
	}
	
	@Override
	public String toString(String background) {
		if(getContenu() instanceof Entite) {
			return getContenu().toString(background);
		}
		return "   ";
	}
	
	@Override
	public boolean estLibre() {
		return getContenu()==null;
	}
	
	
}
