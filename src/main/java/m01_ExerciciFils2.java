/**
 * Classe que crea dos fils i els executa
 *  
 * @author sergi grau
 * @version 1.0, 28.01.2010
 * 
 */
public class m01_ExerciciFils2 {
	private static Fil fil1= new Fil("fil1");
	private static Fil fil2= new Fil("fil2");
	public static void main(String[] args) {

		
		fil1.start(); //no run()
		fil2.start(); //no run()
	}

	public static class Fil extends Thread {


		public Fil(String nom){
			super(nom);
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println(i + "-" + getName());
			}
			System.out.println("final" + getName());
		}

	}
}

