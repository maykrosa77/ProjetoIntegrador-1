package Objects.Units;

import java.awt.image.BufferedImage;

import Manager.Language;

public class Scout extends BasicUnit{
	
	public Scout(BufferedImage image, float x, float y){
		super(image, x, y);
	}
	
	public static void initAtributs(){
		name = Language.texts[3];
		type = Language.texts[4];
		
		meleeDamage = 10;
		explosionDamage = 10;
		atackSpeed = 60;
		moveSpeed = 60;
		meleeResistance = 0.1f;
		explosionResistance = 0.1f;
		coast = 100;
		buildTime = 2;
	}
}
