package Objects.Units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Objects.Sprite;

public class BasicUnit extends Sprite{

	public float speed;
	
	public int stateIA;
	public int battlefieldX;
	public int battlefieldY;
	
	protected BufferedImage image;
	protected int numberAnimations;
	protected int numberFrames;
	protected int currentAnimation;
	protected int currentFrame;
	protected int timeBetweenFrame;
	protected int timerAnimation;
	
	protected float vectorX;
	protected float vectorY;
	
	protected int timerIA;
	
	public BasicUnit(BufferedImage image, float x, float y){
		this.image =  image;
		numberAnimations = 1;
		numberFrames = 6;
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
		
		speed = 100;
		vectorX = 0;
		vectorY = 0;
		
		stateIA = 0;
		battlefieldX = 0;
		battlefieldY = 0;
		
		timerIA = 0;
	}
	
	@Override
	public void update(int difTime) {
		timerIA += difTime;
		if(timerIA>100){
			timerIA -= 100;
			calculateIA(difTime);
		}
		
		/*update animation*/
		timerAnimation+=difTime;
		if(timerAnimation>timeBetweenFrame){
			timerAnimation -= timeBetweenFrame;
			
			currentFrame++;
			if(currentFrame>numberFrames)
				currentFrame=0;
		}
		
        x += vectorX*(difTime/1000f);
        y += vectorY*(difTime/1000f);
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(image, (int)x, (int)y, (int)(x+width), (int)(y+height), currentFrame*width, currentAnimation*height, (currentFrame*width)+width, (currentAnimation*height)+height, null);
	}
	
	public void renderFocus(Graphics2D graphics){
		graphics.setColor(Color.GREEN);
		graphics.drawOval((int)(x), (int)(y+height*0.8f), (int)(width), (int)(height*0.3f));
	}
	
	public void calculateIA(int difTime){
		/*Stop*/
		if(stateIA == 0){
			vectorX = 0;
			vectorY = 0;
		}
		
		/*Going to battlefield*/
		if(stateIA == 1){
            float difX = battlefieldX - x;
            float difY = battlefieldY - y;
            float moduleVector = (float)Math.sqrt((difX * difX)+(difY * difY));
            
			if(moduleVector < 50){
				stateIA = 0;
				battlefieldX = 0;
				battlefieldY = 0;
			}
				
            vectorX = (difX/moduleVector)*speed;
            vectorY = (difY/moduleVector)*speed;
		}
	}
	
	public void goToObjetive(int x, int y){
		stateIA = 1;
		battlefieldX = x;
		battlefieldY = y;
	}
}
