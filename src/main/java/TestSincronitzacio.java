
public class TestSincronitzacio {

	public static final int NOMBRE_COMPTES = 100;
	public static final double SALDO_INICIAL = 1000;

	public static void main(String[] args) {
		Banc b = new Banc(NOMBRE_COMPTES, SALDO_INICIAL);
		int i;
		for (i = 0; i < NOMBRE_COMPTES; i++) {
			FilTransferencia r = new FilTransferencia(b, i, SALDO_INICIAL);
			Thread t = new Thread(r);
			t.start();
		}

	}
}