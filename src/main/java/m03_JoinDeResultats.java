import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Classe que permet mostrar com funciona el mecanisme de fork-join per defecte,
 * en aquets cada tasca és divideix en una altra, i s'espera a tenir-ho tot computat, per a fer un join()
 * Mostra com recollir els resultats de les tasques
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 13.10.2022
 */
public class m03_JoinDeResultats {
    public static void main(String[] args) {
        m02_Pomer[] pomers = m02_Pomer.crearArbres(12);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        TascarRecollirFruites tasca = new TascarRecollirFruites(pomers, 0, pomers.length - 1);
        int resultat = pool.invoke(tasca);

        System.out.println();
        System.out.println("Total pomes recollides: " + resultat);
    }

    /**
     * Classe interna que implementa una tasca Recursiva i que representa
     * el fet de recollir les pomes, hereta de ForkJoinTask
     */
    public static class TascarRecollirFruites extends RecursiveTask<Integer> {

        private final m02_Pomer[] pomers;
        private final int iniciInc;
        private final int finalExc;

        private final int llindarTasques = 4;

        public TascarRecollirFruites(m02_Pomer[] pomers, int iniciInc, int finalExc) {
            this.pomers = pomers;
            this.iniciInc = iniciInc;
            this.finalExc = finalExc;
        }
        //compute es un mètode on s'implementa la computació principal a realitzar per la Tasca
        @Override
        protected Integer compute() {
            if (finalExc - iniciInc < llindarTasques) {
                return tascaAFer();
            }
            int puntMig = iniciInc + (finalExc - iniciInc) / 2;

            TascarRecollirFruites recollirEsquerra = new TascarRecollirFruites(pomers, iniciInc, puntMig);
            TascarRecollirFruites recollirDreta = new TascarRecollirFruites(pomers, puntMig + 1, finalExc);

            recollirDreta.fork(); // realitzat de manera asíncrona

            return recollirEsquerra.compute()// realitzat de manera asíncrona de manera immediata en el mateix fil actual
                    + recollirDreta.join();
        }

        protected Integer tascaAFer() {
            return IntStream.rangeClosed(iniciInc, finalExc)
                    .map(i -> pomers[i].recollirPomes())
                    .sum();
        }
    }

}
