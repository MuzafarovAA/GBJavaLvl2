package ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.server.chat.auth.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyServer {

    private final List<ClientHandler> clients = new ArrayList<>();
    private final HashMap<String, ClientHandler> usersMatchClientHandlerHm = new HashMap<>();
    private final List<String> usersOnline = new ArrayList<>();
    private AuthService authService;

    public void start(int port) {


        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server has been started");
            authService = new AuthService();

            while (true) {
                waitAndProcessNewClientConnection(serverSocket);
            }

        } catch (IOException e) {
            System.err.println("Failed to bind port " + port);
            e.printStackTrace();
        }

    }

    private void waitAndProcessNewClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new Client connection...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client has been connected...");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public void broadcast(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (sender != client) {
                client.sendMessage(message);
            }
        }
    }

    public void sendPrivateMessage(ClientHandler sender, String receiver, String message) throws IOException {
        usersMatchClientHandlerHm.get(receiver).sendMessage(message);
    }

    protected boolean subscribe(ClientHandler clientHandler, String username) {

        if (usersMatchClientHandlerHm.containsKey(username)) {
            return false;
        } else {
            clients.add(clientHandler);
            usersMatchClientHandlerHm.put(username, clientHandler);
            return true;
        }
    }


    protected void unsubscribe(ClientHandler clientHandler, String username) {
        clients.remove(clientHandler);
        usersMatchClientHandlerHm.remove(username);
    }

    public AuthService getAuthService() {
        return authService;
    }
}
