package ru.gb.java2.chat.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private static final String AUTH_OK_COMMAND = "/authOk";
    private static final String AUTH_COMMAND = "/auth";
    private static final String WRONG_USERNAME_COMMAND = "/wrongUsername";
    private static final String ALREADY_EXIST_USERNAME_COMMAND = "/alreadyExistUsername";
    private final Socket clientSocket;
    private final MyServer server;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    String username;

    public ClientHandler(MyServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    void handle() throws IOException {

            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
                    System.err.println("Failed to process message from client");
                    e.printStackTrace();
                } finally {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        System.err.println("Failed to close connection");
                        e.printStackTrace();
                    }
                }

            }).start();
        }




    private void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(AUTH_COMMAND)) {
                String[] parts = message.split(" ");
                String login = parts[1];
                String password = parts[2];

                username = server.getAuthService().getUsernameByLoginAndPassword(login, password);
                if (username == null) {
                    sendMessage(WRONG_USERNAME_COMMAND);
                } else {
                    if (server.subscribe(this, username)) {
                        sendMessage(AUTH_OK_COMMAND + " " + username);
                        return;
                    } else {
                        sendMessage(ALREADY_EXIST_USERNAME_COMMAND);
                    }
                }
            }
        }
    }

    private void closeConnection() throws IOException {
        clientSocket.close();
        server.unsubscribe(this, username);
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = inputStream.readUTF().trim();
            System.out.println("message: " + message);

            if (message.startsWith("/end")) {
                return;
            } else {
                processMessage(message);
            }
        }
    }

    private void processMessage(String message) throws IOException {
        if (message.startsWith("/w")) {
            String[] parts = message.split(" ", 3);
            String receiver = parts[1];
            message = parts[2];
            server.sendPrivateMessage(this, receiver, message);
        } else {
            server.broadcast(this, message);
        }
    }

    public void sendMessage(String message) throws IOException {

        outputStream.writeUTF(message);
    }
}
