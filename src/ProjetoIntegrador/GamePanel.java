package ProjetoIntegrador;

import Manager.Language;
import Scenes.GamePlayScene;
import Scenes.Scene;
import Scenes.MenuScene;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
* GamePanel, create and init graphics and double buffer to draw,
* the game loop and imputs of keyboard and mouse.
* 
* @author Gregorio
* @version 1.0
*/
public class GamePanel extends JPanel implements Runnable {

	public static Scene currentScene = null;
    
    public static int widthScreen = 1280;
    public static int heightScreen = 720;
    
	private static final long serialVersionUID = 1L;
	
    public int fps;    /*Frame per minute*/
    
    public Font font;
    
    private Graphics2D graphics;
    private BufferedImage bufferImage;
    private Thread mainThread;

    private boolean running = false;
    
    private int sfps;   /*Seconds frame per minute*/
    
    /** 
    * Constructor, init parameters of jPanel. Create canvas.
    * Add listeners and set scene.
    */
    public GamePanel() {
        /*Init first scene.*/
        Language.loadLanguage(Language.ENGLISH);
        currentScene = new MenuScene(this);
//        currentScene = new GamePlayScene(this, new int[]{0,0,0,0,0,0}, new int[]{30,30});
        
        prepareFrame();
        
        /*Create and manager imputs.*/
        listener();
        
        /*Create and set font configurations.*/
        font = new Font("PAPYRUS", Font.BOLD, 12);
        graphics.setFont(font);
    }
    
    public void prepareFrame(){
    	currentScene.width = widthScreen;
    	currentScene.height = heightScreen;
    	
    	bufferImage = null;
    	
    	setBackground(Color.white);
        setPreferredSize(new Dimension(widthScreen, heightScreen));
        setFocusable(true);
        requestFocus(); /* JPanel now receives key events.*/
        
        /*Create buffered image and graphics, use to draw
         * the screen and double buffer.*/
        if (bufferImage == null) {
            bufferImage = new BufferedImage(widthScreen, heightScreen,BufferedImage.TYPE_INT_ARGB);
            if (bufferImage == null) {
                System.out.println("BufferImage is null");
            }else {
                graphics = (Graphics2D)bufferImage.getGraphics();
            }
        }
    }
    
    /** 
    * Add notify and start game.
    */
    @Override
    public void addNotify() {
        super.addNotify();
        startGame();
    }
    
    /** 
    * Add notify and start game.
    */
    public void exitGame() {       
        running = false;
    }

    /** 
    * The main thread when started call the run methods. 
    */
    @Override
    public void run() {
        running = true;

        long difTime = 0;
        int seconds = 0;
        long lastTime = System.currentTimeMillis();
        
        /*Game loop*/
        while(running) {
            gameUpdate((int)difTime);
            gameRender();
            paintImmediately(0, 0, widthScreen, heightScreen);
            
            difTime = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            /*Is a new second.*/
            if(seconds!=((int)(lastTime/1000))){
                fps = sfps;
                sfps = 1;
                seconds = ((int)(lastTime/1000));
            }else{
                sfps++;
            }
        }
        
        /*End game, close windows.*/
        System.exit(0);
    }
   
    /** 
    * Paint component, double buffer. 
    * 
    * @param Graphics graphics
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bufferImage != null){
            g.drawImage(bufferImage, 0, 0, null);
        }
    }
    
    /** 
    * Add and manager listeners of keyboard and mouse.
    */
    private void listener(){
        addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(currentScene!=null) {
                    currentScene.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e ) {
                if(currentScene!=null) {
                    currentScene.keyReleased(e);
                }
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(currentScene!=null) {
                    currentScene.mouseMoved(e);
		}
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if(currentScene!=null) {
                    currentScene.mouseDragged(e);
		}
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(currentScene!=null) {
                    currentScene.mouseReleased(e);
		}
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if(currentScene!=null) {
                    currentScene.mousePressed(e);
		}
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(currentScene!=null) {
                    currentScene.mouseClicked(e);
		}
            }
        });
    }
    
    /** 
    * Start game, start main thread.
    */
    private void startGame(){
        if (mainThread == null || !running) {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }
    
    /** 
    * Update variables of current scene.
    * 
    * @param int different time between frames.
    */
    private void gameUpdate(int difTime) { 
        currentScene.update(difTime);
    }
    
    /** 
    * Render variables of current scene.
    */
    private void gameRender() {
        currentScene.render(graphics);
    }
}
