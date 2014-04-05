package Manager;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Objects.Battlefield;
import ProjetoIntegrador.GamePanel;

public class StageImport {
	
	public static Rectangle2D []bases;
	public static Battlefield []battlefields;

	public static boolean loadStageFile(String filename){
        FileReader fileReader;
        BufferedReader bufferedReader;
        String line;
        
        try{
            fileReader = new FileReader("res/"+filename);
            bufferedReader = new BufferedReader(fileReader);
            
            bufferedReader.readLine();
            bufferedReader.readLine();
            bufferedReader.readLine();
            
            /*Amount of bases*/
            line = bufferedReader.readLine();
            String[] subString = line.split(" ");
            bases = new Rectangle[Integer.parseInt(subString[1])];
            
            for(int i=0; i<bases.length;i++){
	            line = bufferedReader.readLine();
	            subString = line.split(",");
	            bases[i] = new Rectangle((int)(GamePanel.widthScreen*Float.parseFloat(subString[0])),(int)(GamePanel.heightScreen* Float.parseFloat(subString[1])), 
	            		(int)(GamePanel.widthScreen*Float.parseFloat(subString[2])), (int)(GamePanel.heightScreen*Float.parseFloat(subString[3])));
            }
            
            bufferedReader.readLine();
            bufferedReader.readLine();
            
            /*Amount of battlefields*/
            line = bufferedReader.readLine();
            subString = line.split(" ");
            battlefields = new Battlefield[Integer.parseInt(subString[1])];
            
            for(int i=0; i<battlefields.length;i++){
	            line = bufferedReader.readLine();
	            subString = line.split(",");
	            battlefields[i] = new Battlefield(new Rectangle((int)(GamePanel.widthScreen*Float.parseFloat(subString[0])),(int)(GamePanel.heightScreen* Float.parseFloat(subString[1])), 
	            		(int)(GamePanel.widthScreen*Float.parseFloat(subString[2])), (int)(GamePanel.heightScreen*Float.parseFloat(subString[3]))));
            }
            
            bufferedReader.readLine();
            
            bufferedReader.close();
            fileReader.close();
            
//            currentLanguage = language;
            return true;
        }catch(IOException e){
            return false;
        }
    }
}