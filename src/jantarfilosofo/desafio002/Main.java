import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal responsável por iniciar o jantar
 * e coletar estatísticas de execução.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Object lockCompartilhado = new Object();

        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo(i, "F" + (i + 1), lockCompartilhado);
            filosofos.add(f);
            threads.add(new Thread(f));
        }

        for (Thread t : threads) {
            t.start();
        }

        // Executa por 2 minutos
        Thread.sleep(120_000);

        // Interrompe todas as threads
        for (Thread t : threads) {
            t.interrupt();
        }

        // Aguarda término
        for (Thread t : threads) {
            t.join();
        }

        // Estatísticas
        System.out.println("\n=== Estatísticas de Execução ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filósofo " + f.getNome() +
                " comeu " + f.getRefeicoes() + " vezes.");
        }
    }
}
