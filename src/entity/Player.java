package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public Player (GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		
		this.loadAnimations();
	}
	
//	public Map parseSpriteSheet(BufferedImage spriteSheet) {
//		Map <String, Integer[]> charSprite1 = new HashMap<String, Integer[]>();
//		charSprite1.put("walk_down", new Integer[] {0, 8});
//		charSprite1.put("walk_up", new Integer[] {1, 8});
//		charSprite1.put("walk_right", new Integer[] {2, 8});
//		charSprite1.put("walk_left", new Integer[] {3, 8});
//		
//		return charSprite1;
//	}
//	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 3;
		animationFrameSpeed = 7; //ticks per animation frame
		animationCounter = 0;
		animationFrame = 0;
		direction = "down";
		
		spriteSheet = importCharacterImage("/player/char1.png");
		
//		charSprites = parseSpriteSheet(charSpriteSheet1);
		
	}
	
	public BufferedImage scaleImage(BufferedImage originalImage) {
		BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = scaledImage.createGraphics();
		
		Image scaled = originalImage.getScaledInstance(gp.tileSize, gp.tileSize, Image.SCALE_SMOOTH);
		g2d.drawImage(scaled, 0, 0, null);
		
		g2d.dispose();
		
		return scaledImage;
	
	}
	
	public void loadAnimations() {
		walk_down = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_down[i] = spriteSheet.getSubimage(i * gp.originalTileSize, 
													0*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_down[i] = scaleImage(walk_down[i]);
			
		}
		walk_up = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_up[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													1*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_up[i] = scaleImage(walk_up[i]);
		}
		walk_right = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_right[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													2*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_right[i] = scaleImage(walk_right[i]);
		}
		walk_left = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_left[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													3*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_left[i] = scaleImage(walk_left[i]);
		}
		
	}
	
	public void update() {
		if(keyH.upPressed == true) {
			direction = "walk_up";
			y -= speed;
		}
		else if(keyH.downPressed == true) {
			direction = "walk_down";
			y += speed;
		}
		else if(keyH.leftPressed== true) {
			direction = "walk_left";
			x -= speed;
		}
		else if(keyH.rightPressed == true) {
			direction = "walk_right";
			x += speed;
		}
	}
	
	public void animationFrameCounter (int resetFrame) {
		animationCounter++;
		if (animationCounter >= animationFrameSpeed) {
			animationCounter = 0;
			animationFrame++;
			if (animationFrame >= resetFrame) {
				animationFrame = 0;
			}
				
		}
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage imageDraw;
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		switch(direction) {
		case "walk_up":
			animationFrameCounter(walk_up.length);
			imageDraw = walk_up[animationFrame];
			break;
		case "walk_down":
			animationFrameCounter(walk_down.length);
			imageDraw = walk_down[animationFrame];
			break;
		case "walk_left":
			animationFrameCounter(walk_left.length);
			imageDraw = walk_left[animationFrame];
			break;
		case "walk_right":
			animationFrameCounter(walk_right.length);
			imageDraw = walk_right[animationFrame];
			break;
		default:
			animationFrameCounter(walk_down.length);
			imageDraw = walk_down[animationFrame];
			break;
			
		}
		
		g2.drawImage(imageDraw, null, x, y);
	}
}
