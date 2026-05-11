public class Cozinha {
    private ComandaDigital mediador;

    public Cozinha(ComandaDigital med) {
        this.mediador = med;
    }

    public void preparar(String prato) {
        mediador.pedidoPronto(prato);
    }
}
