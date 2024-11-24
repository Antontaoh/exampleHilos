package example4;

import java.util.LinkedList;

class Cola {
    private final LinkedList<Integer> datos = new LinkedList<>();
    private final int capacidad = 5;

    public synchronized void producir(int valor) throws InterruptedException {
        while (datos.size() == capacidad) {
            wait(); // Espera si la cola está llena
        }
        datos.add(valor);
        System.out.println(Thread.currentThread().getName() + " produjo: " + valor);
        notifyAll(); // Notifica a todos los consumidores
    }

    public synchronized int consumir() throws InterruptedException {
        while (datos.isEmpty()) {
            wait(); // Espera si la cola está vacía
        }
        int valor = datos.removeFirst();
        System.out.println(Thread.currentThread().getName() + " consumió: " + valor);
        notifyAll(); // Notifica a todos los productores
        return valor;
    }
}

public class EjemploNotifyAll {
    public static void main(String[] args) {
        Cola cola = new Cola();

        Runnable productor = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    cola.producir(i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumidor = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    cola.consumir();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread hiloProductor = new Thread(productor, "Productor");
        Thread hiloConsumidor1 = new Thread(consumidor, "Consumidor-1");
        Thread hiloConsumidor2 = new Thread(consumidor, "Consumidor-2");

        hiloProductor.start();
        hiloConsumidor1.start();
        hiloConsumidor2.start();
    }
}