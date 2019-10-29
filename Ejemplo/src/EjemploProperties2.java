import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EjemploProperties2 {

	public static void main(String[] args) {
		//para leer ficheros 
		
		Properties p= new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(new File("config.ini"));
			try{
				p.load(fis);
				
				System.out.println(p.getProperty("carpeta"));
				System.out.println(p.getProperty("usuario"));
			}catch (IOException e) {
				System.out.println("No se ha podido leer el fichero" + e.getMessage());
			}finally {
				try {
					fis.close();
				}catch(IOException e) {
					//
				}
			}	
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podidod encontrar el fichero" + e.getMessage());
		}
	}

}
