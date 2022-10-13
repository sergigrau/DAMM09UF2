/**
 * Classe que crea dos fils i els executa
 *  
 * @author sergi grau
 * @version 1.0, 28.01.2010
 * 
 */
public class ExerciciFils2 {
	private static Fil fil1= new Fil("fil1");
	private static Fil fil2= new Fil("fil2");
	public static void main(String[] args) {

		
		fil1.start(); //no run()
		fil2.start(); //no run()
	}
	/**
	 * Classe interna est�tica que hereta de Thread 
	 * i que representa un fil que escriu 1000 digits i
	 * acaba. Aquesta manera de crear fils no �s la recomanable
	 * 
	 * @author sergi grau
	 * @version 1.0, 28.01.2010
	 */
	public static class Fil extends Thread {

		/**
		 * Constructor amb 1 par�metre
		 * @param nom nom del fil
		 */
		public Fil(String nom){
			super(nom);
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println(i + "-" + getName());
			}
			System.out.println("final" + getName());
		}

	}
}

