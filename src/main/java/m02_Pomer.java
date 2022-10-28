import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Classe que representa un pomer
 * en un exemple on es crean tres camperols que recullen podes de 6 pomers
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 13.10.2022
 */
public class m02_Pomer {

    private final String etiquetaArbre;
    private final int nombreDePomes;

    public static m02_Pomer[] crearArbres(int mida) {
        m02_Pomer[] pomers = new m02_Pomer[mida];
        for (int i = 0; i < pomers.length; i++) {
            pomers[i] = new m02_Pomer("🌳#" + i);
        }
        return pomers;
    }


    public m02_Pomer(String etiquetaArbre) {
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

    /**
     * Utilitza com a nom del worker el om del fil actual
     * @return
     */
    public int recollirPomes() {
        return recollirPomes(toLabel(Thread.currentThread().getName()));
    }
    private String toLabel(String nomThread) {
        HashMap<String, String> threadNomEtiqueta = new HashMap<>();
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-1", "Fil - Sergi");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-2", "Fil -Joan");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-3", "Fil -Carles");
        threadNomEtiqueta.put("ForkJoinPool.commonPool-worker-4", "Fil -Anna");

        return threadNomEtiqueta.getOrDefault(nomThread, nomThread);
    }
}



