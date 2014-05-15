package Scenes;

import IA.PathMove;
import Manager.Image;
import Manager.StageImport;
import Objects.CPU;
import Objects.Map;
import Objects.Player;
import Objects.Squad;
import Objects.UI;
import ProjetoIntegrador.GamePanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
* GamePlayScene, scene presentation, configuration and 
* look to start the gameplay.
* 
* @author Gregorio
* @version 1.0
*/
public class GamePlayScene extends Scene{
       
	public static Player player;
	public static CPU cpu;
	
	public static Map map;
    
	private UI ui;
    
    /** 
    * Constructor, init parameters of scene. 
    * 
    * @param GamePanel instance of gamePanel
    * @param int width of screen
    * @param int height of screen
    */
    public GamePlayScene(GamePanel gamePanel, int[] units, int[] commanders){
       this.gamePanel = gamePanel;
       this.width  = GamePanel.widthScreen;
       this.height = GamePanel.heightScreen;
       
       this.font = gamePanel.font;
       
       /*Positions bases and battlefields in map.*/
       player = new Player(units, commanders, this);
       cpu = new CPU(units, commanders, this);
       
       /*Positions bases and battlefields in map.*/
       StageImport.loadStageFile("stage0.txt");
       map = new Map(Image.terrain, 0, 0, StageImport.bases, StageImport.battlefields, StageImport.pathMove, this);
       
       random = new Random();
       ui = new UI(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {
//		ArrayList<Squad> squadTemp = player.getSquadsInBattlefield(0);
//		squadTemp.get(0).goToBattleField(1);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {}   
    
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	/*Right Button*/
    	if(e.getButton() == 3){
			for(int i=0; i<map.battlefields.length; i++){
				if(map.battlefields[i].area.contains(e.getPoint())){	
					if(ui.optionBox){
						ui.optionBox = false;
						ui.optionBoxArea.clear();
						ui.optionBoxString.clear();
					}else{
						ui.optionBox = true;
						ui.openActionBox(i);
					}
				}
			}
    	}
    	
    	/*Left Button*/
    	if(e.getButton() == 1){
    		if(ui.optionBox){
    			for(int i=0; i<ui.optionBoxArea.size(); i++){
    				if(ui.optionBoxArea.get(i).contains(e.getPoint())){
    					player.focusSquad = player.getSquadsInBattlefield(ui.optionBoxBattlefield).get(i);
    				}
    			}
    		}
    		
    		/*Click over cards to create news units*/
			for(int i=0; i<ui.rectUnitsCards.length; i++){
				if(ui.rectUnitsCards[i].contains(e.getPoint())){
//					FIXME
					ui.cartasEscolhida = player.units[i];
//					ui.setUIText(player.units[i]);
				}
			}
			
    		/*Click over cards to create news commanders*/
			for(int i=0; i<ui.rectCommandersCards.length; i++){
				if(ui.rectCommandersCards[i].contains(e.getPoint())){
//					FIXME
					ui.cartasEscolhida = player.commanders[i];
//					ui.setUIText(player.commanders[i]);
				}
			}
			
			/*Convene*/
			if(ui.convene.contains(e.getPoint())){
				//TODO
//				cpu.createUnit(0, 0);
				/*Max units in one only squad*/
				player.createUnit(ui.cartasEscolhida, 1);
			}
			
			/*Send select squad to battlefield selected*/
			for(int i=0; i<map.battlefields.length; i++){
				if(map.battlefields[i].area.contains(e.getPoint())){
					/*Test if is valid position*/
					boolean valid = false;
					for(PathMove pm : map.pathMove){
						if(pm.origin == player.focusSquad.currentBattleField+2 && pm.destination == i+map.bases.length){
							valid = true;
						}
					}
					
					if(valid){
						if(player.focusSquad.units.size() > 0){
			    			player.focusSquad.goToBattleField(i+map.bases.length);
			    			
			    			Squad s = new Squad(this);
			       			player.squads.add(s);
			       			player.focusSquad = s;
			       			
			       			if(ui.optionBox){
								ui.optionBox = false;
								ui.optionBoxArea.clear();
								ui.optionBoxString.clear();
			       			}
						}
					}
				}
			}
    	}
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}
        
    int time = 0;
    /** 
    * Update objects of scene. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {
    	time+=difTime;
    	if(time > 3000){
    		time -= 3000;
    		if(cpu.focusSquad.units.size() > 0){
	    		cpu.focusSquad.goToBattleField(random.nextInt(2)+2);
	    		
				Squad s = new Squad(this);
	   			cpu.squads.add(s);
	   			cpu.focusSquad = s;
    		}
    	}
    	player.update(difTime);
    	cpu.update(difTime);
    	map.update(difTime);
    	ui.update(difTime);
    }

    /** 
    * Render objects of scene.
    * 
    * @param Graphics2D graphics
    */
    @Override
    public void render(Graphics2D graphics) {    	
    	map.render(graphics);
    	player.render(graphics);
    	cpu.render(graphics);
    	
    	ui.render(graphics);
    }
}
