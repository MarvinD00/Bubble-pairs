//class that represents all "quadrat" objects on the board 
public class Spielfeld {
	private Quadrat[] quadrate;

	//constructor for Spielfeld
	public Spielfeld() {
		quadrate = new Quadrat[12];
		for(int i=0; i<12; i++) {
			quadrate[i] = new Quadrat(i+1);
		}
	}
	
	//fills quadrate with new "quadrat" objects and calls farbenVergeben
	public void resetGame() {
		quadrate = new Quadrat[12];
		for(int i=0; i<12; i++) {
			quadrate[i] = new Quadrat(i+1);
		}
		farbenVergeben();
	}
	
	//gives every "quadrat" object in quadrate a random "Farbe" but every "Farbe" must be assigned exactly twice
	public void farbenVergeben() {
		int[] farbZahlen = new int[12];
		for(int i=0;i<12; i++) {
			int counter = 0;
			int randomNum = (int)Math.round(Math.random() * (6 - 1)+ 1);
			for(int j=0;j<12;j++) {
				if(farbZahlen[j] == randomNum) {
					counter++;
				}
				if(counter == 2) {
					i--;
					break;
				}
			}
			if(counter<2) {
				farbZahlen[i] = randomNum;
			}
		}
		for(int i=0; i<12; i++) {
			switch(farbZahlen[i]) {
			case 1: quadrate[i].setColor(Farbe.ROT); break;
			case 2: quadrate[i].setColor(Farbe.GELB); break;
			case 3: quadrate[i].setColor(Farbe.GRUEN); break;
			case 4: quadrate[i].setColor(Farbe.ORANGE); break;
			case 5: quadrate[i].setColor(Farbe.BLAU); break;
			case 6: quadrate[i].setColor(Farbe.SCHWARZ); break;
			}
		}
	}
	
	//if every "quadrat" object in quadrate has value entfernt == true then this returns true
	public boolean checkWinner() {
		int entfernteQuadrate = 0;
		for(int i=0; i<12; i++) {
			if(quadrate[i].getEntfernt()) {
				entfernteQuadrate++;
			}
		}
		if(entfernteQuadrate == 12) {
			return true;
		} else return false;
	}
	
	//returns value of color of "quadrat" object in quadrate at position "nummer"
	public Farbe getQuadratFarbe(int nummer) {
		return quadrate[nummer].getColor();
		
	}
	
	//return quadrate array
	public Quadrat[] getQuadrate() {
		return quadrate;
	}
	
	//switches position of "quadrat" objects in array quadrate 
	//  i represents position
	// if oldposition<9 then newposition = oldposition+3
	// if oldposition>=9 then newposition = oldposition-9
	public void drehen()  {
		Quadrat[] quadrateTemp = new Quadrat[12];
		
		for(int i=0; i<12; i++) {
			quadrateTemp[i] = quadrate[i];
		}
		
		for(int i=0; i<12; i++) {
			if(i<9) {
				quadrate[i+3] = quadrateTemp[i];
			} else if(i>=9) {
				quadrate[i-9] = quadrateTemp[i];
			  }
		}
	}
	
	//sets the entfernt value of "quadrat" object in quadrate at position "quadratPos" to true
	public void quadratEntfernen(int quadratPos) {
		quadrate[quadratPos].setEntfernt(true);
	}

	//used for testing purposes not used in the game
	public String toString() {
		String returnString ="";
		for(int i=0; i<12; i++) {
			returnString += quadrate[i];
		}
		return returnString;
	}
	
	
}
