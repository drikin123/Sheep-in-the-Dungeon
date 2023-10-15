public abstract class EntiteMobile extends Entite{
	private Direction d;
	public EntiteMobile(int resistance,Direction d) {
		super(resistance);
		this.d=d;
	}

	public Direction getDir(){
		switch (d) {
			case nord:
				return Direction.nord;
			case sud:
				return Direction.sud;
			case est:
				return Direction.est;
			case ouest:
				return Direction.ouest;
		}
		return null;
	}

	public void changeDir() {
		d=Direction.random();
	}

	public void changeDir(EntiteMobile e) {
		e.changeDir();
	}

	public void setDir(Direction a) {
		d=a;
	}

	public abstract void action(Case courante, Case cible);
	public abstract Entite getContenuapres(Case courante, Case cible);
}