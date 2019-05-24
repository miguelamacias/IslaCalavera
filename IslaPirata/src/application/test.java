package application;

public class test {

	public static void main(String[] args) {
		Dado dado1 = new Dado();
		Dado dado2 = new Dado();
		Dado dado3 = new Dado();
		Dado dado4 = new Dado();
		Dado dado5 = new Dado();
		Dado dado6 = new Dado();
		
		dado1.tirar();
		dado2.tirar();
		dado3.tirar();
		dado4.tirar();
		dado5.tirar();
		dado6.tirar();
		
		System.out.printf("%s  %s  %s  %s  %s  %s", dado1.toString(), dado2.toString(), dado3.toString(), dado4.toString(), dado5.toString(), dado6.toString());

	}

}
