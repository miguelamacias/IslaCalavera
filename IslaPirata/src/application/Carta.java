package application;

import java.security.SecureRandom;

public class Carta {
	public enum ValorCarta {ORO, DIAMANTE, COFRE, PIRATA, CALAVERA, DOBLE_CALAVERA, MONO_LORO, BRUJA, BARCO_2X, BARCO_3X, BARCO_4X};
	private ValorCarta valor;
	
	public String sacarCarta() {
		SecureRandom generadorAleatorio = new SecureRandom();
		valor = ValorCarta.values()[generadorAleatorio.nextInt(7)]; //Cuando se implementen la bruja y barcos cambiar el número por un 11.
		return valor.name();
	}
	
	public ValorCarta getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return valor.name();
	}
}
