class Productor extends Thread {
    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.producir(i); // Produce el producto i
                Thread.sleep(500); // Simula tiempo de producción
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}