import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal da solução com MONITORES e FAIRNESS.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int NUM_FILOSOFOS = 5;

        Mesa mesa = new Mesa(NUM_FILOSOFOS);

        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Filosofo f = new Filosofo(i, "F" + (i + 1), mesa);
            filosofos.add(f);
            threads.add(new Thread(f));
        }

        // Inicia as threads
        for (Thread t : threads) {
            t.start();
        }

        // Executa por 2 minutos
        Thread.sleep(120_000);

        // Interrompe as threads
        for (Thread t : threads) {
            t.interrupt();
        }

        // Aguarda finalização
        for (Thread t : threads) {
            t.join();
        }

        // Estatísticas finais
        System.out.println("\n=== Estatísticas de Execução ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filósofo " + f.getNome() +
                " comeu " + f.getRefeicoes() + " vezes."
            );
        }
    }
}
