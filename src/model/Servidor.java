package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Servidor {
    private double fundoAcumulado = 0.0;
    
    public synchronized Resposta processarAposta(Pedido pedido, int numeroSorteado) {
        fundoAcumulado += 2.0;
        
        if (pedido.getAposta() == numeroSorteado) {
            double ganho = fundoAcumulado * 0.60;
            fundoAcumulado = 0.0;
            return new Resposta(String.format(Locale.US, "Cofre aberto, %s! Ganhou R$ %.2f", pedido.getNome(), ganho));
        } else {
            return new Resposta(String.format(Locale.US, "Código errado, %s. O cofre tem R$ %.2f acumulados.", pedido.getNome(), fundoAcumulado));
        }
    }

    public void iniciar(int porta) {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta);
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nova conexão: " + socketCliente.getInetAddress().getHostAddress());
                new Thread(new ThreadCliente(socketCliente, this)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor().iniciar(12345);
    }
}
