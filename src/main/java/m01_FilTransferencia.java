public class m01_FilTransferencia implements Runnable {

	private m01_Banc banc;
	private int compteOrigen;
	private double quantitatMaxima;
	private int DEMORA = 10;


	public m01_FilTransferencia(m01_Banc b, int origen, double max) {
		banc = b;
		compteOrigen = origen;
		quantitatMaxima = max;
	}

	public void run() {
		try {
			while (true) {
				int compteDestinacio = (int) (banc.size() * Math.random());
				double quantitat = quantitatMaxima * Math.random();
				banc.transferir(compteOrigen, compteDestinacio, quantitat);
				Thread.sleep((int) (DEMORA * Math.random()));

			}
		} catch (InterruptedException e) {

		}
	}

}