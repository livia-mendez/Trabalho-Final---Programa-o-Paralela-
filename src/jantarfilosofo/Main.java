
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            
            Thread t = new Thread(
                new Filosofo(
                    "F" + String.valueOf(i + 1)));

            t.start();

            threads.add(t);
        }

        for (Thread t : threads)
            
            try {
                
                t.join();
            
            } catch(InterruptedException e) {

            }
    }
}