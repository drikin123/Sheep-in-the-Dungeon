public class Obstacle extends Entite{

	public Obstacle(int resistance) {
		super(resistance);
	}

	public Obstacle() {
		this(3);
	}

	@Override
	public String toString(String background) {
		String[] a=background.split("");
		if(getRes()==1){
			return a[0]+"@"+a[2];
		}
		else if(getRes()==2){
			return "@@"+a[2];
		}
		else {
			return "@@@";
		}
	}


	public boolean estLibre() {
		return false;
	}

	public static void main(String[] args) {
		Obstacle o=new Obstacle(3);
		System.out.print(o.toString("###"));
	}

	@Override
	public void action(Case courante, Case cible) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entite getContenuapres(Case courante, Case cible) {
		// TODO Auto-generated method stub
		return null;
	}


}