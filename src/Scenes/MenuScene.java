package Scenes;

import Manager.Image;
import Manager.Language;
import Manager.Sound;
import ProjetoIntegrador.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * MenuScene, scene presentation, configuration and look to start the gameplay.
 * 
 * @author Gregorio
 * @version 1.0
 */
public class MenuScene extends Scene {

	private BufferedImage background;
	private BufferedImage languageFlag;
	private BufferedImage soundTrackImage;
	private BufferedImage sfxImage;

	private Rectangle startRect;
	private Rectangle languageRect;
	private Rectangle soundTrackRect;
	private Rectangle sfxRect;

	/**
	 * Constructor, init parameters of scene.
	 * 
	 * @param GamePanel
	 *            instance of gamePanel
	 * @param int width of screen
	 * @param int height of screen
	 */
	public MenuScene(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.width = GamePanel.widthScreen;
		this.height = GamePanel.heightScreen;

		this.font = gamePanel.font;
		background = Image.mainMenu;

		languageFlag = Image.languageIcons[0];
		soundTrackImage = Image.soundTrackIcons[0];
		sfxImage = Image.sfxIcons[0];

		startRect = new Rectangle((int) (width * 0.35f),
				(int) (height * 0.65f), (int) (width * 0.3f), 48);
		languageRect = new Rectangle((int) (width * 0.8f),
				(int) (height * 0.05f), (int) (width * 0.05f),
				(int) (height * 0.1f));
		soundTrackRect = new Rectangle((int) (width * 0.85f),
				(int) (height * 0.05f), (int) (width * 0.05f),
				(int) (height * 0.1f));
		sfxRect = new Rectangle((int) (width * 0.9f), (int) (height * 0.05f),
				(int) (width * 0.05f), (int) (height * 0.1f));
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (startRect.contains(e.getPoint())) {
			GamePanel.currentScene = new DraftScene(gamePanel);
		}

		if (languageRect.contains(e.getPoint())) {
			Language.loadLanguage(Language.currentLanguage == Language.ENGLISH ? Language.PORTUGUES
					: Language.ENGLISH);
			languageFlag = Language.currentLanguage == Language.ENGLISH ? Image.languageIcons[0]
					: Image.languageIcons[1];
		}

		if (soundTrackRect.contains(e.getPoint())) {
			Sound.soundTrackVolume = Sound.soundTrackVolume == 1 ? 0 : 1;
			soundTrackImage = Sound.soundTrackVolume == 1 ? Image.soundTrackIcons[0]
					: Image.soundTrackIcons[1];
		}

		if (sfxRect.contains(e.getPoint())) {
			Sound.sfxVolume = Sound.sfxVolume == 1 ? 0 : 1;
			sfxImage = Sound.sfxVolume == 1 ? Image.sfxIcons[0]
					: Image.sfxIcons[1];
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

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
	 * @param Graphics2D
	 *            graphics
	 */
	@Override
	public void render(Graphics2D graphics) {
		fontMetrics = graphics.getFontMetrics();

		/* Draw background image, fill all screen */
		graphics.drawImage(background, 0, 0, width, height, null);

		/* Options */
		graphics.drawImage(languageFlag, languageRect.x, languageRect.y,
				languageRect.width, languageRect.height, null);
		graphics.drawImage(soundTrackImage, soundTrackRect.x, soundTrackRect.y,
				soundTrackRect.width, soundTrackRect.height, null);
		graphics.drawImage(sfxImage, sfxRect.x, sfxRect.y, sfxRect.width,
				sfxRect.height, null);

		/* Set font to title */
		graphics.setColor(Color.ORANGE);
		font = new Font("PAPYRUS", Font.BOLD, 32);
		graphics.setFont(font);

		/* Draw start game string, alling center */
		int x = (width - fontMetrics.stringWidth(Language.texts[0])) / 2;
		int y = (int) (height * 0.7f);
		graphics.drawString(Language.texts[0], x, y);
	}

}
