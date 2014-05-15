package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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
import Scenes.GamePlayScene;

public class UI extends Sprite{

	public Rectangle2D[] rectUnitsCards;
	public Rectangle2D[] rectCommandersCards;
	
	public Rectangle convene;
	
	public ArrayList<Rectangle2D> optionBoxArea;
	public ArrayList<String> optionBoxString;
	public boolean optionBox;
	public int optionBoxBattlefield;
	
	public int cartasEscolhida = 0;
	
	private GamePlayScene parent;
	private Player player;
	
	private String namePrepared = "";
	private int damagePrepared;
	private int speedPrepared;
	private float resistencePrepared;
	private int coastPrepared;
	private float buildPrepared;
	
	public UI(GamePlayScene parent){
		this.parent = parent;
		this.width = parent.width;
		this.height = parent.height;
		
		player = GamePlayScene.player;
		
		optionBoxArea = new ArrayList<Rectangle2D>();
		optionBoxString = new ArrayList<String>();
		optionBox = false;
		optionBoxBattlefield = -1;
		
		rectUnitsCards = new Rectangle2D[player.units.length];
        for(int i=0; i<rectUnitsCards.length; i++){
        	rectUnitsCards[i] = new Rectangle((i*(int)(width*0.05f))+(int)(width*0.01f), (int)(height*0.88f), (int)(width*0.04f), (int)(height*0.1f));
        }
        
        rectCommandersCards = new Rectangle2D[player.commanders.length];
        for(int i=0; i<rectCommandersCards.length; i++){
        	rectCommandersCards[i] = new Rectangle((i+rectUnitsCards.length+1)*(int)(width*0.05f)+(int)(width*0.01f), (int)(height*0.88f), (int)(width*0.04f), (int)(height*0.1f));
        }
        
        convene = new Rectangle((int)(width*0.85f), (int)(height*0.93f), (int)(width*0.1f), (int)(height*0.06f));
	}
	
	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render(Graphics2D graphics) {
    	/*Gold, points*/
        parent.font = new Font("PAPYRUS", Font.BOLD, 12);
        graphics.setFont( parent.font);
        
        graphics.setColor(Color.orange);
        graphics.drawString(""+(int)player.gold, 20, 20);
        graphics.drawString(""+(int)player.points, 20, 40);
        
    	/*Cards box*/
        graphics.drawImage(Image.footer, 0, (int)((height)-height*0.12f), width, (int)(height*0.12f), null);
        
        for(int i=0; i<player.units.length; i++){
        	graphics.drawImage(Image.cards[player.units[i]], (int)rectUnitsCards[i].getX(), (int)rectUnitsCards[i].getY(), (int)rectUnitsCards[i].getWidth(), (int)rectUnitsCards[i].getHeight(), null);
        }
        
        for(int i=0; i<player.commanders.length; i++){
        	graphics.drawImage(Image.cards[player.commanders[i]], (int)rectCommandersCards[i].getX(), (int)rectCommandersCards[i].getY(), (int)rectCommandersCards[i].getWidth(), (int)rectCommandersCards[i].getHeight(), null);
        }

        if(optionBox){
    		for(int i=0; i<optionBoxArea.size(); i++){
        		graphics.setColor(new Color(0,0,0,200));
    			graphics.fillRect((int)optionBoxArea.get(i).getX(), (int)optionBoxArea.get(i).getY(), (int)optionBoxArea.get(i).getWidth(), (int)optionBoxArea.get(i).getHeight());
    			
        		graphics.setColor(Color.WHITE);
        		graphics.drawString(optionBoxString.get(i), (int)optionBoxArea.get(i).getX(), (int)(optionBoxArea.get(i).getY()+optionBoxArea.get(i).getHeight()/2));
    		}
        }
        
        graphics.drawImage(Image.convene, (int)convene.getX(),   (int)convene.getY(),   (int)convene.getWidth(),   (int)convene.getHeight(), null);
        
        graphics.setColor(Color.WHITE);
        graphics.drawString(namePrepared, (int)(width*0.5f), (int)(height*0.95f));
        graphics.drawString(""+damagePrepared, (int)(width*0.55f), (int)(height*0.95f));
        graphics.drawString(""+speedPrepared, (int)(width*0.6f), (int)(height*0.95f));
        graphics.drawString(""+resistencePrepared, (int)(width*0.65f), (int)(height*0.95f));
        graphics.drawString(""+coastPrepared, (int)(width*0.7f), (int)(height*0.95f));
        graphics.drawString(""+buildPrepared, (int)(width*0.75f), (int)(height*0.95f));
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
	
//	FIXME
//	public void setUIText(int i){
//		cartasEscolhida = i;
//		
//		switch (i) {
//		case 0:
//			Scout.initAtributs();
//			namePrepared		=  Scout.name;
//			damagePrepared		= (Scout.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (Scout.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((Scout.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= Scout.coast;
//			buildPrepared 		= (int) Scout.buildTime;
//			break;
//		case 1:
//			BarricadeTurtle.initAtributs();
//			namePrepared		=  BarricadeTurtle.name;
//			damagePrepared		= (BarricadeTurtle.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (BarricadeTurtle.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((BarricadeTurtle.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= BarricadeTurtle.coast;
//			buildPrepared 		= (int) BarricadeTurtle.buildTime;
//			break;
//		case 2:
//			LightArtillery.initAtributs();
//			namePrepared		=  LightArtillery.name;
//			damagePrepared		= (LightArtillery.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (LightArtillery.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((LightArtillery.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= LightArtillery.coast;
//			buildPrepared 		= (int) LightArtillery.buildTime;
//			break;
//		case 3:
//			HeavyArtillery.initAtributs();
//			namePrepared		=  HeavyArtillery.name;
//			damagePrepared		= (HeavyArtillery.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (HeavyArtillery.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((HeavyArtillery.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= HeavyArtillery.coast;
//			buildPrepared 		= (int) HeavyArtillery.buildTime;
//			break;
//		case 4:
//			BigHead.initAtributs();
//			namePrepared		=  BigHead.name;
//			damagePrepared		= (BigHead.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (BigHead.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((BigHead.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= BigHead.coast;
//			buildPrepared 		= (int) BigHead.buildTime;
//			break;
//		case 5:
//			LightInfantry.initAtributs();
//			namePrepared		=  LightInfantry.name;
//			damagePrepared		= (LightInfantry.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (LightInfantry.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((LightInfantry.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= LightInfantry.coast;
//			buildPrepared 		= (int) LightInfantry.buildTime;
//			break;
//		case 6:
//			Roulette.initAtributs();
//			namePrepared		=  Roulette.name;
//			damagePrepared		= (Roulette.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (Roulette.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((Roulette.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= Roulette.coast;
//			buildPrepared 		= (int) Roulette.buildTime;
//			break;
//		case 7:
//			Doctor.initAtributs();
//			namePrepared		=  Doctor.name;
//			damagePrepared		= (Doctor.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (Doctor.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((Doctor.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= Doctor.coast;
//			buildPrepared 		= (int) Doctor.buildTime;
//			break;
//		case 8:
//			Devastating.initAtributs();
//			namePrepared		=  Devastating.name;
//			damagePrepared		= (Devastating.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (Devastating.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((Devastating.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= Devastating.coast;
//			buildPrepared 		= (int) Devastating.buildTime;
//			break;
//		case 9:
//			CatchUp.initAtributs();
//			namePrepared		=  CatchUp.name;
//			damagePrepared		= (CatchUp.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (CatchUp.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((CatchUp.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= CatchUp.coast;
//			buildPrepared 		= (int) CatchUp.buildTime;
//			break;
//		case 10:
//			Rock.initAtributs();
//			namePrepared		=  Rock.name;
//			damagePrepared		= (Rock.meleeDamage+Scout.explosionDamage)/2;
//			speedPrepared 		= (Rock.moveSpeed+Scout.atackSpeed)/2;
//			resistencePrepared 	= (int) ((Rock.meleeResistance+Scout.explosionResistance)/2);
//			coastPrepared 		= Rock.coast;
//			buildPrepared 		= (int) Rock.buildTime;
//			break;
//		}
//		
//	}
}
