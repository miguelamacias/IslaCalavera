package application;

import java.security.SecureRandom;


public class Dado {
	private String textoCara;
	private int valorCara;
	private SecureRandom generadorNumeros;
	private String nombreCaras;
	private String[] caras;
	
	public Dado() {
		generadorNumeros = new SecureRandom();
		nombreCaras = "null-Calavera-Diamante-Loro-Mono-Oro-Sable";
		caras = nombreCaras.split("-");
	}
	
	public int tirar() {
		valorCara = generadorNumeros.nextInt(6) + 1;
		textoCara = caras[valorCara];
		return valorCara;
	}
	
	public String toString() {
		return valorCara + textoCara;
	}
	
	public String getValorCara() {
		return String.valueOf(valorCara);
	}
	
}
