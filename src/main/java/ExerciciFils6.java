

public class ExerciciFils6 {

	private static Runnable runnable1= new Fil("fil1");
	private static Runnable runnable2= new Fil("fil2");
	private static Runnable runnable3= new Fil("fil3");
	private static Runnable runnable4= new Fil("fil4");
	
	public static void main(String[] args) {

		
		Thread fil1= new Thread(runnable1);
		Thread fil2= new Thread(runnable2);
		Thread fil3= new Thread(runnable3);
		Thread fil4= new Thread(runnable4);
		
		fil1.start();
		fil2.start();
		fil3.start();
		fil4.start();
		try {
			fil1.join(); 
 		 	fil2.join(); 
			fil3.join();
			fil4.join(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	
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
