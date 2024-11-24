import java.util.LinkedList;

class Buffer {
    private final int capacidad;
    private final LinkedList<Integer> buffer = new LinkedList<>();

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
    }

    // Método para añadir un producto al buffer
    public synchronized void producir(int producto) throws InterruptedException {
        while (buffer.size() == capacidad) {
            System.out.println("Buffer lleno. Productor espera...");
            wait(); // Espera hasta que haya espacio
        }
        buffer.add(producto);
        System.out.println("Productor produjo: " + producto);
        notifyAll(); // Notifica al consumidor que hay un producto disponible
    }

    // Método para consumir un producto del buffer
    public synchronized int consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer vacío. Consumidor espera...");
            wait(); // Espera hasta que haya un producto
        }
        int producto = buffer.removeFirst();
        System.out.println("Consumidor consumió: " + producto);
        notifyAll(); // Notifica al productor que hay espacio disponible
        return producto;
    }
}