
public abstract class Case {
	private int lig;
	private int col;
	
	public Case(int lig, int col) {
		this.lig=lig;
		this.col=col;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getLig() {
		return lig;
	}
	
	public int getCol(Case e) {
		return e.col;
	}
	
	public int getLig(Case e) {
		return e.lig;
	}
	
	public void setCoLig(int a,int b) {
		this.lig=a;
		this.col=b;
	}
	
	public abstract boolean estLibre(); 
	public abstract String toString(String background);
	public abstract void action(Case carte, Case carte2);
	public abstract Entite getContenu();

}
