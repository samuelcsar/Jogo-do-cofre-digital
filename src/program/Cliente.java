package program;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import model.Pedido;
import model.Resposta;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite um número entre 0 e 999: ");
        int aposta = scanner.nextInt();

        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Pedido pedido = new Pedido(nome, aposta);
            out.writeObject(pedido);
            out.flush();

            Resposta resposta = (Resposta) in.readObject();
            System.out.println(resposta.getMensagem());

        } catch (Exception e) {
            System.err.println("Erro ao conectar com o servidor: " + e.getMessage());
        }
        
        scanner.close();
    }
}
