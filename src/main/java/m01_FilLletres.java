public class m01_FilLletres implements Runnable{

    private String nom;
    public m01_FilLletres(String nom) {
        this.nom = nom;

    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println((char) (65 + Math.random() * 26));
        }
        System.out.println("final" + nom);

    }
}
