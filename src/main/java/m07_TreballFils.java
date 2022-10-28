import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Classe que fa una demostració de l'ús de Threads
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 28.10.2022
 */
public class m07_TreballFils {

	private static final Runnable tascaInici =
			() -> System.out.printf("Estàs executant el fil '%s'\n", Thread.currentThread().getName());

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// el main Thread es el programa principal
		System.out.println(" thread actual: " + Thread.currentThread().getName());

		demoThread();
		demoThreadsCreatsPerThreadPool();
		demoExecutorServicesDiferents();
		demoExecutorServiceProgramat();
		demoThreadFactory();
	}

	public static void demoThread() {
		System.out.println("Thread de denostració");
		System.out.println("⚠️només per a demostració, no creis els Threads tu mateix utiltiza ExecutorService!!");

		// enviem 10 tasques iguals
		for (int i = 0; i < 10; i++) {
			new Thread(tascaInici).start();
		}
		// Les tasques s'execten per diferents fils
		// 10 > 4 (4 is nombre de cores del meu ordinador) //htop
		// threads NO es reutilitzen
		System.out.println();
	}

	public static void demoThreadsCreatsPerThreadPool() throws InterruptedException, ExecutionException {
		System.out.println("Demo ThreadPool");
		System.out.println("😄Utilitza ExecutorService per a gestionar els threads");

		ExecutorService pool = Executors.newCachedThreadPool();
		// envia 10 tasques iguals i observa que són executades per diferents threads
		for (int i = 0; i < 10; i++) {
			pool.submit(tascaInici);
		}

		// a diferència de thread.start(), threadPool.submit() retorna una Future
		Future<Integer> nombreAleatori = pool.submit(() -> new Random().nextInt());
		System.out.println("nombre aleatori: " + nombreAleatori.get());

		pool.shutdown();
		System.out.println();
	}

	public static void demoThreadFactory() {
		System.out.println("Demo ThreadFactory");
		System.out.println("😄Utilitza un ExecutorService per a gestionar threads");

		ThreadFactory threadFactory = new ThreadFactory() {
			private final AtomicInteger filNombre = new AtomicInteger(1);

			public Thread newThread(Runnable r) {
				Thread fil = new Thread(r);
				fil.setName("Hola Thread " + filNombre.getAndIncrement());
				fil.setPriority(Thread.MAX_PRIORITY);
				return fil;
			}
		};

		ExecutorService pool = Executors.newCachedThreadPool(threadFactory);

		// envia 10 tasques iguals i observa que són executades per diferents threads
		for (int i = 0; i < 10; i++) {
			pool.submit(tascaInici);
		}

		pool.shutdown();
		System.out.println();
	}

	public static void demoExecutorServicesDiferents() {
		System.out.println("Demo diferents thread pools");

		ExecutorService pool = Executors.newCachedThreadPool();
		//per anar provant
//		ExecutorService pool = Executors.newFixedThreadPool(5);
//		ExecutorService pool = Executors.newFixedThreadPool(1);
//		ExecutorService pool = Executors.newSingleThreadExecutor();

		// envia 10 tasques iguals i observa que són executades per diferents threads
		for (int i = 0; i < 10; i++) {
			// a diferència de thread.start(), threadPool.submit() retorna una Future
			Future<?> result = pool.submit(tascaInici);
		}

		// important fer un shutdown del pool quan acabem
		pool.shutdown();
		System.out.println();
	}

	public static void demoExecutorServiceProgramat() {
		System.out.println("Demo tasques programades tasks");

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

		ScheduledFuture<?> atendreClasse = scheduler.scheduleAtFixedRate(
				() -> System.out.println("Temps per a atendre a classe"), //
				0, 1, TimeUnit.SECONDS);

		ScheduledFuture<?> ferActivitats = scheduler.scheduleAtFixedRate(
				() -> System.out.println("temps per a fer les activitats"), //
				0, 12, TimeUnit.SECONDS);

		// per a cancelar les tasques després de cert quantitat de temps
		Runnable cancelador = () -> {
			ferActivitats.cancel(false);
			atendreClasse.cancel(false);
		};
		scheduler.schedule(cancelador, 15, TimeUnit.SECONDS);
	}
}