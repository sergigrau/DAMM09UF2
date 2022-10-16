public class m01_ExerciciFils3 {
	
	private static Runnable runnable1= new Fil("fil1");
	private static Runnable runnable2= new Fil("fil2");
	
	public static void main(String[] args) {

		
		Thread fil1= new Thread(runnable1);
		Thread fil2= new Thread(runnable2);
		fil1.start(); //no run()
		fil2.start(); //no run()
	}

	public static class Fil implements Runnable {

		private String nom;

		public Fil(String nom){
			this.nom= nom;
		}
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println(i + "-" + nom);
			}
			System.out.println("final" + nom);

		}

	}
}
