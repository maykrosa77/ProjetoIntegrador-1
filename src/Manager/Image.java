package Manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ProjetoIntegrador.StaticContent;

/**
* Image load and maneger all image of game.
* 
* @author Gregorio
* @version 1.0
*/
public class Image {

    public static BufferedImage menuBackground = null;
    public static BufferedImage terrain = null;
    
    public static BufferedImage[] cards = null;
    
    public static BufferedImage soldier = null;
    public static BufferedImage archer = null;
    
    /**
    * Init images statically.
    */
    static{
        try {
            menuBackground =  ImageIO.read(new File(StaticContent.resourceDirectory+"menu.png"));
            terrain =  ImageIO.read(new File(StaticContent.resourceDirectory+"terrain.png"));
            
            cards = new BufferedImage[2];
            for(int i=0; i<cards.length; i++)
            	cards[i] =  ImageIO.read(new File(StaticContent.resourceDirectory+"card"+i+".png"));
            
            soldier =  ImageIO.read(new File(StaticContent.resourceDirectory+"soldier.png"));
            archer =  ImageIO.read(new File(StaticContent.resourceDirectory+"archer.png"));
        } catch (IOException e) {
            System.out.println("ERROR LOAD IMAGES");
        }
    }
}
