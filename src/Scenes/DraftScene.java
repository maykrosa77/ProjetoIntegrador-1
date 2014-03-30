package Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import Manager.Image;
import Manager.Language;
import ProjetoIntegrador.GamePanel;

public class DraftScene extends Scene{

	
    private BufferedImage background;
    private BufferedImage[] cardsImage;
    
    /*Rects in screen in down , represents selected cards*/
    private Rectangle[] unitsRect;
    private Rectangle[] commanderRect;
    private Rectangle[] cancelUnitDraft;
    private Rectangle[] cancelCommanderDraft;
    private Rectangle arrowLeftRect;
    private Rectangle arrowRightRect;
    private Rectangle readyRect;
    
    /*ID that represents selected cards*/
    private int[] unitsSelects;
    private int[] commanderSelects;
    
    /*Cards to choise*/
    private int[] indexSelection;
    private Rectangle[] cardsToDraft;
    
    /** 
    * Constructor, init parameters of scene. 
    * 
    * @param GamePanel instance of gamePanel
    * @param int width of screen
    * @param int height of screen
    */
    public DraftScene(GamePanel gamePanel){
       this.gamePanel = gamePanel;
       this.width  = GamePanel.widthScreen;
       this.height = GamePanel.heightScreen;
       
       this.font = gamePanel.font;
       background = Image.draftMenu;
       cardsImage = Image.cards;
       
       unitsRect = new Rectangle[6];
       cancelUnitDraft = new Rectangle[6];
       for(int i=0; i<6; i++){
    	   unitsRect[i] = new Rectangle((i*(int)(width*0.1f))+(int)(width*0.05f), (int)(height*0.75f), (int)(width*0.08f), (int)(height*0.2f));
    	   cancelUnitDraft[i] = new Rectangle((unitsRect[i].x+unitsRect[i].width)-(int)(width*0.02f), (unitsRect[i].y+unitsRect[i].height)-(int)(height*0.05f), (int)(width*0.02f), (int)(height*0.05f));
       }
       
       commanderRect = new Rectangle[2];
       cancelCommanderDraft = new Rectangle[2];
       for(int i=0; i<2; i++){
    	   commanderRect[i] = new Rectangle(((i+7)*(int)(width*0.1f))+(int)(width*0.05f), (int)(height*0.75f), (int)(width*0.08f), (int)(height*0.2f));
    	   cancelCommanderDraft[i] = new Rectangle((commanderRect[i].x+commanderRect[i].width)-(int)(width*0.02f), (commanderRect[i].y+commanderRect[i].height)-(int)(height*0.05f), (int)(width*0.02f), (int)(height*0.05f));
       }
       
       arrowLeftRect= new Rectangle(0, (int)(height*0.45f), (int)(width*0.05f), (int)(height*0.1f));
       arrowRightRect= new Rectangle((int)(width*0.95f), (int)(height*0.45f), (int)(width*0.05f), (int)(height*0.1f));
       readyRect= new Rectangle((int)(width*0.45f), (int)(height*0.12f), (int)(width*0.1f), (int)(height*0.1f));
       
       unitsSelects = new int[6];
       for(int i=0; i<unitsSelects.length; i++)
    	   unitsSelects[i] = -1;
    	   
       commanderSelects = new int[2];
       for(int i=0; i<2; i++)
    	   commanderSelects[i] = -1;
       
       cardsToDraft = new Rectangle[5];
       indexSelection = new int[5];
       for(int i=0; i<5; i++){
    	   cardsToDraft[i] = new Rectangle((i*(int)(width*0.18f))+(int)(width*0.06f), (int)(height*0.25f), (int)(width*0.16f), (int)(height*0.4f));
    	   indexSelection[i] = i-2;
    	   if(indexSelection[i]<0)
    		   indexSelection[i] +=40;
       }
    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			backwardIndex();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			forwardIndex();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {
		for(int i=0; i<cardsToDraft.length; i++){
			if(cardsToDraft[i].contains(e.getPoint())){
				/*Choise commander*/
				if(indexSelection[i]>29){
					if(commanderSelects[0] == -1)
						commanderSelects[0] = indexSelection[i];
					else
						commanderSelects[1] = indexSelection[i];
				/*Choise unit*/
				}else{
					for(int j=0; j<unitsSelects.length; j++){
						if(unitsSelects[j] > -1){
							continue;
						}else{
							unitsSelects[j] = indexSelection[i];
							break;
						}
					}
				}
			}
		}
		
		/*Cancel a unit pick*/
		for(int i=0; i<cancelUnitDraft.length; i++){
			if(cancelUnitDraft[i].contains(e.getPoint()))
				unitsSelects[i] = -1;	
		}
		
		/*Cancel a commander pick*/
		for(int i=0; i<cancelCommanderDraft.length; i++){
			if(cancelCommanderDraft[i].contains(e.getPoint()))
				commanderSelects[i] = -1;	
		}
		
		if(arrowLeftRect.contains(e.getPoint()))
			backwardIndex();
		
		if(arrowRightRect.contains(e.getPoint()))
			forwardIndex();
		
		if(readyRect.contains(e.getPoint())){
			if(readyToStart())
				GamePanel.currentScene = new GamePlayScene(gamePanel);
			
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render(Graphics2D graphics) {
        fontMetrics = graphics.getFontMetrics();
    	
        /*Draw background image, fill all screen*/
        graphics.drawImage(background, 0, 0, width, height, null);
        
        /*Set font to subTitles*/
        graphics.setColor(Color.ORANGE);
        font = new Font("PAPYRUS", Font.BOLD, 32);
        graphics.setFont(font);
        
        /*Scene title*/    
        int x = ((width-60) - fontMetrics.stringWidth(Language.texts[1]))/2;
        int y = (int)(height*0.1f);
        graphics.drawString(Language.texts[1], x, y);
        
        /*Set font to subTitles*/
        graphics.setColor(Color.ORANGE);
        font = new Font("PAPYRUS", Font.BOLD, 16);
        graphics.setFont(font);
        
        graphics.setColor(Color.BLACK);
        for(int i=0; i<6; i++){
        	graphics.fillRect(unitsRect[i].x, unitsRect[i].y, unitsRect[i].width, unitsRect[i].height);
        	if(unitsSelects[i]>-1){
        		graphics.drawImage(cardsImage[unitsSelects[i]], unitsRect[i].x, unitsRect[i].y, unitsRect[i].width, unitsRect[i].height,null);
        	}
        	graphics.drawImage(Image.cancelDraft, cancelUnitDraft[i].x, cancelUnitDraft[i].y, cancelUnitDraft[i].width, cancelUnitDraft[i].height,null);
        }
        
        for(int i=0; i<2; i++){
        	graphics.fillRect(commanderRect[i].x, commanderRect[i].y, commanderRect[i].width, commanderRect[i].height);
        	if(commanderSelects[i]>-1){
        		graphics.drawImage(cardsImage[commanderSelects[i]], commanderRect[i].x, commanderRect[i].y, commanderRect[i].width, commanderRect[i].height,null);
        	}
        	graphics.drawImage(Image.cancelDraft, cancelCommanderDraft[i].x, cancelCommanderDraft[i].y, cancelCommanderDraft[i].width, cancelCommanderDraft[i].height,null);
        }
        
        for(int i=0; i<5; i++){
        	graphics.drawImage(cardsImage[indexSelection[i]], cardsToDraft[i].x, cardsToDraft[i].y, cardsToDraft[i].width, cardsToDraft[i].height, null);
        }
        
        graphics.setColor(Color.BLACK);
        graphics.fillRect(readyRect.x, readyRect.y, readyRect.width, readyRect.height);
        
        graphics.setColor(Color.WHITE);
        x = ((width) - fontMetrics.stringWidth(Language.texts[2]))/2;
        y = (int)(height*0.18f);
        graphics.drawString(Language.texts[2], x, y);
        
        /*Arrows draw*/
        graphics.drawImage(Image.arrowLeft, arrowLeftRect.x, arrowLeftRect.y, arrowLeftRect.width, arrowLeftRect.height, null);
        graphics.drawImage(Image.arrowRight, arrowRightRect.x, arrowRightRect.y, arrowRightRect.width, arrowRightRect.height, null);
	}
	
	private void forwardIndex(){
	       for(int i=0; i<5; i++){
	    	   indexSelection[i]++;
	    	   if(indexSelection[i]>39)
	    		   indexSelection[i] -=40;
	       }
	}
	
	private void backwardIndex(){
	       for(int i=0; i<5; i++){
	    	   indexSelection[i]--;
	    	   if(indexSelection[i]<0)
	    		   indexSelection[i] +=40;
	       }
	}
	
	private boolean readyToStart(){
		for(int i=0; i<unitsSelects.length; i++){
			if(unitsSelects[i] == -1)
				return false;
		}
		
		for(int i=0; i<commanderSelects.length; i++){
			if(commanderSelects[i] == -1)
				return false;
		}
		
		return true;
	}
}
