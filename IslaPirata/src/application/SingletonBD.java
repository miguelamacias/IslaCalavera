package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Clase que permite la comunicacion de la aplicacion con la base de datos.
 * Implementa el patrón Singleton para que solo exista un objeto con el
 * que comunicarse con la base de datos.
 * @author Miguel Ángel Macías
 * @version 1.0 RC1
 */

public class SingletonBD {
	//Instancia del objeto que se comunica con la BD
	private static SingletonBD instance = new SingletonBD();

	//Contructor que carga el driver JDBC. Privado para el Singleton.
	private SingletonBD() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	}
	/**
	 * Método que devuelve la instancia única del objeto para
	 * comunicarse con la BD.
	 * @return <code>SingletonBD</code> Instancia de la clase.
	 */
	public static SingletonBD getInstance() {
		return instance;
	}
	
	/**
	 * Método que devuelve la tabla de puntuaciones almacenada en la base de datos.
	 * @return <code>String</code> Puntuaciones almacenadas formateadas como una tabla.
	 */
	public String getPuntuaciones() {
		StringBuilder puntuaciones = new StringBuilder("\n");
		
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet resultado = null;

		try {

			conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/BC7Yxrr0d0?useSSL=false", "BC7Yxrr0d0", PassDB.getPass());
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery("SELECT nombre, puntuacion, fecha FROM puntuaciones ORDER BY puntuacion DESC");
			
			
			while (resultado.next()) {
				Timestamp fecha = resultado.getTimestamp(3);
				puntuaciones.append(String.format("%-25s%-11s%s%n", resultado.getString(1), resultado.getString(2), formatearFecha(fecha)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		
		return puntuaciones.toString();
		
	}
	/**
	 * Método que almacena la puntuación obtenida en la base de datos
	 * @param nombre nombre del jugador
	 * @param puntuacion puntuación obtenida
	 */
	public void guardarPuntuacionBD(String nombre, int puntuacion) {
		Connection conexion = null;
		PreparedStatement sentencia = null;
		
		try {

			conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/BC7Yxrr0d0?useSSL=false", "BC7Yxrr0d0", PassDB.getPass());
			sentencia = conexion.prepareStatement("INSERT INTO puntuaciones (nombre, puntuacion) VALUES(?, ?)");
			if (nombre.length() > 23) {
				sentencia.setString(1, nombre.substring(0, 23));
			} else {
				sentencia.setString(1, nombre);
			}
			sentencia.setInt(2, puntuacion);
			sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}	
	
	/**
	 * Método que devuelve la tabla de puntuaciones almacenada en la base de datos.
	 * @return <code>Text</code> Puntuaciones almacenadas formateadas en un objeto Text de JavaFX
	 * @see getPuntuaciones()
	 */
	public Text getPuntuacionesText() {
		Text tabla = new Text(getPuntuaciones());
		tabla.setFont(new Font("Consolas", 16));
		return tabla;
	}
	
	/**
	 * Método que acepta un objeto Timestamp y devuelve la fecha y hora en formato texto.
	 * @param fecha Timestamp con una determinada fecha y hora.
	 * @return <code>String</code> La fecha en formato texto.
	 */
	public String formatearFecha(Timestamp fecha) {		
		LocalDateTime objetoFecha = fecha.toLocalDateTime().plusHours(2);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy");
		return objetoFecha.format(formato);
	}	
}
