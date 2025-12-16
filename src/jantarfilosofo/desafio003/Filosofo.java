import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Filósofo que participa do jantar usando SEMÁFOROS.
 * 
 * Estratégia:
 * - Um semáforo global limita a no máximo 4 filósofos tentando pegar garfos.
 * - Cada garfo é representado por um semáforo binário.
 * - Essa abordagem previne deadlock de forma determinística.
 */
public class Filosofo implements Runnable {

    private final int id;
    private final String nome;

    // Semáforo global que limita o número de filósofos concorrendo
    private final Semaphore semaforoSala;

    // Garfos representados como semáforos individuais
    private final Semaphore garfoEsquerdo;
    private final Semaphore garfoDireito;

    private final Random random = new Random();
    private int refeicoes = 0;

    public Filosofo(int id, String nome,
                    Semaphore semaforoSala,
                    Semaphore garfoEsquerdo,
                    Semaphore garfoDireito) {
        this.id = id;
        this.nome = nome;
        this.semaforoSala = semaforoSala;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pensar();
                tentarComer();
            }
        } catch (InterruptedException e) {
            // Garante encerramento correto da thread
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep(random.nextInt(1000, 3000));
    }

    /**
     * Controla o acesso aos recursos usando semáforos.
     * A ordem é:
     * 1. Entrar na sala (semaforoSala)
     * 2. Pegar os dois garfos
     * 3. Comer
     * 4. Liberar os garfos e sair da sala
     */
    private void tentarComer() throws InterruptedException {

        log("tentando entrar na sala");
        semaforoSala.acquire();

        try {
            log("tentando pegar o garfo esquerdo");
            garfoEsquerdo.acquire();

            log("tentando pegar o garfo direito");
            garfoDireito.acquire();

            comer();

        } finally {
            // Liberação garantida dos recursos
            garfoDireito.release();
            garfoEsquerdo.release();
            semaforoSala.release();
            log("liberou os garfos e saiu da sala");
        }
    }

    private void comer() throws InterruptedException {
        refeicoes++;
        log("está comendo (refeição " + refeicoes + ")");
        Thread.sleep(random.nextInt(1000, 3000));
        log("terminou de comer");
    }

    public int getRefeicoes() {
        return refeicoes;
    }

    public String getNome() {
        return nome;
    }

    private void log(String msg) {
        System.out.println("Filósofo " + nome + ": " + msg);
    }
}
