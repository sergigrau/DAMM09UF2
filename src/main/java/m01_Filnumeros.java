public class m01_Filnumeros implements Runnable{

    private final String nom;
    public m01_Filnumeros(String nom) {
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