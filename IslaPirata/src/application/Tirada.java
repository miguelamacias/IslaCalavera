package application;

public class Tirada {
	
	public int[] obtenerTirada(Dado[] dados) {
		//Array que almacena las veces que ha salido cada cara
		int[] resultado = new int[7];
		
		//Tira los dados
		for (int i = 1; i < dados.length; i++) {
			resultado[dados[i].tirar()] ++;
			
		}
		
		return resultado;
	}
	
	public int calcularPuntuacion(int[] puntuaciones) {
		//Reinicia la puntuación
		int puntuacion = 0;
		
		//100 puntos por cada oro y diamante obtenidos
		puntuacion += puntuaciones[2] * 100;
		puntuacion += puntuaciones[5] * 100;
		
		//Suma las puntuaciones por grupos
		for (int i = 1; i < puntuaciones.length; i++) {
			switch (puntuaciones[i]) {
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
		
		return puntuacion;
	}
	
	
}
