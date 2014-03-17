package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Manager.Image;
import Scenes.GamePlayScene;

public class UI extends Sprite{

	public Rectangle2D[] rectCards;
	
	public ArrayList<Rectangle2D> optionBoxArea;
	public ArrayList<String> optionBoxString;
	public boolean optionBox;
	public int optionBoxBattlefield;
	
	private GamePlayScene parent;
	private Player player;
	
	
	public UI(GamePlayScene parent){
		this.parent = parent;
		this.width = parent.width;
		this.height = parent.height;
		
		player = parent.player;
		
		optionBoxArea = new ArrayList<Rectangle2D>();
		optionBoxString = new ArrayList<String>();
		optionBox = false;
		optionBoxBattlefield = -1;
		
		rectCards = new Rectangle2D[player.cards.length];
        for(int i=0; i<rectCards.length; i++){
        	rectCards[i] = new Rectangle(i*(int)(width*0.1f)+20, (int)(height*0.91f), (int)(width*0.05f), (int)(height*0.08f));
        }
	}
	
	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render(Graphics2D graphics) {
    	/*Gold, points*/
        graphics.setColor(Color.orange);
        graphics.drawString(""+(int)player.gold, 20, 20);
        graphics.drawString(""+(int)player.points, 20, 40);
        
    	/*Cards box*/
        graphics.setColor(new Color(0, 0, 0, 150));
        graphics.fillRect(0, (int)(height*0.9f), width, height);
        
        for(int i=0; i<player.cards.length; i++){
        	graphics.drawImage(Image.cards[player.cards[i]], (int)rectCards[i].getX(), (int)rectCards[i].getY(), (int)rectCards[i].getWidth(), (int)rectCards[i].getHeight(), null);
        }

        if(optionBox){
    		for(int i=0; i<optionBoxArea.size(); i++){
        		graphics.setColor(new Color(0,0,0,200));
    			graphics.fillRect((int)optionBoxArea.get(i).getX(), (int)optionBoxArea.get(i).getY(), (int)optionBoxArea.get(i).getWidth(), (int)optionBoxArea.get(i).getHeight());
    			
        		graphics.setColor(Color.WHITE);
        		graphics.drawString(optionBoxString.get(i), (int)optionBoxArea.get(i).getX(), (int)(optionBoxArea.get(i).getY()+optionBoxArea.get(i).getHeight()/2));
    		}
        }
	}
	
	public void openActionBox(int battleFieldID){
		optionBoxBattlefield = battleFieldID;
		
       	Rectangle2D rectArea = GamePlayScene.map.battlefields[optionBoxBattlefield].area;
       	ArrayList<Squad> squadsTemp = GamePlayScene.player.getSquadsInBattlefield(battleFieldID);

       	for(int i=0; i<squadsTemp.size(); i++){
       		optionBoxArea.add(new Rectangle((int)(rectArea.getX()+rectArea.getWidth()), (int)(rectArea.getY()+i*30), 100, 30));
       		optionBoxString.add(""+i);
       	}
	}
}
