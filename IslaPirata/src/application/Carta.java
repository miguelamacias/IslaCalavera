package application;

import java.security.SecureRandom;

public class Carta {
	private enum Valores {ORO, DIAMANTE, BRUJA, COFRE, PIRATA, CALAVERA, DOBLE_CALAVERA, BARCO_2X, BARCO_3X, BARCO_4X, MONO_LORO};
	private Valores valor;
	
	public String sacarCarta() {
		SecureRandom generadorAleatorio = new SecureRandom();
		valor = Valores.values()[generadorAleatorio.nextInt(11)];
		return valor.name();
	}
	
	@Override
	public String toString() {
		return valor.name();
	}
}
