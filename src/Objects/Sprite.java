package Objects;

import java.awt.Graphics2D;

/**
* Abstract class. Sprite is basic graphic element.
* 
* @author Gregorio
* @version 1.0
*/
public abstract class Sprite {

	public float x;
	public float y;
	public boolean active;

	public int width;
	public int height;
	public int radius;
	
    public abstract void update(int difTime);
    public abstract void render(Graphics2D graphics);
}
