package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Battlefield extends Sprite{

	public Rectangle2D area;
	
	public Battlefield(Rectangle2D area){
		this.area = area;
	}
	
	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect((int)area.getX(), (int)area.getY(), (int)area.getWidth(), (int)area.getHeight());
	}
}
