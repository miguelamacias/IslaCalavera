package application;

import application.Carta.ValorCarta;

public class Tirada {
		
	private int puntuacion;
	public int[] obtenerTirada(Dado[] dados, Boolean[] dadosMarcados) {
		int[] resultado = new int[7];
		
		//Tira los dados
		for (int i = 1; i < dados.length; i++) {
			if (dadosMarcados[i]) {
				resultado[dados[i].tirar()] ++;
			} else {
				resultado[dados[i].getValorCara()] ++;
			}
		}
		
		return resultado;
	}
	
	public int[] obtenerTiradaCofre(Dado[] dados, Boolean[] dadosMarcados) {
		int[] resultado = new int[7];
		
		//Tira los dados
		for (int i = 1; i < dados.length; i++) {
			if (dadosMarcados[i]) {
				resultado[1] ++; //Se le suma a las calaveras y luego se borran
			} else {
				resultado[dados[i].getValorCara()] ++;
			}
		}
		
		return resultado;
	}
	
	public int calcularPuntuacion(int[] carasObtenidas, ValorCarta carta) {
		//Reinicia la puntuación
		puntuacion = 0;
		
		if (carta != null) {
			//Evalúa la carta y hace el efecto de la misma si es un efecto "de dado"
			switch (carta) {
			case DIAMANTE:
				carasObtenidas[2]++;
				break;
			case ORO:
				carasObtenidas[5]++;
				break;
			case CALAVERA:
				carasObtenidas[1]++;
				break;
			case DOBLE_CALAVERA:
				carasObtenidas[1] += 2;
				break;
			case MONO_LORO:
				carasObtenidas[4] += carasObtenidas[3];
				carasObtenidas[3] = 0;
			default:
				break;
			}
		}
		//100 puntos por cada oro y diamante obtenidos
		puntuacion += carasObtenidas[2] * 100;
		puntuacion += carasObtenidas[5] * 100;
		
		//Suma las puntuaciones por grupos
		for (int i = 1; i < carasObtenidas.length; i++) {
			switch (carasObtenidas[i]) {
			case 3:
				puntuacion += 100;
				break;
			case 4:
				puntuacion += 200;
				break;
			case 5:
				puntuacion += 500;
				break;
			case 6:
				puntuacion +=1000;
				break;
			case 7:
				puntuacion += 2000;
				break;
			case 8:
				puntuacion += 4000;
			default:
				break;
			}
		}
		//Si hay 3 o más calaveras devuelve -1 (sin puntuación)
		if (carasObtenidas[1] >= 3) {
			return -1;
		}
		//Si la carta es un pirata se dobla la puntuación
		if (carta == ValorCarta.PIRATA) {
			puntuacion *= 2;
		}		
		
		return puntuacion;
	}
	
	public int calcularPuntuacionCofre(int[] carasObtenidas) {
		carasObtenidas[1] = 0;
		
		return calcularPuntuacion(carasObtenidas, null);
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
}
