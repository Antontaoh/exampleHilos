package example3;

class Semaforo {
    private boolean disponible = false;

    public synchronized void esperar() throws InterruptedException {
        while (!disponible) {
            wait(); // Espera hasta que otro hilo llame a notify()
        }
        System.out.println(Thread.currentThread().getName() + " continuó después de esperar.");
    }

    public synchronized void liberar() {
        disponible = true;
        notify(); // Despierta a un hilo en espera
        System.out.println(Thread.currentThread().getName() + " liberó el semáforo.");
    }
}

public class EjemploWaitNotify {
    public static void main(String[] args) {
        Semaforo semaforo = new Semaforo();

        Thread hilo1 = new Thread(() -> {
            try {
                semaforo.esperar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Hilo-1");

        Thread hilo2 = new Thread(() -> semaforo.liberar(), "Hilo-2");

        hilo1.start();
        try {
            Thread.sleep(1000); // Simula un retraso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hilo2.start();
    }
}
