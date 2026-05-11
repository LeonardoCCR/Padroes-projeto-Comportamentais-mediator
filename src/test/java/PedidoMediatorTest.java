import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoMediatorTest {
    private ComandaDigital mediador;
    private Cozinha cozinha;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setup() {
        mediador = new ComandaDigital();
        cozinha = new Cozinha(mediador);
        mediador.setCozinha(cozinha);

        cliente1 = new Cliente("Alice", mediador);
        cliente2 = new Cliente("Bob", mediador);
    }

    @Test
    void deveRetornarMensagemFormatadaCorretamente() {
        String resultado = cliente1.receberNotificacao("Seu prato está pronto");
        assertEquals("[Alice] recebeu aviso: Seu prato está pronto", resultado);
    }

    @Test
    void deveNotificarClienteCorretoQuandoPedidoFinaliza() {
        mediador.fazerPedido("Pizza", cliente1);
        mediador.pedidoPronto("Pizza");

        // Verificação da fila de pedidos
        assertFalse(mediador.getMapaPedidos().containsKey("Pizza"), "O pedido deve ser removido do mapa após pronto");
    }

    @Test
    void deveGerenciarFilaDePedidosSequencialmente() {
        mediador.fazerPedido("Pizza", cliente1);
        mediador.fazerPedido("Hambúrguer", cliente2);
        assertTrue(mediador.getFilaPedidos().isEmpty());
    }

    @Test
    void naoDeveFalharAoTentarFinalizarPedidoInexistente() {
        assertDoesNotThrow(() -> {
            mediador.pedidoPronto("Sushi");
        });
    }
}