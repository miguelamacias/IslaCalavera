package application;

import java.security.SecureRandom;


public class Dado {
	private String textoCara;
	private int valorCara;
	private SecureRandom generadorNumeros;
	private String[] caras = {"Calavera", "Diamante", "Espadas", "Loro", "Mono", "Oro"};
	
	public Dado() {
		generadorNumeros = new SecureRandom();
	}
	
	public int tirar() {
		valorCara = generadorNumeros.nextInt(6);
		textoCara = caras[valorCara];
		return valorCara;
	}
	
	public String toString() {
		return valorCara + "" + textoCara;
	}
}
