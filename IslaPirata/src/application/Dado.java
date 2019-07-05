package application;

import java.security.SecureRandom;

import javafx.scene.control.CheckBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
/**Clase que simula un dado de 6 caras del juego de mesa
 * original Isla Calavera.
 * 
 * @author Miguel Ángel Macías
 * @version 1.0
 *
 */

public class Dado {
	//Atributos de la clase
	private String textoCara;
	private int valorCara;
	private String nombreCaras;
	private String[] caras;
	
	/**
	 * Constructor sin parámetros que inicializa las variables
	 * necesarias para el funcionamiento del dado.
	 */
	public Dado() {
		nombreCaras = "null-Calavera-Diamante-Loro-Mono-Oro-Sable";
		caras = nombreCaras.split("-");
	}
	
	/**
	 * Método que lanza el dado y almacena el valor obtenido.
	 * @return <code>int</code> Valor numérico entre 1 y 6 obtenido al azar.
	 */
	public int tirar() {
		SecureRandom generadorNumeros = new SecureRandom();
		valorCara = generadorNumeros.nextInt(6) + 1;
		textoCara = caras[valorCara];
		return valorCara;
	}
	/**
	 * Método que devuelve una representación en texto de los atributos
	 * actuales del dado.
	 * @return <code>String</code> Representación del estado del dado.
	 */
	public String toString() {
		return valorCara + textoCara;
	}
	/**
	 * Método que devuelve el valor numérico de la cara que tiene
	 * almacenada el dado.
	 * @return <code>int</code> Valor numérico entre 1 y 6 obtenido al azar.
	 */
	public String getTextoCara() {
		return textoCara;
	}
	
	/**
	 * Método que devuelve el nombre textual de la cara que tiene
	 * almacenada el dado.
	 * @return <code>int</code> Valor numérico entre 1 y 6 obtenido al azar.
	 */
	public int getValorCara() {
		return valorCara;
	}
	
	//Métodos estáticos auxiliares para trabajar con el dado desde la interfaz
	public static void cambiarEstadoDado(CheckBox chck) {
		if (chck.isSelected()) {
			chck.setSelected(false);
		} else {
			chck.setSelected(true);
		}
	}
	
	public static void efectosEstadoDado(ImageView img, CheckBox chck) {
		ColorAdjust oscurecer = new ColorAdjust(0,0,-0.5,0);
		ColorAdjust aclarar = new ColorAdjust(0,0,0,0);
		
		if (chck.isSelected()) {
			img.setEffect(aclarar);
		} else {
			img.setEffect(oscurecer);
		}
	}
	
}
