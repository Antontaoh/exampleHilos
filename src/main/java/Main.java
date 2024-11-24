public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Buffer con capacidad de 5 productos

        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start(); // Inicia el hilo del productor
        consumidor.start(); // Inicia el hilo del consumidor
    }
}
