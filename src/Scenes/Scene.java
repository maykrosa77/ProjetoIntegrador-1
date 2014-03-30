package Scenes;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ProjetoIntegrador.GamePanel;

/**
* Abstract class. Scene is a struct of game objects.
* 
* @author Gregorio
* @version 1.0
*/
public abstract class Scene {
    
    public GamePanel gamePanel;
    
    public int width;
    public int height;
    
    public Font font;
    public FontMetrics fontMetrics;
    
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e); 
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);  
    public abstract void mouseClicked(MouseEvent e);
        
    public abstract void update(int difTime);
    public abstract void render(Graphics2D graphics);
}