package Objects.Units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import IA.Flocking;
import IA.PathMove;
import Math.PVector;
import Objects.Sprite;
import Objects.Squad;
import ProjetoIntegrador.GamePanel;

public class BasicUnit extends Sprite{
	
	/*Atributs*/
	public String name;
	public String type;
	
	public int meleeDamage;
	public int explosionDamage;
	public int atackSpeed;
	public int moveSpeed;
	public float meleeResistance;
	public float explosionResistance;
	public int coast;
	public float buildTime;	/*In milliseconds*/
	public int hp;
	
	/*Variables*/
	
	public int currentHp;
	public Color colorHp;
	
	public int stateIA;
	public int battlefieldX;
	public int battlefieldY;
	public PathMove pathMove;
	public int currentPath;
	
	public int placeIDMap;
	public Squad yourSquad;
	
	protected BufferedImage image;
	protected int numberAnimations;
	protected int numberFrames;
	protected int currentAnimation;
	protected int currentFrame;
	protected int timeBetweenFrame;
	protected int timerAnimation;
	
	public Flocking flocking;
	
	public PVector velocity;
	public PVector acceleration;
	public float maxforce;
	public float maxspeed;
	
	protected int timerIA;
	protected int timerBuild;
	
	public BasicUnit(BufferedImage image, float x, float y, int numberAnimations, int numberFrames, int placeIDMap, Squad yourSquad){
		this.image =  image;
		this.numberAnimations = numberAnimations;
		this.numberFrames = numberFrames;
		currentAnimation = 0;
		currentFrame = 0;
		timeBetweenFrame = 80;
		timerAnimation = 0;
		
		this.width = image.getWidth()/numberFrames;
		this.height = image.getHeight()/numberAnimations;
		this.radius = width/2;
		
		location = new PVector();
		this.location.x = x;
		this.location.y = y;
		
		this.active = false;
		
		velocity = new PVector();
		acceleration = new PVector();
		maxspeed = 50f;
		maxforce = 0.1f;
		
		colorHp = Color.GREEN;
		
		stateIA = 0;
		battlefieldX = 0;
		battlefieldY = 0;
		pathMove = null;
		currentPath = -1;
		
		this.placeIDMap = placeIDMap;
		this.yourSquad = yourSquad;
		
		timerIA = 0;
		timerBuild = 0;
		
		flocking = new Flocking();
	}
	
	@Override
	public void update(int difTime) {
		if(active){
			updateAnimation(difTime);
			
			timerIA += difTime;
			if(timerIA>100){
				timerIA -= 100;
				calculateIA(difTime);
			}
			
	        location.x += velocity.x*(difTime/1000f);
	        location.y += velocity.y*(difTime/1000f);
		}else{	/*Under construction*/
			timerBuild += difTime;
			if(timerBuild > buildTime){
				active = true;
			}
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		if(active){
			int widthBar = hp / 10;
			colorHp = changeColorHp();
			
			graphics.setColor(Color.BLACK);
			graphics.drawRect((int)(location.x+(width/2)-widthBar/2), (int)(location.y-10), widthBar, 6);
			
			graphics.setColor(colorHp);
			graphics.fillRect((int)(location.x+(width/2)-widthBar/2), (int)(location.y-10), currentHp*widthBar/hp, 6);
			
			graphics.drawImage(image, (int)location.x, (int)location.y, (int)(location.x+width), (int)(location.y+height), 
					currentFrame*width, currentAnimation*height, (currentFrame*width)+width, (currentAnimation*height)+height, null);
		}
	}
	
	public void updateAnimation(int difTime){
		/*update animation*/
		timerAnimation+=difTime;
		if(timerAnimation>timeBetweenFrame){
			timerAnimation -= timeBetweenFrame;
			
			currentFrame++;
			if(currentFrame>=numberFrames)
				currentFrame=0;
			
			/*Size of unit*/
			if(numberAnimations>=2)
				currentAnimation = velocity.x > 0? 0:1;
		}
	}
	
	public void renderFocus(Graphics2D graphics){
		graphics.setColor(Color.GREEN);
		graphics.drawOval((int)(location.x), (int)(location.y+height*0.8f), (int)(width), (int)(height*0.3f));
	}
	
	public void calculateIA(int difTime){
		/*Stop*/
		if(stateIA == 0){
			velocity.x = 0;
			velocity.y = 0;
		}
		
		/*Going to battlefield*/
		if(stateIA == 1){
            float difX = battlefieldX - location.x;
            float difY = battlefieldY - location.y;
            float moduleVector = (float)Math.sqrt((difX * difX)+(difY * difY));
            
			if(moduleVector < 50){
				currentPath++;
				if(currentPath >= pathMove.paths.size()){
					currentFrame = -1;
					stateIA = 0;
					battlefieldX = 0;
					battlefieldY = 0;
				}else{
					battlefieldX = (int)(GamePanel.widthScreen*pathMove.paths.get(currentPath).x);
					battlefieldY = (int)(GamePanel.heightScreen*pathMove.paths.get(currentPath).y);
				}
			}
				
            velocity.x = (difX/moduleVector)*moveSpeed;
            velocity.y = (difY/moduleVector)*moveSpeed;
		}
		
//		FIXME Flocking
//		PVector sep = flocking.separate(this, yourSquad.units);
//		PVector ali = flocking.align(this, yourSquad.units);
//		PVector coh = flocking.cohesion(this, yourSquad.units);
//
//		sep.mult(1.5f);
//		ali.mult(1.0f);
//		coh.mult(1.0f);
//
//		acceleration.add(sep);
//		acceleration.add(ali);
//		acceleration.add(coh);
//		
//		velocity.add(acceleration);
//		velocity.limit(maxspeed);
//		acceleration.mult(0);
	}
	
	public void goToObjetive(PathMove path){
		stateIA = 1;
		this.pathMove = path;
		currentPath = 0;
		battlefieldX = (int)(GamePanel.widthScreen*pathMove.paths.get(currentPath).x);
		battlefieldY = (int)(GamePanel.heightScreen*pathMove.paths.get(currentPath).y);
	}
	
	private Color changeColorHp(){
		if(currentHp > hp*0.75f){
			return Color.GREEN;
		}else if(currentHp > hp*0.5f){
			return Color.BLUE;
		}else if(currentHp > hp*0.25f){
			return Color.ORANGE;
		}else{
			return Color.RED;
		}
	}
}
