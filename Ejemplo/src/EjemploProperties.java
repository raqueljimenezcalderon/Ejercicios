import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EjemploProperties {

	public static void main(String[] args) {
		// nos permite guardar en un fichero
		Properties p = new Properties();
		p.setProperty("carpeta", "c:\\user\\all\\datos");
		p.setProperty("usuario", "maria");
		p.setProperty("ventana", "6840,480");

		try {

			FileOutputStream fos = new FileOutputStream(new File("config.ini")); //me lo hace en la raiz del proyecto
			
			try {
				// lo guarda con store
				p.store(fos, "Esto es un ejemplo de configuracion");
				fos.close();
			} catch (IOException e) {
				System.out.println("No se ha podido escribir en el fichero" + e.getMessage());
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					//
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido abrir el fichero" + e.getMessage());

		}
		System.out.println("Programa terminado");

	}

}
