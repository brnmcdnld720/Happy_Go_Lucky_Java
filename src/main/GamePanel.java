package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16 x 16 tiles
	final int scale = 3;

	final int tileSize = originalTileSize * scale; //48x48 tile
	final int maxScreenCol = 20;
	final int maxScreenRow = 12;
	final int SCREEN_WIDTH = tileSize * maxScreenCol; //960 pixels
	final int SCREEN_HEIGHT = tileSize * maxScreenRow; // 576 pixels
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

}
