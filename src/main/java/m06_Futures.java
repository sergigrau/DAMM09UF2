import java.util.concurrent.*;

/**
 * Classe que fa una demostració de l'ús de Futures
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 20.10.2022
 */
public class m06_Futures {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		demoFutureAmbCallable();
		demoCallableVsRunnable();
	}
	public static void demoFutureAmbCallable() throws InterruptedException, ExecutionException {
		System.out.println();
		System.out.println("Demo Future amb Callable");
		ExecutorService pool = Executors.newCachedThreadPool();

		Future<Pizza> comandaPizza = pool.submit(() -> {
			System.out.println("   Restaurant> tallar tomàquet");
			System.out.println("   Restaurant> tallar ceba");
			System.out.println("   Restaurant> repartir tomaquet, i posar els ingredents");
			System.out.println("   Restaurant> ficar al forn la 🍕");
			TimeUnit.MILLISECONDS.sleep(300);
			return new Pizza();
		});

		System.out.println("Jo: estudiar Java i Fils");
		TimeUnit.MILLISECONDS.sleep(200);
		System.out.println("Jo: estudiar Android");

		//comandaPizza.cancel(true);
		if (comandaPizza.isCancelled()) {
			System.out.println("Jo: cancel·lem la 🍕 is cancelled");
			System.out.println("comandaPizza.isDone(): " + comandaPizza.isDone());
		} else if (!comandaPizza.isDone()) {
			System.out.println("Jo: Mirar una sèrie");
		}
		Pizza pizza = comandaPizza.get();

		System.out.println("Jo: menjar la 🍕 " + pizza);

		pool.shutdown();
		System.out.println();
		System.out.println();
	}

	/**
	 * Runnable no pot retornar res
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void demoCallableVsRunnable() throws InterruptedException, ExecutionException {
		System.out.println();
		System.out.println("Demo: Callable vs Runnable");
		ExecutorService pool = Executors.newCachedThreadPool();

		Runnable preparacioPizza = () -> {
			System.out.println("   Restaurant> tallar tomàquet");
			System.out.println("   Restaurant> tallar ceba");
			System.out.println("   Restaurant> repartir tomaquet, i posar els ingredents");
			System.out.println("   Restaurant> ficar al forn la 🍕");
			// Compare to Callable: need to handle exception here
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Comparem amb Callable:  Future retornava la pizza
		};
		
		// comparem amb submit(Callable): Future<?> vs Future<T>
		Future<?> comandaPizza = pool.submit(preparacioPizza);
		
		// pool.execute(preparacioPizza);

		System.out.println("Jo: estudiar Java i Fils");
		TimeUnit.MILLISECONDS.sleep(200);
		System.out.println("Jo: estudiar Android");

		Object pizza = comandaPizza.get(); // null
		System.out.println("Jo: menjar la 🍕 " + pizza);

		pool.shutdown();
	}

	public static class Pizza {

		@Override
		public String toString() {
			return "margherita clàssica";
		}

	}

}