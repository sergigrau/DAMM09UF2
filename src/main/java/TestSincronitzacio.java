
public class TestSincronitzacio {

	public static final int NOMBRE_COMPTES = 100;
	public static final double SALDO_INICIAL = 1000;

	public static void main(String[] args) {
		m01_Banc b = new m01_Banc(NOMBRE_COMPTES, SALDO_INICIAL);
		int i;
		for (i = 0; i < NOMBRE_COMPTES; i++) {
			m01_FilTransferencia r = new m01_FilTransferencia(b, i, SALDO_INICIAL);
			Thread t = new Thread(r);
			t.start();
		}

	}
}