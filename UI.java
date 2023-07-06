import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;
import javax.imageio.ImageIO;
 


public class UI extends JFrame implements ActionListener {
	
	//---------------------------------------------//
	//--------------instantiation------------------//
	//---------------------------------------------//
	
	//panels
	JPanel menuPanel = new JPanel();
	JPanel anleitungsPanel = new JPanel();
	JPanel spielPanel = new JPanel();
	JPanel tButtonPanel = new JPanel();
	JPanel bButtonPanel = new JPanel();
	JPanel lsideButtonPanel = new JPanel();
	JPanel rsideButtonPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	
	//12 buttons for the game(cards)
	JButton spielButtons[] = new JButton[12];
	{
	for(int i=0;i<12;i++) {
		spielButtons[i] = new JButton();
		spielButtons[i].setBackground(Color.black);
		spielButtons[i].setPreferredSize(new Dimension(100,100));
		spielButtons[i].addActionListener(this);
	}
	}
	
	//menu Buttons
	JButton menuButtons[] = new JButton[3];
	{
	for(int i=0;i<3;i++) {
		menuButtons[i] = new JButton();
		menuButtons[i].setPreferredSize(new Dimension(200,80));
		menuButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		menuButtons[i].addActionListener(this);
	}
	}
	
	//spieler/spielfeld
	static Spieler spieler = new Spieler();
	static Spielfeld spielfeld = new Spielfeld();
	
	//------------images/URLS/icons---------------//
	URL URL_roterKreis = this.getClass().getResource( "/roter kreis.png" );
	URL URL_gelberKreis = this.getClass().getResource( "/gelber kreis.png" );
	URL URL_schwarzerKreis = this.getClass().getResource( "/schwarzer kreis.png" );
	URL URL_blauerKreis = this.getClass().getResource( "/blauer kreis.png" );
	URL URL_orangenerKreis = this.getClass().getResource( "/orangener kreis.png" );
	URL URL_gruenerKreis = this.getClass().getResource( "/gruener kreis.png" );
	URL URL_pfeil = this.getClass().getResource("/pfeil.jpg");
	URL URL_icon = this.getClass().getResource("/Icon.jpg");
	Icon roterKreisIcon = new ImageIcon(URL_roterKreis);
	Icon gelberKreisIcon = new ImageIcon(URL_gelberKreis);
	Icon schwarzerKreisIcon = new ImageIcon(URL_schwarzerKreis);
	Icon blauerKreisIcon = new ImageIcon(URL_blauerKreis);
	Icon orangenerKreisIcon = new ImageIcon(URL_orangenerKreis);
	Icon gruenerKreisIcon = new ImageIcon(URL_gruenerKreis);
	Icon pfeilIcon = new ImageIcon(URL_pfeil);
	Icon iconIcon = new ImageIcon(URL_icon);
	Image roterKreis = Toolkit.getDefaultToolkit().createImage(URL_roterKreis);
	Image gelberKreis = Toolkit.getDefaultToolkit().createImage(URL_gelberKreis);
	Image schwarzerKreis = Toolkit.getDefaultToolkit().createImage(URL_schwarzerKreis);
	Image blauerKreis = Toolkit.getDefaultToolkit().createImage(URL_blauerKreis);
	Image orangenerKreis = Toolkit.getDefaultToolkit().createImage(URL_orangenerKreis);
	Image gruenerKreis = Toolkit.getDefaultToolkit().createImage(URL_gruenerKreis);
	Image pfeil = Toolkit.getDefaultToolkit().createImage(URL_pfeil);
	Image iconImage = Toolkit.getDefaultToolkit().createImage(URL_icon);
	
	//buttons that have been selected and their numbers
	AbstractButton selectedButton1;
	int selectedButton1Number;
	AbstractButton selectedButton2;
	int selectedButton2Number;
	
