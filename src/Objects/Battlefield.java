package Objects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Objects.Units.BasicUnit;
import Scenes.GamePlayScene;
public class Battlefield extends Sprite{

	public Rectangle2D area;
	public int id;
	
	public Flag flag;
	
	public int state;
	
	public Battlefield(Rectangle2D area, int id){
		this.area = area;	
		flag = new Flag((int)(area.getX()+(area.getWidth()/2)), (int)(area.getY()+(area.getHeight()/2)), id);
		this.id = id;
		
		state = 0;
	}
	
	@Override
	public void update(int difTime) {
		int situation = unitsInBattleField();
		if(flag.getState() == 0 && situation ==1){
			flag.setState(1);
		}else if(flag.getState() == 2 && situation == 0){
			flag.setState(3);
		}
		flag.update(difTime);
	}

	@Override
	public void render(Graphics2D graphics) {
		flag.render(graphics);
	}
	
	/*0 == nobody, 1 == player, 2 == unknown*/
	public int unitsInBattleField(){
		ArrayList<Squad> squads = GamePlayScene.player.getSquadsInBattlefield(id);
		if(squads.size() == 0){
			return 0;
		}else{
			for(Squad s: squads){
				if(s.currentBattleField == id){
					for(BasicUnit u : s.units){
						if(area.contains(u.x, u.y))
							return 1;
					}
				}
			}
		}
		return 2;
	}
}
