package backgournd;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BackgroundManager {

	GamePanel gp;
	Background[] background;
	
	public BackgroundManager(GamePanel gp) {
		this.gp = gp;
		
		background = new Background[2];
		
		getBackgroundImage("/backgrounds/DemoScene.png");
	}

	private void getBackgroundImage(String location) {
		
		InputStream is = getClass().getResourceAsStream(location);
		try {
			background[0] = new Background(); 
			background[0].image = ImageIO.read(is);
						
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try{
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		g2.drawImage(background[0].image, 0, 0, 1600, 1600, null);
	}
}
