import java.util.Random;

/**
 * Filósofo que interage com a Mesa (monitor).
 * Não acessa garfos diretamente.
 */
public class Filosofo implements Runnable {

    private final int id;
    private final String nome;
    private final Mesa mesa;

    private final Random random = new Random();
    private int refeicoes = 0;

    public Filosofo(int id, String nome, Mesa mesa) {
        this.id = id;
        this.nome = nome;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep(random.nextInt(1000, 3000));
    }

    private void comer() throws InterruptedException {
        mesa.pegarGarfos(id);

        refeicoes++;
        log("está comendo (refeição " + refeicoes + ")");
        Thread.sleep(random.nextInt(1000, 3000));

        mesa.devolverGarfos(id);
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
