
public class Persona {

	public String nombre;
	public String appelido;

	public Persona(String nombre, String appelido) {

		this.nombre = nombre;
		this.appelido = appelido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAppelido() {
		return appelido;
	}
	public String getNombreCompleto() {
		return appelido + ", " + nombre;
	}
	
	
	public static void main(String[] args) {
		Persona p= new Persona("raquel", "Jimenez");
		
		
	}
}
