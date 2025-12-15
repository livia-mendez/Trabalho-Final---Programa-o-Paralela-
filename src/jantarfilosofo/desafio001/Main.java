package desafio001;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que inicializa os filósofos
 * e cria uma estrutura circular entre eles.
 */
public class Main {

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        List<Filosofo> filosofos = new ArrayList<>();

        // Criação dos filósofos
        for (int i = 1; i <= 5; i++) {
            filosofos.add(new Filosofo("F" + i));
        }

        // Liga os filósofos em forma de anel
        for (int i = 0; i < filosofos.size(); i++) {
            Filosofo atual = filosofos.get(i);
            Filosofo proximo = filosofos.get((i + 1) % filosofos.size());
            atual.setProximoFilosofo(proximo);
        }

        // Autoriza apenas o primeiro filósofo a começar
        filosofos.get(0).autorizar();

        // Cria e inicia as threads
        for (Filosofo f : filosofos) {
            Thread t = new Thread(f);
            threads.add(t);
            t.start();
        }

        // Aguarda todas as threads terminarem
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
