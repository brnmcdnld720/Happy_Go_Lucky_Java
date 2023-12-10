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
	String lastKeyPressed;
	
	public Player (GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		
		this.loadAnimations();
	}

	public void setDefaultValues() {
		
		x = 400;
		y = 300;
		speed = 3;
		animationFrameSpeed = 7; //ticks per animation frame
		animationCounter = 0;
		animationFrame = 0;
		direction = "down";
		lastKeyPressed = "walk_down";
		
		spriteSheet = importCharacterImage("/player/char1.png");
		
//		charSprites = parseSpriteSheet(charSpriteSheet1);
		
	}
	
	public void loadAnimations() {
		walk_down = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_down[i] = spriteSheet.getSubimage(i * gp.originalTileSize, 
													0*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_down[i] = scaleImage(walk_down[i], gp.tileSize * 2);
			
		}
		walk_up = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_up[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													1*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_up[i] = scaleImage(walk_up[i], gp.tileSize * 2);
		}
		walk_right = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_right[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													2*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_right[i] = scaleImage(walk_right[i], gp.tileSize * 2);
		}
		walk_left = new BufferedImage[8];
		for(int i = 0; i<walk_down.length; i++) {
			walk_left[i] = spriteSheet.getSubimage(i * gp.originalTileSize,
													3*gp.originalTileSize, 
													gp.originalTileSize, 
													gp.originalTileSize);
			walk_left[i] = scaleImage(walk_left[i], gp.tileSize * 2);
		}
		
	}
	
	public void update() {
		playerMovement();
	}
	
	private void playerMovement() {
		if(keyH.upPressed == true) {
			lastKeyPressed = "walk_up";
			direction = "walk_up";
			y -= speed;
		}
		else if(keyH.downPressed == true) {
			lastKeyPressed = "walk_down";
			direction = "walk_down";
			y += speed;
		}
		else if(keyH.leftPressed== true) {
			lastKeyPressed = "walk_left";
			direction = "walk_left";
			x -= speed;
		}
		else if(keyH.rightPressed == true) {
			lastKeyPressed = "walk_right";
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
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
		   keyH.leftPressed == true || keyH.rightPressed == true) {
					
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
	//			animationFrameCounter(walk_down.length);
				imageDraw = walk_down[0];
				break;	
			}
		}
		else {
			switch(lastKeyPressed) {
			case "walk_up":
				imageDraw = walk_up[0];
				break;
			case "walk_down":
				imageDraw = walk_down[0];
				break;
			case "walk_left":
				imageDraw = walk_left[0];
				break;
			case "walk_right":
				imageDraw = walk_right[0];
				break;
			default:
				imageDraw = walk_down[0];
				break;	 
			}
		}
		
		g2.drawImage(imageDraw, null, x, y);
	}

}
