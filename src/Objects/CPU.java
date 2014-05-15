package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import Manager.Image;
import Objects.Units.BarricadeTurtle;
import Objects.Units.BigHead;
import Objects.Units.CatchUp;
import Objects.Units.Devastating;
import Objects.Units.Doctor;
import Objects.Units.HeavyArtillery;
import Objects.Units.LightArtillery;
import Objects.Units.LightInfantry;
import Objects.Units.Rock;
import Objects.Units.Roulette;
import Objects.Units.Scout;
import ProjetoIntegrador.StaticContent;
import Scenes.GamePlayScene;
import Scenes.Scene;

public class CPU extends Sprite{

	public int[] units;
	public int[] commanders;
	
	public float gold;
	public float points;
	
	public ArrayList<Squad> squads;
	public Squad focusSquad;
	
	private Scene parent;
	
	public CPU(int[] units, int[] commanders, GamePlayScene parent){
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
		int unitsInSquad = GamePlayScene.cpu.focusSquad.units.size();
		float x = parent.width*0.45f+(unitsInSquad%4)*parent.width*0.02f;
		float y = unitsInSquad>=4?(int)(parent.height*0.12f):(int)(parent.height*0.1f);
		
		
		switch (i) {
		case 0:
			Scout s = new Scout(Image.scoutR, x, y, 4, 6, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(s.coast))
				return;
			squads.get(squads.size()-1).units.add(s);
			gold -= s.coast;
			break;
		case 1:
			BarricadeTurtle b = new BarricadeTurtle(Image.barricadeTurtleR, x, y, 4, 8, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(b.coast))
				return;
			squads.get(squads.size()-1).units.add(b);
			gold -= b.coast;
			break;
		case 2:
			LightArtillery l = new LightArtillery(Image.LightArtilleryR, x, y, 4, 8, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(l.coast))
				return;
			squads.get(squads.size()-1).units.add(l);
			gold -= l.coast;
			break;
		case 3:
			HeavyArtillery h = new HeavyArtillery(Image.HeavyArtilleryR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(h.coast))
				return;
			squads.get(squads.size()-1).units.add(h);
			gold -= h.coast;
			break;
		case 4:
			BigHead bh = new BigHead(Image.BigHeadR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(bh.coast))
				return;
			squads.get(squads.size()-1).units.add(bh);
			gold -= bh.coast;
			break;
		case 5:
			LightInfantry li = new LightInfantry(Image.LightInfantryR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(li.coast))
				return;
			squads.get(squads.size()-1).units.add(li);
			gold -= li.coast;
			break;
		case 6:
			Roulette r = new Roulette(Image.RouletteR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(r.coast))
				return;
			squads.get(squads.size()-1).units.add(r);
			gold -= r.coast;
			break;
		case 7:
			Doctor dr = new Doctor(Image.DoctorR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(dr.coast))
				return;
			squads.get(squads.size()-1).units.add(dr);
			gold -= dr.coast;
			break;
		case 8:
			Devastating dv = new Devastating(Image.DevastatingR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(dv.coast))
				return;
			squads.get(squads.size()-1).units.add(dv);
			gold -= dv.coast;
			break;
		case 9:
			CatchUp cu = new CatchUp(Image.CatchUpR, x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(cu.coast))
				return;
			squads.get(squads.size()-1).units.add(cu);
			gold -= cu.coast;
			break;
		case 10:
			Rock rock = new Rock(Image.RockR,  x, y, 1, 1, placeIDMap, squads.get(squads.size()-1));
			if(!approvedInsertion(rock.coast))
				return;
			squads.get(squads.size()-1).units.add(rock);
			gold -= rock.coast;
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
	
	public boolean approvedInsertion(int cust){
		if(focusSquad.units.size() >= 8)
			return false;
		if(cust > gold)
			return false;
		
		return true;
	}
}
