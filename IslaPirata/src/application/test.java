package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class test {

	public static void main(String[] args) throws Exception {
		//Class.forName("org.mariadb.jdbc.Driver");
		
		String nombre = "Francisco Pére ç@ñ€";
		int puntuacion = 158748547;
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/BC7Yxrr0d0", "BC7Yxrr0d0", "HqgJ0PxyA1");
		PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO puntuaciones (nombre, puntuacion) VALUES(?, ?)");
		sentencia.setString(1, nombre);
		sentencia.setInt(2, puntuacion);
		sentencia.executeUpdate();
	}

}
