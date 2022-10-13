/**
 * Classe que crea dos fils i els executa. El primer El primer fil escriu per la
 * sortida est�ndard 1000 lletres aleat�ries i el segon 1000 nombres reals
 * aleatoris. El fils de nombres ha d'acabar abans.
 * 
 * @author sergi grau
 * @version 1.0, 28.01.2010
 * 
 */

public class Fils {

	public static void main(String[] args) {

		Runnable runnable1 = new FilLletres("fil1");
		Runnable runnable2 = new FilNumeros("fil2");

		Thread fil1 = new Thread(runnable1);
		Thread fil2 = new Thread(runnable2);
		fil1.setPriority(5);
		fil2.setPriority(10);
		
		fil1.start();
		fil2.start();
	}
}


