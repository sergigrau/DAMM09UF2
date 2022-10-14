import static java.util.Arrays.asList;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

/**
 * Classe que permet mostrar com funciona el mecanisme de fork-join per defecte,
 * en un exemple on es crean tres camperols que recullen podes de 6 pomers
 * Mostra com executar tasques en concurrència amb la classe ForkJoinPool
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 13.10.2022
 */
public class m02_ForkJoinExemple {
    public static void main(String[] args) {
        m02_Pomer[] pomers = m02_Pomer.crearArbres(6);
        /*La interfície Callable és similar a Runnable, ja que totes dues estan dissenyades per a classes les instàncies de les quals s'executen potencialment per un altre fil.
        Un Runnable, però, no retorna un resultat i no pot llançar una excepció marcada. La classe Executors conté mètodes d'utilitat per convertir d'altres formes habituals a classes Callable*/
        Callable<Void> recollector1 = crearRecolectorPomes(pomers, 0, 2, "Alex");
        Callable<Void> recollector2 = crearRecolectorPomes(pomers, 2, 4, "Bob");
        Callable<Void> recollector3 = crearRecolectorPomes(pomers, 4, 6, "Carol");

        ForkJoinPool.commonPool().invokeAll(asList(recollector1, recollector2, recollector3));

        System.out.println();
        System.out.println("Totes les fruites recollides!");
    }

    public static Callable<Void> crearRecolectorPomes(m02_Pomer[] pomers, int desDeIndex, int finsIndexExc,
                                                      String treballador) {
        return () -> {
            for (int i = desDeIndex; i < finsIndexExc; i++) {
                pomers[i].recollirPomes(treballador);
            }
            return null;
        };
    }
}