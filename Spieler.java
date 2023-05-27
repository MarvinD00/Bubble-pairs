//represents the player in the game bubble pairs
public class Spieler {
	private int versuche;
	
	//constructor for player, sets versuche to zero
	public Spieler()  {
		this.versuche = 0;
	}
	
	//called when a quadrat object is revealed increments versuche by 1
	public void quadratAufdecken() {
		versuche++;
	}
	
	//returns versuche
	public int getVersuche() {
		return versuche;
	}
}
