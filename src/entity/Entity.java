package entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Entity {
	
	
	public int x, y;
	public int speed;
	public int animationFrameSpeed;
	public int animationCounter;
	public int animationFrame;
	
	public BufferedImage[] walk_down;
	public BufferedImage[] walk_up;
	public BufferedImage[] walk_right;
	public BufferedImage[] walk_left;
	public BufferedImage spriteSheet;
	
	
	public String direction;
	
	
	public BufferedImage importCharacterImage(String location) {
				
		InputStream is = getClass().getResourceAsStream(location);
		try {
			spriteSheet = ImageIO.read(is);
						
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try{
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return spriteSheet;
	}

}
