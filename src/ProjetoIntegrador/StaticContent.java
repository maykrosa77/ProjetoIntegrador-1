package ProjetoIntegrador;

/**
* StaticContent called statically throughout the game, usually constants.
* 
* @author Gregorio
* @version 1.0
*/
public class StaticContent {
    public static String system = System.getProperty("os.name");
    public static String separetor = System.getProperties().get("file.separator").toString();
    
    public static int goldBase = 5000;
}
