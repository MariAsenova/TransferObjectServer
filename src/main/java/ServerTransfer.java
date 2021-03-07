import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransfer {

    public void start() {

        try {
            ServerSocket serverSocket = new ServerSocket(2910);

            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            System.out.println("Server with IP: " + hostAddress + " is listening on port: 2910");


            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Connection with client established");
                ServerHandler serverHandler = new ServerHandler(socket);
                new Thread(() -> handleClient(serverHandler)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(ServerHandler serverHandler) {
        serverHandler.readTransferObject();
        serverHandler.sendToClient();

    }
}
