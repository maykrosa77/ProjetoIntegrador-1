package Scenes;

import Manager.Image;
import Manager.Language;
import ProjetoIntegrador.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
* MenuScene, scene presentation, configuration and 
* look to start the gameplay.
* 
* @author Gregorio
* @version 1.0
*/
public class MenuScene extends Scene{
       
    private BufferedImage background;
    
    /** 
    * Constructor, init parameters of scene. 
    * 
    * @param GamePanel instance of gamePanel
    * @param int width of screen
    * @param int height of screen
    */
    public MenuScene(GamePanel gamePanel, int width, int height){
       this.gamePanel = gamePanel;
       this.width = width;
       this.height = height;
       
       this.font = gamePanel.font;
       
       background = Image.menuBackground;
       
       Language.loadLanguage(Language.PORTUGUES);
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}    
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
    	GamePanel.currentScene = new GamePlayScene(gamePanel, width, height);
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
        
    /** 
    * Update objects of scene. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {

    }

    /** 
    * Render objects of scene.
    * 
    * @param Graphics2D graphics
    */
    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(background, 0, 0, width, height, null);
        
        graphics.setColor(Color.orange);
        graphics.drawString(Language.texts[0], width*0.45f, height*0.8f);
    }

}
