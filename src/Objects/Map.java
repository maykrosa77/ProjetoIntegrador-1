package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Scenes.Scene;

/**
* Map, contains the bases and battlefields, manager, 
* update and render they.
* 
* @author Gregorio
* @version 1.0
*/
public class Map extends Sprite{

	public Rectangle2D[] bases;
	public Battlefield[] battlefields;
	
	public int numberPaths;
	
	private Scene parent;
	
	private BufferedImage terrain;
	
    /** 
    * Constructor, init parameters of map.
    * 
    * @param BufferedImage image background, the terrain
    * @param int x position
    * @param int y position
    * @param Rectangle2D[] bases
    * @param Rectangle2D[] battlefields
    * @param Scene scene parent
    */
	public Map(BufferedImage image, int x, int y, Rectangle2D[] bases, Battlefield[] battlefields, Scene parent){
		terrain =  image;
		this.width = terrain.getWidth();
		this.height = terrain.getHeight();
		this.radius = width/2;
		
		this.x = x;
		this.y = y;
		
		this.active = true;
		this.parent = parent;
		
		this.bases = bases;
		this.battlefields = battlefields;
	}
	
    /** 
    * Update objects of map. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {

    }

    /** 
    * Render objects of map.
    * 
    * @param Graphics2D graphics
    */
	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(terrain, 0, 0, parent.width, parent.height, null);
		
		graphics.setColor(Color.WHITE);
		for(Rectangle2D b : bases)
			graphics.fillRect((int)b.getX(), (int)b.getY(), (int)b.getWidth(), (int)b.getHeight());
		
		for(Battlefield b : battlefields)
			b.render(graphics);
	}

}
