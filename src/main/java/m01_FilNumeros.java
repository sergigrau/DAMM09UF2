/**
 * @author sergi.grau@fje.edu
 * @version 1.0 1.1.2010
 * @version 2.0 13.10.2022
 */
class m01_FilNumeros implements Runnable{

    private String nom;

    public m01_FilNumeros(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Math.random());
        }
        System.out.println("final" + nom);

    }

}