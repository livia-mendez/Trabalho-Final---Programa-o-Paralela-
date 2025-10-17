
import java.util.Random;

public class Filosofo implements Runnable {

    private final Random random = new Random();
    private final String nome;

    public Filosofo(String nome) {
        this.nome = nome;
    }

    public void jantar() {

        final long tempoComendo = this.random.nextLong(1000, 5000);
        final long tempoInicial = System.currentTimeMillis();

        System.out.println(
            "Filosofo " + this.nome + " está jantando por " + 
            String.valueOf(tempoComendo / 1000.) + " segundos");

        while (System.currentTimeMillis() - tempoInicial < tempoComendo);

        System.out.println(
            "Filosofo " + this.nome + 
            " terminou de jantar!");
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            this.jantar();
            this.esperar();
        }
    }

    public void esperar() {
        final long tempoEspera = this.random.nextLong(5000, 10000);

        System.out.println(
                " *** Filosofo " + this.nome + 
                " está esperando por " + String.valueOf(tempoEspera / 1000.));

        try {

            Thread.sleep(tempoEspera);

        } catch(InterruptedException e) {

        }
    }
}