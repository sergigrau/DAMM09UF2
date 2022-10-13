
/**
 * Classe que implementa la interf�cie Runnable Representa un fil que escriu
 * 1000 n�meros aleat�ries i acaba.
 * 
 * @author sergi grau
 * @version 1.0, 28.01.2010
 */
class FilNumeros  implements Runnable{

	private String nom;

	/**
	 * Constructor amb 1 par�metre
	 * 
	 * @param nom
	 *            nom del fil
	 */
	public FilNumeros(String nom) {
		this.nom = nom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(Math.random());
		}
		System.out.println("final" + nom);

	}

}