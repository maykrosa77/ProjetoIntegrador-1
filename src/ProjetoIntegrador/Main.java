package ProjetoIntegrador;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

/**
* Main class, create the window and start the Game Panel.
* 
* @author Gregorio
* @version 1.0
*/
public class Main {
    /** 
    * Main methods.
    * 
    * @param String[] argumets
    */
    public static void main(String[] args) {
        final GamePanel gamePanel = new GamePanel();
        final JFrame jframe = new JFrame("Projeto Integrador");
        
        jframe.getContentPane().add(gamePanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	GamePanel.widthScreen  = jframe.getComponent(0).getWidth();
            	GamePanel.heightScreen = jframe.getComponent(0).getHeight();
            	
                gamePanel.prepareFrame();
            }
        });
    }
}
