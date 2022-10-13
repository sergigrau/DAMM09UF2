import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Pomer {

    private final String etiquetaArbre;
    private final int nombreDePomes;

    public static Pomer[] newCampArbres(int mida) {
        Pomer[] pomers = new Pomer[mida];
        for (int i = 0; i < pomers.length; i++) {
            pomers[i] = new Pomer("🌳#" + i);
        }
        return pomers;
    }


    public Pomer(String etiquetaArbre) {
        this.etiquetaArbre = etiquetaArbre;
        nombreDePomes = 3;
    }

    public int recollirPomes(String nomTreballador) {
        try {
            //System.out.printf("%s iniciat recollint pomes de %s \n", nomTreballador, treeLabel);
            TimeUnit.SECONDS.sleep(1);
            System.out.printf("%s recollit %d 🍏s de %s \n", nomTreballador, nombreDePomes, etiquetaArbre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nombreDePomes;
    }

    public int recollirPomes() {
        return recollirPomes(toLabel(Thread.currentThread().getName()));
    }

    private String toLabel(String nomThread) {
        HashMap<String, String> threadNomEtiqueta = new HashMap<>();
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-1", "Sergi");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-2", "Joan");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-3", "Carles");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-4", "Anna");

        return threadNomEtiqueta.getOrDefault(nomThread, nomThread);
    }
}



