public class Cliente extends Pessoa {
    private PedidoMediator mediador;

    public Cliente(String nome, PedidoMediator med) {
        this.nome = nome;
        this.mediador = med;
    }

    public void fazerPedido(String prato) {
        mediador.fazerPedido(prato, this);
    }

    public String receberNotificacao(String msg) {
        return "[" + nome + "] recebeu aviso: " + msg;
    }
}
