package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Image;

public class Flag extends Sprite{
	
	public int battleFieldID;
	
	private BufferedImage image;
	
	private int numberAnimations;
	private int numberFrames;
	private int currentAnimation;
	private int currentFrame;
	private int timeBetweenFrame;
	private int timerAnimation;
	
	private int state;

	public Flag(float x, float y, int battleFieldID){
		image = Image.flagDown;
		numberAnimations = 1;
		numberFrames = 1;
		currentAnimation = 0;
		currentFrame = 0;
		timeBetweenFrame = 60;
		timerAnimation = 0;
		
		this.width = image.getWidth()/numberFrames;
		this.height = image.getHeight()/numberAnimations;
		this.radius = width/2;
		
		this.x = x;
		this.y = y;
		
		this.active = true;
		
		state = 0;
	}
	
	@Override
	public void update(int difTime) {
		switch (state) {
		/*Nobody owner*/
		case 0:
			
			break;
		/*Raising*/
		case 1:
			timerAnimation+=difTime;
			if(timerAnimation>timeBetweenFrame){
				timerAnimation -= timeBetweenFrame;
				
				currentFrame++;
				if(currentFrame>=numberFrames)
					setState(2);
			}
			break;
		/*Raised*/
		case 2:
			timerAnimation+=difTime;
			if(timerAnimation>timeBetweenFrame){
				timerAnimation -= timeBetweenFrame;
				
				currentFrame++;
				if(currentFrame>=numberFrames)
					currentFrame = 0;
			}
			break;
		/*Lowering*/
		case 3:
			timerAnimation+=difTime;
			if(timerAnimation>timeBetweenFrame){
				timerAnimation -= timeBetweenFrame;
				
				currentFrame--;
				if(currentFrame<0)
					setState(0);
			}
			break;
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		if(image == Image.flagHoistingG)
			graphics.drawImage(image, (int)(x-width/2), (int)(y-height+(height*0.08f)), (int)(x+width/2), (int)(y+(height*0.08f)), currentFrame*width, currentAnimation*height, 
					(currentFrame*width)+width, (currentAnimation*height)+height, null);
		else
			graphics.drawImage(image, (int)(x-width/2), (int)(y-height), (int)(x+width/2), (int)(y), currentFrame*width, currentAnimation*height,
					(currentFrame*width)+width, (currentAnimation*height)+height, null);
	}
	
	public void setState(int _state){
		this.state = _state;
		switch (_state) {
		/*Nobody owner*/
		case 0:
			image = Image.flagDown;
			numberAnimations = 1;
			numberFrames = 1;
			currentFrame = 0;
			currentAnimation = 0;
			timeBetweenFrame = 60;
			
			this.width = image.getWidth()/numberFrames;
			this.height = image.getHeight()/numberAnimations;
			this.radius = width/2;
			break;
		/*Raising*/
		case 1:
			image = Image.flagHoistingG;

			numberAnimations = 1;
			numberFrames = 8;
			currentFrame = 0;
			currentAnimation = 0;
			timeBetweenFrame = 100;
		
			this.width = image.getWidth()/numberFrames;
			this.height = image.getHeight()/numberAnimations;
			this.radius = width/2;
			break;
		/*Raised*/
		case 2:
			image = Image.flagHoistedG;
			numberAnimations = 1;
			numberFrames = 6;
			currentFrame = 0;
			currentAnimation = 0;
			timeBetweenFrame = 60;
			
			this.width = image.getWidth()/numberFrames;
			this.height = image.getHeight()/numberAnimations;
			this.radius = width/2;
			break;
		/*Lowering*/
		case 3:
			image = Image.flagHoistingG;

			numberAnimations = 1;
			numberFrames = 8;
			currentFrame = 5;
			currentAnimation = 0;
			timeBetweenFrame = 100;
		
			this.width = image.getWidth()/numberFrames;
			this.height = image.getHeight()/numberAnimations;
			this.radius = width/2;
			break;
		}
	}
	
	public int getState(){
		return state;
	}
}
