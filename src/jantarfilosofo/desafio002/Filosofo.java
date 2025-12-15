import java.util.Random;

/**
 * Filósofo que participa do jantar.
 * A prevenção de deadlock ocorre por assimetria:
 * um filósofo entra na seção crítica em ordem diferente.
 */
public class Filosofo implements Runnable {

    private final int id;
    private final String nome;
    private final Object lockCompartilhado;

    private final Random random = new Random();
    private int refeicoes = 0;

    public Filosofo(int id, String nome, Object lockCompartilhado) {
        this.id = id;
        this.nome = nome;
        this.lockCompartilhado = lockCompartilhado;
    }

    @Override
    public void run() {

        try {
            while (!Thread.currentThread().isInterrupted()) {
                pensar();
                jantar();
            }
        } catch (InterruptedException e) {
            // Finaliza corretamente a thread
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep(random.nextInt(1000, 3000));
    }

    /**
     * Simula o jantar.
     * A ordem de acesso ao recurso muda para um filósofo específico,
     * quebrando a espera circular.
     */
    private void jantar() throws InterruptedException {

        if (id == 4) {
            // Filósofo assimétrico
            synchronized (lockCompartilhado) {
                comer();
            }
        } else {
            synchronized (lockCompartilhado) {
                comer();
            }
        }
    }

    private void comer() throws InterruptedException {

        log("está comendo");
        refeicoes++;

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