	//timer to wait after second button has been selected (2s)
	Timer timer = new Timer(1000, new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent arg0) {
		    buttonClickEvent2();
		  }
		});
	{timer.setRepeats(false);}
	
	//arrow image button (unusable)
	JButton arrow = new JButton();
	{
	arrow.setIcon(pfeilIcon);
	arrow.setEnabled(false);
	}
	
	//--------------------------------------------------//
	//--------------instantiation end-------------------//
	//--------------------------------------------------//
	
	//UI constructor
	public UI() {
		this.setTitle("Bubble Pairs");
        this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(iconImage);
		
		//initialize panels
		tButtonPanel();
		bButtonPanel();
		lsideButtonPanel();
		rsideButtonPanel();
		centerPanel();
		spielPanel();
		menuPanel();
		
		this.setContentPane(menuPanel);
		this.pack();
	}
	
	//--------------------------------------------------//
	//--------------panel construction------------------//
	//--------------------------------------------------//
	
	//textpanel above the game
	public static JPanel textPanel() {
		JPanel textPanel = new JPanel();
		JLabel text = new JLabel("Bubble Pairs v 1.0");
		textPanel.add(text);
		return textPanel;
	}
	
	//menu when you first start the game
	public void menuPanel() {
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuButtons[0].setText("          Spielen      ");
		menuButtons[1].setText("       Anleitung     ");
		menuButtons[2].setText("Spiel Verlassen");
		for(int i=0; i<3; i++) {
			menuPanel.add(menuButtons[i]);
			menuPanel.add(Box.createRigidArea(new Dimension(0,10)));
			menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		}
	}
	
	//panel for the game itself
	public void spielPanel() {
		spielPanel.setLayout(new BorderLayout());
		spielPanel.add(textPanel(), BorderLayout.PAGE_START);
		spielPanel.add(centerPanel,BorderLayout.CENTER);
	}
	
	//panel with all the buttonpanels + middle
	public JPanel centerPanel() {
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(lsideButtonPanel, BorderLayout.LINE_START);
		centerPanel.add(rsideButtonPanel, BorderLayout.LINE_END);
		centerPanel.add(arrow);
		centerPanel.add(tButtonPanel, BorderLayout.PAGE_START);
		centerPanel.add(bButtonPanel, BorderLayout.PAGE_END);
		return centerPanel;
	}
	
	//buttonpanel on the left side with 2 buttons
	public void lsideButtonPanel() {
		lsideButtonPanel.setLayout(new GridLayout(2,0,0,0));
		lsideButtonPanel.add(spielButtons[11]);
		lsideButtonPanel.add(spielButtons[10]);
	}
	
	//buttonpanel on the right side with 2 buttons
	public void rsideButtonPanel() {
		rsideButtonPanel.setLayout(new GridLayout(2,0,0,0));
		rsideButtonPanel.add(spielButtons[4]);
		rsideButtonPanel.add(spielButtons[5]);
	}
	
	//buttonpanel on the bottom side with 4 buttons
	public void bButtonPanel() {
		bButtonPanel.setLayout(new GridLayout(0,4,0,0));
		bButtonPanel.add(spielButtons[9]);
		bButtonPanel.add(spielButtons[8]);
		bButtonPanel.add(spielButtons[7]);
		bButtonPanel.add(spielButtons[6]);
	}
	
	//buttonpanel on the top side with 4 buttons
	public void tButtonPanel() {
		tButtonPanel.setLayout(new GridLayout(0,4,0,0));
		tButtonPanel.add(spielButtons[0]);
		tButtonPanel.add(spielButtons[1]);
		tButtonPanel.add(spielButtons[2]);
		tButtonPanel.add(spielButtons[3]);
	}
	
	//--------------------------------------------------//
	//--------------panel construction end--------------//
	//--------------------------------------------------//
	
	
	
 
	
	//turns the centerpanel 90deg to the right by setting and unsetting buttons to be invisible
	public void turnPanel()  {
		for(int i=0; i<12; i++) {
			if(spielfeld.getQuadrate()[i].getEntfernt()) {
				spielButtons[i].setVisible(false);
			} else  {
				spielButtons[i].setVisible(true);
				spielButtons[i].setOpaque(true);
				spielButtons[i].setIcon(null); 
			}
		}
	}
	
	//sets icon for the clicked button / starts timer for buttonClickEvent2
	public void buttonClickEvent1() {
		//if 1 button is selected
		selectedButton1.setIcon(getButtonIcon(selectedButton1Number));
		selectedButton1.setOpaque(false);
		//if 2 buttons are selected
		if(selectedButton2 != null) {
			selectedButton2.setIcon(getButtonIcon(selectedButton2Number));
			selectedButton2.setOpaque(false);
			timer.start();
		}
		
	}
	

	
	//is used after the timer runs out sets icon to default and or removes buttons
	public void buttonClickEvent2()  {
		//if  both selected buttons are the same color
		if(spielfeld.getQuadratFarbe(selectedButton1Number) == spielfeld.getQuadratFarbe(selectedButton2Number)) {
			selectedButton1.setVisible(false);
			selectedButton2.setVisible(false);
			selectedButton1 = null;
			selectedButton2 = null;
			spielfeld.quadratEntfernen(selectedButton1Number);
			spielfeld.quadratEntfernen(selectedButton2Number);
			spielfeld.drehen();
			spieler.quadratAufdecken();
			turnPanel();
			//if there is no  button left (e.g. player has won the game)
			if(spielfeld.checkWinner()) {
				switch(JOptionPane.showOptionDialog(null, "Herzlichen Glückwunsch sie haben " + spieler.getVersuche() + 
						" Versuche gebraucht" + "\n Wollen sie erneut spielen oder zurück zum Hauptmenü?",
						"",
		                JOptionPane.YES_NO_CANCEL_OPTION,
		                JOptionPane.WARNING_MESSAGE, null, 
		                new String[]{"Erneut spielen", "zurück zum Hauptmenü","Spiel verlassen"}, "B")) {
		                case JOptionPane.YES_OPTION : resetGame(); break;
		                case JOptionPane.NO_OPTION : this.setContentPane(menuPanel); this.pack(); break;
		                case JOptionPane.CANCEL_OPTION : this.dispose(); break;
				}
			}
			//if both are selected but not the same
		} else if(selectedButton2 != null) {
			selectedButton1.setIcon(null);
			selectedButton2.setIcon(null);
			selectedButton1.setOpaque(true);
			selectedButton2.setOpaque(true);
			selectedButton1 = null;
			selectedButton2 = null;
			spieler.quadratAufdecken();
			spielfeld.drehen();
			turnPanel();
		}
	}
	
	//sets all buttons to be visible/opaque again and sets the icon to null (removes the icon)
	public void resetGame() {
		spielfeld.resetGame();
		for(int i=0;i<12;i++) {
			spielButtons[i].setVisible(true);
			spielButtons[i].setOpaque(true);
			spielButtons[i].setIcon(null);
		}
	}
	
	//returns an icon to the color of "quadrat"
	public Icon getButtonIcon(int nummer) {
		switch (spielfeld.getQuadratFarbe(nummer)) {
		case SCHWARZ: return schwarzerKreisIcon;
		case GRUEN: return gruenerKreisIcon;
		case GELB: return gelberKreisIcon;
		case ROT: return roterKreisIcon;
		case ORANGE: return orangenerKreisIcon;
		case BLAU: return blauerKreisIcon;
		default : return roterKreisIcon;
		}
	}
	
	//main method
	public static void main(String[] args) {
		UI myUI = new UI();
		myUI.setVisible(true);
		spielfeld.farbenVergeben();
	}
	

	//actionlistening events
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<12;i++) {
			//if any of the game buttons is clicked this records which button is clicked and starts buttonClickEvent
			//also prevents unwanted input during the game
			if(e.getSource() == spielButtons[i] && selectedButton2 == null && e.getSource() != selectedButton1) {
				if(selectedButton1 == null) {
					selectedButton1 = spielButtons[i];
					selectedButton1Number = i;
				} else {
					selectedButton2 = spielButtons[i];
					selectedButton2Number = i;
				}
				buttonClickEvent1();
			}
		}
		
		//if "spielen" button is clicked the spielPanel is used as contentPane
		if(e.getSource() == menuButtons[0]) {
			this.setContentPane(spielPanel);
			this.pack();
		}
		
		//if "anleitung" button is clicked it shows a message dialog with instructions on how to play
		if(e.getSource() == menuButtons[1]) {
			JOptionPane.showMessageDialog(null,"Versuchen sie alle Paare zu finden, indem sie mit der Maus auf einen der schwarzen Knöpfe klicken,"
					+ "\n das Feld dreht sich nach jedem Spielzug (wenn die karten aufgedeckt wurden) um 90 Grad nach rechts"
					+ "\n Sie haben gewonnen, wenn keine Karten mehr übrig sind"
					+ "\n Ziel ist es mit so wenig versuchen wie Möglich alle karten aufzudecken","Anleitung", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//if "spiel verlassen" button is clicked the application is closed
		if(e.getSource() == menuButtons[2]) {
			this.dispose();
		}
	}

}
