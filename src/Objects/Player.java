package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import Manager.Image;
import Objects.Units.Archer;
import Objects.Units.Soldier;
import ProjetoIntegrador.StaticContent;
import Scenes.GamePlayScene;
import Scenes.Scene;

public class Player extends Sprite{

	public int[] cards;
	
	public float gold;
	public float points;
	
	public ArrayList<Squad> squads;
	public Squad focusSquad;
	
	private Scene parent;
	
	public Player(int[] cards, GamePlayScene parent){
		this.active = true;
		
		this.cards = cards;
		
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
	
	public void createUnit(int i, float x, float y){
		switch (i) {
		case 0:
			squads.get(squads.size()-1).units.add(new Soldier(Image.soldier, x, y));
			break;
		case 1:
			squads.get(squads.size()-1).units.add(new Archer(Image.archer, x, y));
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
