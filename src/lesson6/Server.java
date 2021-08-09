package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static final int PORT = 8189;
    private DataInputStream input;
    private DataOutputStream output;

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server has been started, waiting for connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client is connected");

            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());

            inputMessage();
            outputMessage();

        } catch (IOException e) {
            System.err.println("Failed to bind port " + PORT);
            e.printStackTrace();
        }
    }

    private void inputMessage() {

        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    String message = input.readUTF();
                    System.out.println("Client: " + message);
                    if (message.equals("/end")) {
                        System.out.println("Connection has been closed from client.");
                        System.exit(0);
                    }
                } catch (IOException e) {
                    System.out.println("Connection has been closed.");
                    break;
                }
            }

        });

        thread.start();

    }

    private void outputMessage() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String message = scanner.next();
                output.writeUTF(message);
                if (message.equals("/end")) {
                    System.out.println("Connection is closing.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Connection has been closed.");
                break;
            }
        }

    }

}
