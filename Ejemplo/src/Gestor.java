
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Gestor {

	private Connection conn = null;

	public void conectar() throws BDException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:datos/ejemplo.bd");
		} catch (ClassNotFoundException e) {
			throw new BDException("No se ha podido cargar el driver", e);
		} catch (SQLException e) {
			throw new BDException("No se ha podido conectar a la BD", e);
		}
	}

	public void desconectar() throws BDException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new BDException("No se ha podido cerrar la base de datos", e);
		}
	}

	public void crearTableUsuario() throws BDException {
		try (Statement stmt = conn.createStatement()) {
			String sql = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, name VARCHAR, lastname VARCHAR)";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new BDException("no se ha podido crear la tabla", e);
		}

	}

	public void borrarTablaUsuario() throws BDException {
		try (Statement stmt = conn.createStatement()) {
			String sql = "DROP TABLE usuario";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new BDException("No se ha podido crear la table 'usuario'", e);
		}
	}

	public List<Usuario> obtenerTodosUsuario() throws BDException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id,name,lastname FROM usuario");
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("name"));
				u.setApellido(rs.getString("lastname"));

				usuarios.add(u);
			}

		} catch (SQLException e) {
			throw new BDException("Nose pudo obtener la lista de la tabla 'usuario'", e);
		}

		return usuarios;
	}

	public Usuario obtenerUsuarioPorID(int id) throws BDException {
		try (Statement stmt = conn.createStatement()) {
			String sql = "SELECT id,name,lastname FROM usuario WHERE id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("name"));
				u.setApellido(rs.getString("lastname"));
				return u;
			} else {
				return new Usuario();
			}
		} catch (SQLException e) {
			throw new BDException("Error al obtener el usuario con id " + id, e);
		}
	}

	public List<Usuario> obtenerUsuarioPorApellido(String nombre, String ape) throws BDException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try (Statement stmt = conn.createStatement()) {
			String sql = "SELECT id,name,lastname FROM usuario WHERE lastname='" + ape + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("name"));
				u.setApellido(rs.getString("lastname"));
				usuarios.add(u);
			}
		} catch (SQLException e) {
			throw new BDException("Error al obtener usuarios con apellido '" + ape + "'", e);
		}

		return usuarios;
	}

	public List<Usuario> obtenerUsuarioPorApellidoVersionMejorada(String nombre, String apellido) throws BDException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT id,name,lastname FROM usuario WHERE name=? AND lastname =?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("name"));
				u.setApellido(rs.getString("lastname"));
				usuarios.add(u);
			}
		} catch (SQLException e) {
			throw new BDException("Error al obtener usuarios con apellido '" + apellido + "'", e);
		}

		return usuarios;
	}

	public void guardarUsuario(Usuario u) throws BDException {
		try (Statement stmt = conn.createStatement()) {
			String sql = "INSERT INTO usuario(id,name,lastname)VALUES (" + u.getId() + ",'" + u.getNombre() + "','"
					+ u.getApellido() + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new BDException("Error al obtener el usuario", e);
		}
	}

	public void guardarUsuarioMejor(Usuario u) throws BDException {
		String sql = "INSERT INTO usuario(id,name,lastname)VALUES (?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) { // el preparestatement gestiona esos "?"
			// rellenamos los valores de la plantilla
			stmt.setInt(1, u.getId());
			stmt.setString(2, u.getNombre());
			stmt.setString(3, u.getApellido());

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new BDException("Error al obtener el usuario", e);
		}
	}

}
