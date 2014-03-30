package Manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Language {
    
    public final static String ENGLISH = "english";
    public final static String PORTUGUES = "portugues";
    
    public static String[] texts;
    public static String currentLanguage;
    
    public static boolean loadLanguage(String language){
        FileReader fileReader;
        BufferedReader bufferedReader;
        String line;
        
        try{
            fileReader = new FileReader("res/language/"+language+".txt");
            bufferedReader = new BufferedReader(fileReader);
//            
            while((line = bufferedReader.readLine())!=null){
                //Discards empty lines.
                if (line.isEmpty())
                    continue;

                //Discart comments.
                if(line.contains("#"))
                    continue;

                String[] subString = line.split("=");
                
                //Remove black space of sides
                for(int i=0; i<subString.length; i++)
                    subString[i] = subString[i].trim();
                
                //Discovering the amount of Strings
                if(subString[0].equals("qtd")){
                    int qtd = Integer.parseInt(subString[1]);
                    texts = new String[qtd];
                }else{ //Saving the Strings
                    int id = Integer.parseInt(subString[0]);
                    texts[id] = subString[1];
                }       
            }
            
            bufferedReader.close();
            fileReader.close();
            
            currentLanguage = language;
            return true;
        }catch(IOException e){
            return false;
        }
    }
    
}
