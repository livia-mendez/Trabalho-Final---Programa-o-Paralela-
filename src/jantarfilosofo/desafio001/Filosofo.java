package desafio001;
import java.util.Random;

/**
 * Representa um filósofo que janta em turnos.
 * 
 * Cada filósofo:
 * - espera até ser autorizado
 * - janta
 * - autoriza o próximo filósofo
 * - entra novamente em espera
 *
 * Este código demonstra o uso correto de wait/notify,
 * evitando perda de notificações.
 */
public class Filosofo implements Runnable {

    private final String nome;
    private final Random random = new Random();

    // Referência ao filósofo à direita (próximo)
    private Filosofo proximoFilosofo;

    // Indica se este filósofo pode jantar
    private boolean autorizado = false;

    public Filosofo(String nome) {
        this.nome = nome;
    }

    public void setProximoFilosofo(Filosofo proximo) {
        this.proximoFilosofo = proximo;
    }

    /**
     * Define se o filósofo está autorizado a prosseguir.
     * Deve ser chamado dentro de um bloco synchronized.
     */
    public synchronized void autorizar() {
        this.autorizado = true;
        this.notify();
    }

    @Override
    public void run() {

        for (int turno = 1; turno <= 10; turno++) {

            esperarAutorizacao();
            jantar(turno);
            passarVezAoProximo();
        }
    }

    /**
     * Faz o filósofo aguardar até que seja autorizado.
     * O uso de while evita problemas com wakeups espúrios.
     */
    private void esperarAutorizacao() {
        synchronized (this) {
            while (!autorizado) {
                try {
                    System.out.println("Filosofo " + nome + " está esperando autorização...");
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Consome a autorização
            autorizado = false;
        }
    }

    /**
     * Simula o ato de jantar.
     */
    private void jantar(int turno) {

        long tempoComendo = random.nextLong(1000, 3000);

        System.out.println(
            "Filosofo " + nome + " está jantando pela " +
            turno + "ª vez por " + (tempoComendo / 1000.0) + " segundos");

        long inicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - inicio < tempoComendo);

        System.out.println("Filosofo " + nome + " terminou de jantar.");
    }

    /**
     * Autoriza o próximo filósofo e o notifica.
     */
    private void passarVezAoProximo() {
        System.out.println("Filosofo " + nome + " autorizou o próximo.");
        synchronized (proximoFilosofo) {
            proximoFilosofo.autorizar();
        }
    }
}
