package ProjetoIntegrador;


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
        GamePanel gamePanel = new GamePanel();
        JFrame jframe = new JFrame("Projeto Integrador");
        jframe.getContentPane().add(gamePanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
