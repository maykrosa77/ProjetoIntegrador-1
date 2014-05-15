package Manager;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import ProjetoIntegrador.GamePanel;
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
    
    public static BufferedImage barricadeTurtleG = null;
    public static BufferedImage barricadeTurtleR = null;
    
    public static BufferedImage LightArtilleryG = null;
    public static BufferedImage LightArtilleryR = null;
    
    public static BufferedImage HeavyArtilleryG = null;
    public static BufferedImage HeavyArtilleryR = null;
    
    public static BufferedImage BigHeadG = null;
    public static BufferedImage BigHeadR = null;
    
    public static BufferedImage LightInfantryG = null;
    public static BufferedImage LightInfantryR = null;
    
    public static BufferedImage RouletteG = null;
    public static BufferedImage RouletteR = null;
    
    public static BufferedImage DoctorG = null;
    public static BufferedImage DoctorR = null;
    
    public static BufferedImage DevastatingG = null;
    public static BufferedImage DevastatingR = null;
    
    public static BufferedImage CatchUpG = null;
    public static BufferedImage CatchUpR = null;
    
    public static BufferedImage RockG = null;
    public static BufferedImage RockR = null;
    
    /**
    * Init images statically.
    */
    public Image(){
        /*Scene background*/
		mainMenu =  LoadImageRes("main_menu.png");
		draftMenu =  LoadImageRes("draft_menu.png");
		
		/*UI elements*/
		languageIcons = new BufferedImage[2];
		languageIcons[0] = LoadImageRes("ui"+StaticContent.separetor+"english_flag.png");
		languageIcons[1] = LoadImageRes("ui"+StaticContent.separetor+"portugues_flag.png");
		
		sfxIcons = new BufferedImage[2];
		sfxIcons[0] = LoadImageRes("ui"+StaticContent.separetor+"sfx_on.png");
		sfxIcons[1] = LoadImageRes("ui"+StaticContent.separetor+"sfx_off.png");
		
		soundTrackIcons = new BufferedImage[2];
		soundTrackIcons[0] = LoadImageRes("ui"+StaticContent.separetor+"sound_track_on.png");
		soundTrackIcons[1] = LoadImageRes("ui"+StaticContent.separetor+"sound_track_off.png");
		
		cancelDraft = LoadImageRes("ui"+StaticContent.separetor+"cancel_draft.png");
		arrowLeft = LoadImageRes("ui"+StaticContent.separetor+"arrow_left.png");
		arrowRight =LoadImageRes("ui"+StaticContent.separetor+"arrow_right.png");
		
		footer = LoadImageRes("ui"+StaticContent.separetor+"footer.png");
		convene = LoadImageRes("ui"+StaticContent.separetor+"convene.png");
		
		/*Gameplay elements*/
		terrain =  LoadImageRes("mapaBase.png");
		
		flagDown      =  LoadImageRes("sprite"+StaticContent.separetor+"baseTorreNova.png");
		flagHoistingG =  LoadImageRes("sprite"+StaticContent.separetor+"AnimacaoTorreVerde.png");
		flagHoistingR =  LoadImageRes("sprite"+StaticContent.separetor+"AnimacaoTorreVermelha.png");
		flagHoistedG  =  LoadImageRes("sprite"+StaticContent.separetor+"spriteTorreVerdeVento.png");
		flagHoistedR  =  LoadImageRes("sprite"+StaticContent.separetor+"spriteTorreVermelhoVento.png");
		
		cards = new BufferedImage[40];
		for(int i=0; i<cards.length; i++)
			cards[i] =  LoadImageRes("cards"+StaticContent.separetor+"card"+i+".png");
		
		scoutG = LoadImageRes("sprite"+StaticContent.separetor+"spriteBatedorVerde.png");
		scoutR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteBatedorVermelho.png");
		
		barricadeTurtleG = LoadImageRes("sprite"+StaticContent.separetor+"spriteTurtle.png");
		barricadeTurtleR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteTurtleNPC.png");
		
		LightArtilleryG = LoadImageRes("sprite"+StaticContent.separetor+"spriteArtilharia.png");
		LightArtilleryR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteArtilhariaNPC.png");
		
		HeavyArtilleryG = LoadImageRes("sprite"+StaticContent.separetor+"spriteArtilhariaAvancada.png");
		HeavyArtilleryR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteArtilhariaAvancadaNPC.png");
		
		BigHeadG = LoadImageRes("sprite"+StaticContent.separetor+"spriteBigheadSmallBrain.png");
		BigHeadR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteBigheadNPC.png");
		
		LightInfantryG = LoadImageRes("sprite"+StaticContent.separetor+"spariteInfantaria.png");
		LightInfantryR =  LoadImageRes("sprite"+StaticContent.separetor+"spariteInfantariaNPC.png");
		
		RouletteG = LoadImageRes("sprite"+StaticContent.separetor+"spriteRoleta.png");
		RouletteR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteRoletaNPC.png");
		
		DoctorG = LoadImageRes("sprite"+StaticContent.separetor+"spriteMedico.png");
		DoctorR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteMedicoNPC.png");
		
		DevastatingG = LoadImageRes("sprite"+StaticContent.separetor+"spriteDevastador.png");
		DevastatingR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteDevastadorNPC.png");
		
		CatchUpG = LoadImageRes("sprite"+StaticContent.separetor+"spritePegapega.png");
		CatchUpR =  LoadImageRes("sprite"+StaticContent.separetor+"spritePegapegaNPC.png");
		
		RockG = LoadImageRes("sprite"+StaticContent.separetor+"spriteRock.png");
		RockR =  LoadImageRes("sprite"+StaticContent.separetor+"spriteRockNPC.png");
    }
    
    public static BufferedImage LoadImageRes(String filename) {
		BufferedImage imtmp;

		try {
			imtmp = ImageIO.read(GamePanel.instance.getClass().getResource("/" + filename));
		} catch (Exception e) {
			imtmp = null;
			System.out.println("ERROR LOAD IMAGE---> " + filename);
			e.printStackTrace();
			return null;
		}

		BufferedImage imgfinal = new BufferedImage(imtmp.getWidth(), imtmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
		imgfinal.getGraphics().drawImage(imtmp, 0, 0, null);
		
		/*Emptying allocated memory*/
		imtmp = null;

		return imgfinal;
	}
}
