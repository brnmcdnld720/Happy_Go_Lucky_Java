package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import main.GamePanel;

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
	
	public BufferedImage scaleImage(BufferedImage originalImage, int sizeToScale) {
		BufferedImage scaledImage = new BufferedImage(sizeToScale, sizeToScale, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = scaledImage.createGraphics();
		
		Image scaled = originalImage.getScaledInstance(sizeToScale, sizeToScale, Image.SCALE_SMOOTH);
		g2d.drawImage(scaled, 0, 0, null);
		
		g2d.dispose();
		
		return scaledImage;
	
	}

}
