
public class CaseIntraversable extends CaseTraversable{

	public CaseIntraversable(int lig, int col, Entite contenu) {
		super(lig, col, contenu);
	}
	
	public CaseIntraversable(int lig, int col) {
		super(lig, col, null);
	}

	@Override
	public String toString() {
		return "###";
	}

	@Override
	public boolean estLibre() {
		return false;
	}
	
	public static void main(String[] args) {
		Entite b=null;
		CaseTraversable a=new CaseIntraversable(12,15,b);
		System.out.print(a);
	}

}
