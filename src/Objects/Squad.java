package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

import IA.PathMove;
import Objects.Units.BasicUnit;
import Scenes.GamePlayScene;

public class Squad{

	public ArrayList<BasicUnit> units;
	
	public int currentBattleField;
	private GamePlayScene parent;
	
	public Squad(GamePlayScene parent){
		this.parent = parent;
		units = new ArrayList<BasicUnit>();
		
		currentBattleField = -1;
	}
	
	public void update(int difTime) {
		for(int i=0; i<units.size(); i++)
			units.get(i).update(difTime);
	}

	public void render(Graphics2D graphics) {
		for(int i=0; i<units.size(); i++)
			units.get(i).render(graphics);
		
		if(this == GamePlayScene.player.focusSquad)
			for(int i=0; i<units.size(); i++)
				units.get(i).renderFocus(graphics);
	}
	
	public void goToBattleField(int battleField){
		currentBattleField = battleField-2;
		
		for(BasicUnit bu : units){
			PathMove pm = getPathMove(bu.placeIDMap, battleField);
			if(pm != null){
				bu.goToObjetive(pm);
				bu.placeIDMap = battleField;
			}
		}
	}
	
	private PathMove getPathMove(int currentPlace, int goingPlace){
		for(PathMove pm : GamePlayScene.map.pathMove){
			if(pm.origin == currentPlace && pm.destination == goingPlace){
				return pm;
			}
		}
		return null;
	}
}