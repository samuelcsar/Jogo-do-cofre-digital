package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ThreadCliente implements Runnable {
    private Socket socket;
    private Servidor servidor;
    private Random random;

    public ThreadCliente(Socket socket, Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
        this.random = new Random();
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            Pedido pedido = (Pedido) in.readObject();
            
            int numeroSorteado = random.nextInt(1000);
            
            System.out.println("Cliente " + pedido.getNome() + " apostou " + pedido.getAposta() + ". Número sorteado: " + numeroSorteado);

            Resposta resposta = servidor.processarAposta(pedido, numeroSorteado);
            
            out.writeObject(resposta);
            out.flush();

        } catch (Exception e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                // ignorar
            }
        }
    }
}
