import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Classe que fa us de la cancelació de Tasques i el llençament d'excepcions
 * @author sergi.grau@fje.edu
 * @version 1.0 20.10.2022
 */
public class m05_Cancelacio_Excepcions {
	private static final int ARBRES=12;
	public static void main(String[] args) {
		m02_Pomer[] pomers = m02_Pomer.crearArbres(ARBRES);
		ForkJoinPool pool = ForkJoinPool.commonPool();

		TascaRecollirFruites tasca = new TascaRecollirFruites(pomers, 0, pomers.length - 1);
		int resultat = pool.invoke(tasca);

		System.out.println();
		System.out.println("Total pomes recollides: " + resultat);
	}
	public static class CapturaProblemaException extends Exception {
	}

	public static class TascaRecollirFruites extends RecursiveTask<Integer> {
		private final m02_Pomer[] pomers;
		private final int iniciInc;
		private final int finalExc;
		private final int llindarTasques = 4;

		public TascaRecollirFruites(m02_Pomer[] pomers, int iniciInc, int finalExc) {
			this.pomers = pomers;
			this.iniciInc = iniciInc;
			this.finalExc = finalExc;
		}
		@Override
		protected Integer compute() {

			// llença una excepcio per a qualsevol tasca que del costat dret de l'array dels pomers
			if (iniciInc >= ARBRES / 2) {
				//int i =10/0;
				//throw new CapturaProblemaException(); //-> no permet l'execució de fils
				//completeExceptionally(new CapturaProblemaException()); // per a treballar amb fils
			}
			if (finalExc - iniciInc < llindarTasques) {
				return doCompute();
			}
			int puntMig = iniciInc + (finalExc - iniciInc) / 2;

			TascaRecollirFruites recollirEsquerra = new TascaRecollirFruites(pomers, iniciInc, puntMig);
			TascaRecollirFruites recollirDreta = new TascaRecollirFruites(pomers, puntMig + 1, finalExc);


			recollirDreta.fork(); // es realitza ssíncronament

			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//recollirDreta.cancel(true);

			return recollirEsquerra.compute()// computed synchronously: immediately and in the current thread
					+ recollirDreta.join();
		}

		protected Integer doCompute() {
			return IntStream.rangeClosed(iniciInc, finalExc)//
					.map(i -> pomers[i].recollirPomes())//
					.sum();

		}
	}

}
