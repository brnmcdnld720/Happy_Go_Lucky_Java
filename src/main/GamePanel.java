package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import backgournd.BackgroundManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTINGS
	public final int originalTileSize = 32; //32 x 32 tiles
	final float scale = 1.5f;

	public final int tileSize = (int)(originalTileSize * scale); //48x48 tile
	final int maxScreenCol = 22;
	final int maxScreenRow = 13;
	final int SCREEN_WIDTH = tileSize * maxScreenCol; //1056 pixels
	final int SCREEN_HEIGHT = tileSize * maxScreenRow; // 624 pixels
	
	//FPS
	int FPS = 60;
	
	BackgroundManager backgroundManager = new BackgroundManager(this); 
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public int getTileSize() {
		return tileSize;
	}
	public int getOriginalTileSize() {
		return originalTileSize;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		
		
		while(gameThread != null) {
			
			update();
			
			repaint();
		
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
					
			} catch (InterruptedException e) {
				
			}
			
		}

	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		backgroundManager.draw(g2);
		
		player.draw(g2);
		
		g2.dispose(); 
	}
	
}
