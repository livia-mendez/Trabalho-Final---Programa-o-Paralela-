import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe Mesa atua como um MONITOR.
 * Ela gerencia o acesso aos garfos e garante FAIRNESS
 * por meio de uma fila FIFO de filósofos.
 */
public class Mesa {

    private final int numFilosofos;
    private final boolean[] garfos;

    // Fila para garantir fairness (ordem de chegada)
    private final Queue<Integer> fila = new LinkedList<>();

    public Mesa(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.garfos = new boolean[numFilosofos];
    }

    /**
     * Método sincronizado: filósofo pede para comer.
     * Só come se:
     * - estiver no início da fila
     * - ambos os garfos estiverem livres
     */
    public synchronized void pegarGarfos(int id) throws InterruptedException {

        fila.add(id);
        log(id, "entrou na fila para comer");

        while (!podeComer(id)) {
            wait();
        }

        // Remove da fila e ocupa os garfos
        fila.poll();
        garfos[id] = true;
        garfos[(id + 1) % numFilosofos] = true;

        log(id, "pegou os dois garfos e começou a comer");
    }

    /**
     * Método sincronizado: filósofo termina de comer.
     */
    public synchronized void devolverGarfos(int id) {
        garfos[id] = false;
        garfos[(id + 1) % numFilosofos] = false;

        log(id, "devolveu os garfos");

        // Acorda todos para reavaliar a fila
        notifyAll();
    }

    /**
     * Verifica se o filósofo pode comer:
     * - é o primeiro da fila
     * - garfos livres
     */
    private boolean podeComer(int id) {
        return fila.peek() != null
                && fila.peek() == id
                && !garfos[id]
                && !garfos[(id + 1) % numFilosofos];
    }

    private void log(int id, String msg) {
        System.out.println("[Mesa] Filósofo F" + (id + 1) + ": " + msg);
    }
}
