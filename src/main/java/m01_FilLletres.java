/**
 * @author sergi.grau@fje.edu
 * @version 1.0 1.1.2010
 * @version 2.0 13.10.2022
 */
class m01_FilLletres implements Runnable {

    private String nom;

    /**
     * Constructor amb 1 par�metre
     *
     * @param nom
     *            nom del fil
     */
    public m01_FilLletres(String nom) {
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
            System.out.println((char) (65 + Math.random() * 26));
        }
        System.out.println("final" + nom);

    }
}
