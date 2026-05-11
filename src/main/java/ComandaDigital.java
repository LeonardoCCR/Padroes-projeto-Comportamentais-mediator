import java.util.*;

public class ComandaDigital implements PedidoMediator {

    private Cozinha cozinha;
    private Queue<String> filaPedidos = new LinkedList<>();
    private Map<String, Cliente> mapaPedidos = new HashMap<>();

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    @Override
    public void fazerPedido(String prato, Cliente cliente) {
        filaPedidos.add(prato);
        mapaPedidos.put(prato, cliente);

        if (filaPedidos.size() == 1) {
            cozinha.preparar(prato);
        }
    }

    @Override
    public void pedidoPronto(String prato) {
        Cliente c = mapaPedidos.get(prato);

        if (c != null) {
            c.receberNotificacao("Seu prato [" + prato + "] está pronto!");
            mapaPedidos.remove(prato);
        }

        if (!filaPedidos.isEmpty() && filaPedidos.peek().equals(prato)) {
            filaPedidos.poll();
        }

        if (!filaPedidos.isEmpty()) {
            String proximo = filaPedidos.peek();
            cozinha.preparar(proximo);
        }
    }

    public Queue<String> getFilaPedidos() {
        return this.filaPedidos;
    }

    public Map<String, Cliente> getMapaPedidos() {
        return this.mapaPedidos;
    }
}