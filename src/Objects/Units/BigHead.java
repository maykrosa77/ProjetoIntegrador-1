package Objects.Units;

import java.awt.image.BufferedImage;
import Manager.Language;
import Objects.Squad;

public class BigHead extends BasicUnit{
	
	public BigHead(BufferedImage image, float x, float y, int numberAnimations, int numberFrames, int placeIDMap, Squad yourSquad){
		super(image, x, y, numberAnimations, numberFrames, placeIDMap, yourSquad);
		
		name = Language.texts[11];
		type = Language.texts[4];
		
		meleeDamage = 30;
		explosionDamage = 40;
		atackSpeed = 120;
		moveSpeed = 120;
		meleeResistance = 0.1f;
		explosionResistance = 0.1f;
		coast = 300;
		buildTime = 1000;
		
		hp = 300;
		currentHp = hp;
	}
}