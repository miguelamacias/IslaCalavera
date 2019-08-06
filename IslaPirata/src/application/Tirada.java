package application;

public class Tirada {
		
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
	
	public int calcularPuntuacion(int[] carasObtenidas, String carta) {
		//Reinicia la puntuación
		int puntuacion = 0;
		
		//Evalúa la carta y hace el efecto de la misma si es un efecto "de dado"
		switch (carta) {
		case "DIAMANTE":
			carasObtenidas[2]++;
			break;
		case "ORO":
			carasObtenidas[5]++;
			break;
		case "CALAVERA":
			carasObtenidas[1]++;
			break;
		case "DOBLE_CALAVERA":
			carasObtenidas[2] += 2;
			break;
		default:
			break;
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
			puntuacion = -1;
		}
		//Si la carta es un pirata se dobla la puntuación
		if (carta.equals("PIRATA")) {
			puntuacion *= 2;
		}
		
		
		return puntuacion;
	}
}
