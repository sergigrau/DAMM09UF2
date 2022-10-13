import static java.util.Arrays.asList;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExemple {
    public static void main(String[] args) {
        Pomer[] pomers = Pomer.newCampArbres(6);
        /*La interfície Callable és similar a Runnable, ja que totes dues estan dissenyades per a classes les instàncies de les quals s'executen potencialment per un altre fil.
        Un Runnable, però, no retorna un resultat i no pot llançar una excepció marcada. La classe Executors conté mètodes d'utilitat per convertir d'altres formes habituals a classes Callable*/
        Callable<Void> recollector1 = crearRecolectorPomes(pomers, 0, 2, "Alex");
        Callable<Void> recollector2 = crearRecolectorPomes(pomers, 2, 4, "Bob");
        Callable<Void> recollector3 = crearRecolectorPomes(pomers, 4, 6, "Carol");

        ForkJoinPool.commonPool().invokeAll(asList(recollector1, recollector2, recollector3));

        System.out.println();
        System.out.println("Totes les fruites recollides!");
    }

    public static Callable<Void> crearRecolectorPomes(Pomer[] appleTrees, int desDeIndex, int finsIndexExc,
                                                      String treballador) {
        return () -> {
            for (int i = desDeIndex; i < finsIndexExc; i++) {
                appleTrees[i].recollirPomes(treballador);
            }
            return null;
        };
    }
}