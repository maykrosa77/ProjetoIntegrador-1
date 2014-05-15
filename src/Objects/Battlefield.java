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

		//Estava conquistada pelo jogador e agora está em batalha
		if(flag.getState() == 2 && situation == 3){
			flag.setState(3);
		//Estava conquistada pela CPU e agora está em batalha
		}else if(flag.getState() == 5 && situation == 3){
			flag.setState(6);
		//Sem bandeira para jogador conquistou
		}else if(flag.getState() == 0 && situation ==1){
			flag.setState(1);
		//Jogador tinha conquistado e agora ninguem comanda
		}else if(flag.getState() == 2 && situation == 0){
			flag.setState(3);
		//Sem bandeira para CPU conquistou
		}else if(flag.getState() == 0 && situation == 2){
			flag.setState(4);
		//CPU tinha conquistado e agora ninguem comanda
		}else if(flag.getState() == 4 && situation == 0){
			flag.setState(6);
		}
		
		flag.update(difTime);
	}

	@Override
	public void render(Graphics2D graphics) {
		flag.render(graphics);
	}
	
	/*0 == nobody, 1 == player, 2 == cpu, 3 == inbattle*/
	public int unitsInBattleField(){
		ArrayList<Squad> squads0 = GamePlayScene.player.getSquadsInBattlefield(id);
		ArrayList<Squad> squads1 = GamePlayScene.cpu.getSquadsInBattlefield(id);
		
		if(squads0.size() == 0 && squads1.size() == 0){
			return 0;
		}
		
		for(Squad s: squads0){
			if(s.currentBattleField == id){
				for(BasicUnit u : s.units){
					if(area.contains(u.location.x, u.location.y) && squads1.size() == 0)
						return 1;
				}
			}
		}
		
		for(Squad s: squads1){
			if(s.currentBattleField == id){
				for(BasicUnit u : s.units){
					if(area.contains(u.location.x, u.location.y) && squads0.size() == 0)
						return 2;
				}
			}
		}
		
		if(squads0.size() > 0 && squads1.size() > 0){
			boolean playerOK = false;
			boolean cpuOK = false;
			for(Squad s: squads0){
				if(s.currentBattleField == id)
					if(area.contains(s.units.get(0).location.x, s.units.get(0).location.y))
						playerOK = true;
			}
			
			for(Squad s: squads1){
				if(s.currentBattleField == id){
					if(area.contains(s.units.get(0).location.x, s.units.get(0).location.y))
						cpuOK = true;
				}
			}
			
			if(playerOK && cpuOK)
				return 3;
		}

		return -1;
	}
}
