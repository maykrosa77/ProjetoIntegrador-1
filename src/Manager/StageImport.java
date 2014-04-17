package Manager;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import IA.PathMove;
import Objects.Battlefield;
import ProjetoIntegrador.GamePanel;

public class StageImport {
	
	public static Rectangle2D []bases;
	public static Battlefield []battlefields;
	public static PathMove []pathMove;

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
	            		(int)(GamePanel.widthScreen*Float.parseFloat(subString[2])), (int)(GamePanel.heightScreen*Float.parseFloat(subString[3]))), i);
            }
            
            bufferedReader.readLine();
            bufferedReader.readLine();
            line = bufferedReader.readLine();
            subString = line.split(" ");
            pathMove = new PathMove[Integer.parseInt(subString[1])];
            int i = 0;
            
            while((line=bufferedReader.readLine())!=null){
                subString = line.split(" ");
            	int quant = Integer.parseInt(subString[1]);
            	
            	int origin = 0;
            	int destination = 0;
            	ArrayList<FPoint> paths = new ArrayList<FPoint>();
            	for(int j=0; j<quant; j++){
                	line = bufferedReader.readLine();
                    subString = line.split(",");
                    for(int h=0; h<subString.length; h++)
                        subString[h] = subString[h].trim();
                    
                    origin =  Integer.parseInt(subString[0]);
                    destination =  Integer.parseInt(subString[1]);
                    paths.add(new FPoint(Float.parseFloat(subString[2]), Float.parseFloat(subString[3])));

            	}
            	pathMove[i] = new PathMove(origin, destination, paths);
                bufferedReader.readLine();
            	i++;
            }
            
            bufferedReader.close();
            fileReader.close();
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
