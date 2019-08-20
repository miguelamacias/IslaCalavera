package application;

import java.security.SecureRandom;

public class Carta {
	private enum Valores {ORO, DIAMANTE, COFRE, PIRATA, CALAVERA, DOBLE_CALAVERA, MONO_LORO, BRUJA, BARCO_2X, BARCO_3X, BARCO_4X};
	private Valores valor;
	
	public String sacarCarta() {
		SecureRandom generadorAleatorio = new SecureRandom();
		valor = Valores.values()[generadorAleatorio.nextInt(7)]; //Cuando se implementen la bruja y barcos cambiar el número por un 11.
		return valor.name();
	}
	
	@Override
	public String toString() {
		return valor.name();
	}
}
