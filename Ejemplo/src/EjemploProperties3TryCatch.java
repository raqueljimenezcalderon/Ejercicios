import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EjemploProperties3TryCatch {
 //esta es la forma recomendada de leer y cargar ficheros en properties, esta es una nueva
// version, es mas corta, java se encarga de cerrar el fichero para evitar el consumo de re-
// cursos 

	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream(new File("config.ini"))) {
			Properties p = new Properties();
			p.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar este fichero");
		}catch(IOException e){
			System.out.println("No se ha podido leer el fichero");
		}

	}
}
