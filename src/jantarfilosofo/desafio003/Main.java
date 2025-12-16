import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Classe principal responsável por iniciar o jantar dos filósofos
 * usando semáforos e coletar estatísticas de execução.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int NUM_FILOSOFOS = 5;

        // Semáforo global: no máximo 4 filósofos tentando pegar garfos
        Semaphore semaforoSala = new Semaphore(4);

        // Garfos representados como semáforos binários
        Semaphore[] garfos = new Semaphore[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Semaphore(1);
        }

        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Criação dos filósofos e associação dos garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Semaphore garfoEsquerdo = garfos[i];
            Semaphore garfoDireito = garfos[(i + 1) % NUM_FILOSOFOS];

            Filosofo f = new Filosofo(
                    i,
                    "F" + (i + 1),
                    semaforoSala,
                    garfoEsquerdo,
                    garfoDireito
            );

            filosofos.add(f);
            threads.add(new Thread(f));
        }

        // Inicia todas as threads
        for (Thread t : threads) {
            t.start();
        }

        // Executa o programa por 2 minutos
        Thread.sleep(120_000);

        // Interrompe todas as threads
        for (Thread t : threads) {
            t.interrupt();
        }

        // Aguarda o término das threads
        for (Thread t : threads) {
            t.join();
        }

        // Exibe estatísticas finais
        System.out.println("\n=== Estatísticas de Execução ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filósofo " + f.getNome() +
                " comeu " + f.getRefeicoes() + " vezes."
            );
        }
    }
}
