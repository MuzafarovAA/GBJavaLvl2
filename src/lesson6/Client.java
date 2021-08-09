package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8189;
    private DataInputStream input;
    private DataOutputStream output;

    public static void main(String[] args) {

        new Client().connect();

    }

    private void connect() {

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {

            System.out.println("Connected to server");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            inputMessage();
            outputMessage();

        } catch (UnknownHostException e) {
            System.err.println("Failed to connect: unknown host");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to connect to server");
            e.printStackTrace();
        }

    }

    private void inputMessage() {

        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    String message = input.readUTF();
                    System.out.println("Server: " + message);
                    if (message.equals("/end")) {
                        System.out.println("Connection has been closed from server.");
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
