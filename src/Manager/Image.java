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

	/*Scene background*/
    public static BufferedImage mainMenu = null;
    public static BufferedImage draftMenu = null;
    
	/*UI elements*/
    public static BufferedImage[] languageIcons = null;
    public static BufferedImage[] sfxIcons = null;
    public static BufferedImage[] soundTrackIcons = null;
    
    public static BufferedImage cancelDraft = null;
    public static BufferedImage arrowLeft = null;
    public static BufferedImage arrowRight = null;
    
    public static BufferedImage footer = null;
    public static BufferedImage convene = null;
    
    /*Gameplay elements*/
    public static BufferedImage terrain = null;
    public static BufferedImage[] cards = null;
    
    public static BufferedImage flagDown = null;
    public static BufferedImage flagHoistingG = null;
    public static BufferedImage flagHoistingR = null;
    public static BufferedImage flagHoistedG = null;
    public static BufferedImage flagHoistedR = null;
    
    public static BufferedImage scoutG = null;
    public static BufferedImage scoutR = null;
    
    /**
    * Init images statically.
    */
    static{
        try {
        	/*Scene background*/
            mainMenu =  ImageIO.read(new File(StaticContent.resourceDirectory+"main_menu.png"));
            draftMenu =  ImageIO.read(new File(StaticContent.resourceDirectory+"draft_menu.png"));
            
        	/*UI elements*/
            languageIcons = new BufferedImage[2];
            languageIcons[0] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"english_flag.png"));
            languageIcons[1] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"portugues_flag.png"));
            
            sfxIcons = new BufferedImage[2];
            sfxIcons[0] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"sfx_on.png"));
            sfxIcons[1] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"sfx_off.png"));
            
            soundTrackIcons = new BufferedImage[2];
            soundTrackIcons[0] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"sound_track_on.png"));
            soundTrackIcons[1] = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"sound_track_off.png"));
            
            cancelDraft = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"cancel_draft.png"));
            arrowLeft = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"arrow_left.png"));
            arrowRight = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"arrow_right.png"));
            
            footer = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"footer.png"));
            convene = ImageIO.read(new File(StaticContent.resourceDirectory+"ui"+StaticContent.separetor+"convene.png"));
            
            /*Gameplay elements*/
            terrain =  ImageIO.read(new File(StaticContent.resourceDirectory+"mapaBase.png"));
            
            flagDown      =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"baseTorreNova.png"));
            flagHoistingG =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"AnimacaoTorreVerde.png"));
            flagHoistingR =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"AnimacaoTorreVermelha.png"));
            flagHoistedG  =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"spriteTorreVerdeVento.png"));
            flagHoistedR  =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"spriteTorreVermelhoVento.png"));
            
            cards = new BufferedImage[40];
            for(int i=0; i<cards.length; i++)
            	cards[i] =  ImageIO.read(new File(StaticContent.resourceDirectory+"cards"+StaticContent.separetor+"card"+i+".png"));
            
            scoutG =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"spriteBatedorVerde.png"));
            scoutR =  ImageIO.read(new File(StaticContent.resourceDirectory+"sprite"+StaticContent.separetor+"spriteBatedorVermelho.png"));
        } catch (IOException e) {
            System.out.println("ERROR LOAD IMAGES");
        }
    }
}
