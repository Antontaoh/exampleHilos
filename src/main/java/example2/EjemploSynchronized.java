package example2;

class Contador {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
        System.out.println(Thread.currentThread().getName() + " incrementó el contador a: " + contador);
    }

    public int getContador() {
        return contador;
    }
}

public class EjemploSynchronized {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Runnable tarea = () -> {
            for (int i = 0; i < 5; i++) {
                contador.incrementar();
            }
        };

        Thread hilo1 = new Thread(tarea, "Hilo-1");
        Thread hilo2 = new Thread(tarea, "Hilo-2");

        hilo1.start();
        hilo2.start();
    }
}