import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe que representa un banc amb un determinat nombre de comptes
 * 
 * @author sergi grau
 * @version 1.0, 01.02.2010
 * 
 */
public class Banc {

	private final double[] comptes;
	private Lock bancLock = new ReentrantLock(); //opci� 1 de sincronitzaci�

	public Banc(int nombreComptes, double saldoInicial) {
		comptes = new double[nombreComptes];
		for (int i = 0; i < comptes.length; i++) {
			comptes[i] = saldoInicial;
		}
	}


	//opcio 2 sincronitzaci
	//public synchronized void transfer(int origen, int destinacio, double quantitat) throws InterruptedException{
		
	public void transferir(int origen, int destinacio, double quantitat){
		//bancLock.lock();//opcio 1 de sincronitzaci�
		if (comptes[origen] < quantitat)
		//	comentar en cas d'utilitzar opcio 2
			return;
		//opcio 2 de sincronitzacio
		//wait(); // espera a que acabi aquesta secci� de codi abans de que cap altre pugui accedir
		System.out.print(Thread.currentThread());
		
		comptes[origen] -= quantitat;
		System.out.printf(" %10.2f des de %d a %d", quantitat, origen,
				destinacio);
		comptes[destinacio] += quantitat;
		
		System.out.printf(" Saldo Total: %10.2f%n", getSaldoTotal());
		//opcio 2 de sincronitzacio
		//notifyAll(); // notifica a tots els fils que estan esperant
		
		//bancLock.unlock(); //opcio 1 de sincronitzacio
	}

	/**
	 * Retorna la suma de tots els comptes corrents
	 * 
	 * @return retorna el saldo total de tots els comptes
	 */
	//opcio 2 de sincronitzacio
	//public synchronized double getSaldoTotal() {
	
	public double getSaldoTotal() {
		double sum = 0;
		for (double a : comptes)
			sum += a;
		return sum;
	}

	/**
	 * retorna el nombre total de comptes del banc
	 * 
	 * @return nombre total de comptes
	 */
	public int size() {
		return comptes.length;
	}
}
