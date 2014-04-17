package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import Manager.Image;
import Objects.Units.Scout;
import ProjetoIntegrador.StaticContent;
import Scenes.GamePlayScene;
import Scenes.Scene;

public class Player extends Sprite{

	public int[] units;
	public int[] commanders;
	
	public float gold;
	public float points;
	
	public ArrayList<Squad> squads;
	public Squad focusSquad;
	
	private Scene parent;
	
	public Player(int[] units, int[] commanders, GamePlayScene parent){
		this.active = true;
		
		this.units = units;
		this.commanders = commanders;
		
		this.gold = StaticContent.goldBase;
		this.points = 0;
		
		this.parent = parent;
		
		squads= new ArrayList<Squad>();
		Squad s = new Squad(parent);
		squads.add(s);
		focusSquad = s;
	}
	
    /** 
    * Update objects of map. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {
    	gold += difTime/1000f;
    	
    	for(int i=0; i<squads.size(); i++)
    		squads.get(i).update(difTime);
    }

    /** 
    * Render objects of map.
    * 
    * @param Graphics2D graphics
    */
	@Override
	public void render(Graphics2D graphics) {
    	for(int i=0; i<squads.size(); i++)
    		squads.get(i).render(graphics);
		
	}
	
	public void createUnit(int i, int placeIDMap){
		int unitsInSquad = GamePlayScene.player.focusSquad.units.size();
		float x = parent.width*0.45f+(unitsInSquad%4)*parent.width*0.02f;
		float y = unitsInSquad>=4?(int)(parent.height*0.87f):(int)(parent.height*0.85f);
		
		
		switch (i) {
		case 0:
			squads.get(squads.size()-1).units.add(new Scout(Image.scoutG, x, y, placeIDMap));
			break;
		}
		
		focusSquad = squads.get(squads.size()-1);
	}
	
	public ArrayList<Squad> getSquadsInBattlefield(int battlefield){
		ArrayList<Squad> squadsTemp = new ArrayList<Squad>();
		
		for(Squad s: squads){
			if(s.currentBattleField == battlefield)
				squadsTemp.add(s);
		}
		
		return squadsTemp;
	}
}
