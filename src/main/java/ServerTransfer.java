import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerTransfer {

    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void start() {

        try {
            ServerSocket serverSocket = new ServerSocket(2910);
            System.out.println("Server started");

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Connection with client established");
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Thread thread = new Thread(() -> readTransferObject());
                thread.start();
                System.out.println("Thread started");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readTransferObject() {
        byte[] dataFromClient = new byte[1024];
        try {
            int bytesRead = inputStream.read(dataFromClient, 0, dataFromClient.length);
            System.out.println("Bytes sent: " + bytesRead);

            String messageFromClient = new String(dataFromClient);
            System.out.println("Reading string with bytes sent from client: " + messageFromClient);

            // from JSON to custom object
            Customer customer = objectMapper.readValue(messageFromClient, Customer.class);
            System.out.println("Reading value from the Customer object: " + customer.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToClient() {
        Customer customer = new Customer();
        customer.set(10, "John");

        byte[] sentToClient = customer.toString().getBytes();
        int numberOfBytes = sentToClient.length;
        try {
            outputStream.write(sentToClient, 0, numberOfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
