import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** Classe que demostra el funcionament de RecursiveAction que NO retorna cap resultat en l'execució
 * només necessitem recollir les pomes, no el nombre recollit
 * Es important veure la diferencia entre execute i invoke
 * @author sergi.grau@fje.edu
 * @version 1.0 20.10.2022
 */
public class m04_RecursiveAction {

	public static void main(String[] args) throws InterruptedException {
		m02_Pomer[] pomers = m02_Pomer.crearArbres(12);
		ForkJoinPool pool = ForkJoinPool.commonPool();
		long tempsInicial = System.currentTimeMillis();
		AccioRecollirFruites tasca = new AccioRecollirFruites(pomers, 0, pomers.length - 1);
		//espera a que es completi la tasca per a continuar el codi
		pool.invoke(tasca);
		//execute -> execució asíncrona, hem d'esperar amb join el finals del fils
		// pool.execute(tasca);
		// pool.execute(tasca); tasca.join();
		//pool.execute(tasca); pool.awaitTermination(10, TimeUnit.SECONDS);

		System.out.println();
		long tempsFinal = System.currentTimeMillis();
		System.out.println("Fet!"+(tempsFinal -tempsInicial));
	}

	public static class AccioRecollirFruites extends RecursiveAction {
		private final m02_Pomer[] pomers;
		private final int iniciInc;
		private final int finalExc;
		private final int llindarTasques = 4;

		public AccioRecollirFruites(m02_Pomer[] pomers, int iniciInc, int finalExc) {
			this.pomers = pomers;
			this.iniciInc = iniciInc;
			this.finalExc = finalExc;
		}

		@Override
		protected void compute() {
			if (finalExc - iniciInc < llindarTasques) {
				accioAfer();
				return;
			}
			int puntMig = iniciInc + (finalExc - iniciInc) / 2;

			AccioRecollirFruites recollirEsquerra = new AccioRecollirFruites(pomers, iniciInc, puntMig);
			AccioRecollirFruites recollirDreta = new AccioRecollirFruites(pomers, puntMig + 1, finalExc);

			recollirDreta.fork(); // realitzat de manera asíncrona
			recollirEsquerra.compute();// realitzat de manera asíncrona immediatament en el fil actual
			recollirDreta.join();

		}

		protected void accioAfer() {
			System.out.printf("Fil treballant\n");
			IntStream.rangeClosed(iniciInc, finalExc)//
					.forEach(i -> pomers[i].recollirPomes());

		}
	}
}
