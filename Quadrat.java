//a single quadrat which represents a card in the game bubble pairs
public class Quadrat {
	
	private boolean entfernt;
	private int nummer; //used for testing purposes not used in the game
	private Farbe farbe;
	
	//constructor for quadrat sets entfernt to false
	public Quadrat(int nummer) {
		this.entfernt = false;
	}

	//sets value of entfernt to given parameter
	public void setEntfernt(boolean entfernt)  {
		this.entfernt = entfernt;
	}
	
	//sets value of color to given parameter
	public void setColor(Farbe farbe) {
		this.farbe = farbe;
	}
	
	//returns value of farbe
	public Farbe getColor() {
		return farbe;
	}
	
	//returns value of entfernt
	public boolean getEntfernt() {
		return this.entfernt;
	}
	
	//used for testing purposes not used in the game
	public String toString() {
		return Integer.toString(nummer) +" ";
	}
	
}
