package application;

public class test {

	public static void main(String[] args) {
		Dado dado1 = new Dado();
		Dado dado2 = new Dado();
		Dado dado3 = new Dado();
		Dado dado4 = new Dado();
		Dado dado5 = new Dado();
		Dado dado6 = new Dado();
		Dado dado7 = new Dado();
		Dado dado8 = new Dado();
		
		Dado[] dados = {dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8};
		
//		dado1.tirar();
//		dado2.tirar();
//		dado3.tirar();
//		dado4.tirar();
//		dado5.tirar();
//		dado6.tirar();
//		dado7.tirar();
//		dado8.tirar();
		
		
		//System.out.printf("%s  %s  %s  %s  %s  %s %s %s", dado1.toString(), dado2.toString(), dado3.toString(), dado4.toString(), dado5.toString(), dado6.toString(), dado7.toString(), dado8.toString());
		Tirada tirada = new Tirada();
		
		//System.out.println(tirada.calcularPuntuacion(tirada.obtenerTirada(dados)));;

	}

}
